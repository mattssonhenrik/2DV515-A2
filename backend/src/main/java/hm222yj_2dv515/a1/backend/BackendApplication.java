package hm222yj_2dv515.a1.backend;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hm222yj_2dv515.a1.backend.blogdatatomemory.BlogDataToMemory;
import hm222yj_2dv515.a1.backend.fileprocessor.FileProcessor;
import hm222yj_2dv515.a1.backend.pearsonsimilarity.PearsonSimilarity;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);

		FileProcessor fileProcessor = new FileProcessor();
		BlogDataToMemory blogData = fileProcessor.loadData("blogdata.txt");

		PearsonSimilarity pearsonSimilarity = new PearsonSimilarity();

		double[] firstBlogVector = blogData.getVectors()[0];
		double[] secondBlogVector = blogData.getVectors()[1];

		double score = pearsonSimilarity.pearsonSimilarity(firstBlogVector, secondBlogVector);

		String firstBlogName = blogData.getBlogNames()[0];
		String secondBlogName = blogData.getBlogNames()[1];

		System.out.println("Pearson similarity:");
		System.out.println(firstBlogName + " vs " + secondBlogName + " => " + score);
	}

}
