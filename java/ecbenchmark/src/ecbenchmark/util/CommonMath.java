/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.util;

import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author jcrada
 */
public class CommonMath {

    // [target_min, target_max)
    public static double Scale(double src_min, double src_max, double value,
            double target_min, double target_max) {
        return value / ((src_max - src_min) / (target_max - target_min)) + target_min;
    }

    public static double[] Multiply(final double[] vector, final double[][] matrix) {
        //a square matrix is assumed, i.e. vector.length == matrix.length
        double result[] = new double[vector.length];
        for (int row = 0; row < matrix.length; ++row) {
            for (int j = 0; j < vector.length; ++j) {
                result[row] += vector[j] * matrix[row][j];
            }
        }
        return result;
    }

    public static double Pow(double x, int e) {
        double result = 1.0;
        for (int i = 0; i < e; ++i) {
            result *= x;
        }
        return result;
    }

    public static double Pow2(double x) {
        return x * x;
    }



    public static <T> String toString(T[] array) {
        String result = "";
        for (T x : array) {
            result += x + " ";
        }
        return result;
    }
    public static final DecimalFormat DF = new DecimalFormat("#############.###");

    public static String toString(double[] array) {
        String result = "";
        for (double x : array) {
            result += DF.format(x) + " ";
        }
        return result;
    }

    public static String toString(int[] array) {
        String result = "";
        for (int x : array) {
            result += x + " ";
        }
        return result;
    }

    public static String toString(double[][] matrix) {
        String result = "";
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                result += DF.format(matrix[i][j]) + " ";
            }
            result += "\n";
        }
        return result;
    }

    public static double StDev(final List<? extends Number> x, double mean) {
        double result = 0;
        for (Number n : x) {
            result += Pow2(n.doubleValue() - mean);
        }
        return Math.sqrt(result / x.size());
    }

    public static double StDev(final List<? extends Number> x) {
        return StDev(x, Mean(x));

    }

    public static double Mean(final List<? extends Number> x) {
        double result = 0;
        for (Number n : x) {
            result += n.doubleValue();
        }
        return result / x.size();
    }

    public static <T extends Number> T Min(final List<T> items) {
        T result = null;
        double min = Double.POSITIVE_INFINITY;

        for (T x : items) {
            if (x.doubleValue() < min) {
                result = x;
                min = x.doubleValue();
            }
        }
        return result;
    }

    public static <T extends Number> T Max(final List<T> items) {
        T result = null;
        double max = Double.NEGATIVE_INFINITY;

        for (T x : items) {
            if (x.doubleValue() > max) {
                result = x;
                max = x.doubleValue();
            }
        }
        return result;
    }
}
