package hm222yj_2dv515.a1.backend.fileprocessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    public double[][] loadData(String filePath) {
        List<double[]> dataList = new ArrayList<>();

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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList.toArray(new double[0][0]);
    }
}
