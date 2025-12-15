package hm222yj_2dv515.a1.backend.fileprocessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import hm222yj_2dv515.a1.backend.blogdatatomemory.BlogDataToMemory;

/**
 * Reads a tab-separated file and returns the numeric columns as a 2D array in
 * for of a BlogDataToMemory-object.
 */
@Service
public class FileProcessor {

    /**
     * Loads blog names + numeric vectors from a tab-separated file.
     * The first line is treated as a header. The first column in each row is the
     * blog name.
     *
     * @param filePath path to the dataset file which is in the root of the backend project.
     * @return in-memory dataset containing blog names and vectors
     */
    public BlogDataToMemory loadData(String filePath) {
        List<double[]> dataList = new ArrayList<>();
        List<String> blogNameList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String headerLine = bufferedReader.readLine(); // Måste konsumera headers för att inte få error.
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split("\t");
                double[] vector = new double[values.length - 1];

                for (int i = 1; i < values.length; i++) {
                    vector[i - 1] = Double.parseDouble(values[i]);
                }
                dataList.add(vector);

                blogNameList.add(values[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] blogNames = blogNameList.toArray(new String[0]);
        double[][] vectors = dataList.toArray(new double[0][0]);

        return new BlogDataToMemory(blogNames, vectors);
    }
}
