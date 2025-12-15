package hm222yj_2dv515.a1.backend.kmeansclusteringservice;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import hm222yj_2dv515.a1.backend.blogdatatomemory.BlogDataToMemory;
import hm222yj_2dv515.a1.backend.clusterresponsedto.ClusterResponseDto;
import hm222yj_2dv515.a1.backend.fileprocessor.FileProcessor;
import hm222yj_2dv515.a1.backend.pearsonsimilarity.PearsonSimilarity;

/**
 * K-means clustering using Pearson similarity.
 */
@Service
public class KMeansClusteringService {
    FileProcessor fileProcessor;
    PearsonSimilarity pearsonSimilarity;

    public KMeansClusteringService(FileProcessor fileProcessor, PearsonSimilarity pearsonSimilarity) {
        this.fileProcessor = fileProcessor;
        this.pearsonSimilarity = pearsonSimilarity;
    }

    public ClusterResponseDto clusterBlogs(int numberOfClusters, int numberOfIterations) {
        BlogDataToMemory blogData = fileProcessor.loadData("blogdata.txt");
        int[] clusterIndexPerBlogIndex = runKMeans(
                blogData.getVectors(),
                numberOfClusters,
                numberOfIterations);

        return new ClusterResponseDto(
                numberOfClusters,
                numberOfIterations,
                blogData.getBlogNames(),
                clusterIndexPerBlogIndex);
    }

    private int[] runKMeans(double[][] vectors, int numberOfClusters, int numberOfIterations) {
        int numberOfBlogs = vectors.length;
        int numberOfDimensions = vectors[0].length;
        double[][] centroids = new double[numberOfClusters][numberOfDimensions];
        for (int clusterIndex = 0; clusterIndex < numberOfClusters; clusterIndex++) {
            centroids[clusterIndex] = vectors[clusterIndex].clone();
        }
        int[] clusterIndexPerBlogIndex = new int[numberOfBlogs];
        for (int iterationIndex = 0; iterationIndex < numberOfIterations; iterationIndex++) {
            // --------------Choose the cluster centroid with highest Pearson
            // similarity--------------
            for (int blogIndex = 0; blogIndex < numberOfBlogs; blogIndex++) {
                int bestClusterIndex = 0;
                double bestSimilarityScore = pearsonSimilarity.pearsonSimilarity(vectors[blogIndex], centroids[0]);
                for (int clusterIndex = 1; clusterIndex < numberOfClusters; clusterIndex++) {
                    double similarityScore = pearsonSimilarity.pearsonSimilarity(vectors[blogIndex],
                            centroids[clusterIndex]);
                    if (similarityScore > bestSimilarityScore) {
                        bestSimilarityScore = similarityScore;
                        bestClusterIndex = clusterIndex;
                    }
                }
                clusterIndexPerBlogIndex[blogIndex] = bestClusterIndex;
            }
            // --------------Average per dimension--------------
            double[][] newCentroids = new double[numberOfClusters][numberOfDimensions];
            int[] numberOfMembersPerCluster = new int[numberOfClusters];
            for (int blogIndex = 0; blogIndex < numberOfBlogs; blogIndex++) {
                int clusterIndex = clusterIndexPerBlogIndex[blogIndex];
                numberOfMembersPerCluster[clusterIndex]++;

                for (int dimensionIndex = 0; dimensionIndex < numberOfDimensions; dimensionIndex++) {
                    newCentroids[clusterIndex][dimensionIndex] += vectors[blogIndex][dimensionIndex];
                }
            }
            for (int clusterIndex = 0; clusterIndex < numberOfClusters; clusterIndex++) {
                int numberOfMembers = numberOfMembersPerCluster[clusterIndex];
                if (numberOfMembers == 0) {
                    newCentroids[clusterIndex] = centroids[clusterIndex];
                    continue;
                }
                for (int dimensionIndex = 0; dimensionIndex < numberOfDimensions; dimensionIndex++) {
                    newCentroids[clusterIndex][dimensionIndex] /= numberOfMembers;
                }
            }
            centroids = newCentroids;
        }
        return clusterIndexPerBlogIndex;
    }
}