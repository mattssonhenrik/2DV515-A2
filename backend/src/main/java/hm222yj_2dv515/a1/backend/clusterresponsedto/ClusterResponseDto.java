package hm222yj_2dv515.a1.backend.clusterresponsedto;

/**
 * JSON response: blog names + cluster index for each blog index.
 */
public class ClusterResponseDto {

    int numberOfClusters;
    int numberOfIterations;
    String[] blogNames;
    int[] clusterIndexPerBlogIndex;

    public ClusterResponseDto(
            int numberOfClusters,
            int numberOfIterations,
            String[] blogNames,
            int[] clusterIndexPerBlogIndex) {
        this.numberOfClusters = numberOfClusters;
        this.numberOfIterations = numberOfIterations;
        this.blogNames = blogNames;
        this.clusterIndexPerBlogIndex = clusterIndexPerBlogIndex;
    }

    public int getNumberOfClusters() {
        return numberOfClusters;
    }

    public int getNumberOfIterations() {
        return numberOfIterations;
    }

    public String[] getBlogNames() {
        return blogNames;
    }

    public int[] getClusterIndexPerBlogIndex() {
        return clusterIndexPerBlogIndex;
    }
}