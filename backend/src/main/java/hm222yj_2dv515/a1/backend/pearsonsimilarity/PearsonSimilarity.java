package hm222yj_2dv515.a1.backend.pearsonsimilarity;

import org.springframework.stereotype.Service;

/**
 * Computes Pearson correlation similarity between two equal-length vectors.
 */
@Service
public class PearsonSimilarity {

    /**
     * Returns the Pearson correlation coefficient for two vectors.
     *
     * @param firstVector  first vector
     * @param secondVector second vector
     * @return correlation in [-1, 1]. Returns 0.0 if vectors are invalid or denominator is 0.
     */
    public double pearsonSimilarity(double[] firstVector, double[] secondVector) {
        double sumOfFirstVectorValues = 0.0;
        double sumOfSecondVectorValues = 0.0;
        double sumOfSquaredFirstVectorValues = 0.0;
        double sumOfSquaredSecondVectorValues = 0.0;
        double sumOfValueProducts = 0.0;
        int numberOfDimensions = firstVector.length;

        for (int dimensionIndex = 0; dimensionIndex < numberOfDimensions; dimensionIndex++) {
            double firstValue = firstVector[dimensionIndex];
            double secondValue = secondVector[dimensionIndex];

            sumOfFirstVectorValues += firstValue;
            sumOfSecondVectorValues += secondValue;

            sumOfSquaredFirstVectorValues += firstValue * firstValue;
            sumOfSquaredSecondVectorValues += secondValue * secondValue;

            sumOfValueProducts += firstValue * secondValue;
        }
        double numerator = sumOfValueProducts
                - ((sumOfFirstVectorValues * sumOfSecondVectorValues) / numberOfDimensions);
        double denominatorLeftTerm = sumOfSquaredFirstVectorValues
                - ((sumOfFirstVectorValues * sumOfFirstVectorValues) / numberOfDimensions);
        double denominatorRightTerm = sumOfSquaredSecondVectorValues
                - ((sumOfSecondVectorValues * sumOfSecondVectorValues) / numberOfDimensions);
        double denominator = Math.sqrt(denominatorLeftTerm * denominatorRightTerm);
        if (denominator == 0.0) {
            return 0.0;
        }
        return numerator / denominator;
    }
}





