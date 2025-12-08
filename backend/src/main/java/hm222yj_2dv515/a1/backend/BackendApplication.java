package hm222yj_2dv515.a1.backend;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hm222yj_2dv515.a1.backend.fileprocessor.FileProcessor;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		FileProcessor fileProcessor = new FileProcessor();
		double[][] data = fileProcessor.loadData("blogdata.txt");

		System.out.println("Printing matrix...");
		for (double[] row : data) {
			System.out.println(Arrays.toString(row));
		}
	}

}
