/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import java.util.Random;

/**
 *
 * @author jcrada
 */
public class CecMath {

    

    public static double[] ShiftVector(int size, double min, double max, Random random) {
        /** Code extracted from
         * K. Tang, Xiaodong Li, P. N. Suganthan, Z. Yang and T. Weise, 
         * "Benchmark Functions for the CEC'2010 Special Session and Competition 
         * on Large Scale Global Optimization," Technical Report, Nature Inspired 
         * Computation and Applications Laboratory, USTC, China,
         * http://nical.ustc.edu.cn/cec10ss.php
         */
        final double[] result;
        final double hw, middle;
        double s;
        int i;

        hw = (0.5d * (max - min));
        middle = (min + hw);
        result = new double[size];

        for (i = (size - 1); i >= 0; i--) {
            do {
                s = (middle + (random.nextGaussian() * hw));
            } while ((s < min) || (s > max));
            result[i] = s;
        }

        return result;
    }


    public static double[][] RotationMatrix(int mValue, Random random) {
        /** Code extracted from
         * K. Tang, Xiaodong Li, P. N. Suganthan, Z. Yang and T. Weise, 
         * "Benchmark Functions for the CEC'2010 Special Session and Competition 
         * on Large Scale Global Optimization," Technical Report, Nature Inspired 
         * Computation and Applications Laboratory, USTC, China,
         * http://nical.ustc.edu.cn/cec10ss.php
         */
        final double[][] m;
        int i, j, k;
        double dp, t;

        m = new double[mValue][mValue];

        outer:
        for (;;) {

            // initialize
            for (i = (mValue - 1); i >= 0; i--) {
                for (j = (mValue - 1); j >= 0; j--) {
                    m[i][j] = random.nextGaussian();
                }
            }

            // main loop of gram/schmidt
            for (i = (mValue - 1); i >= 0; i--) {

                //
                for (j = (mValue - 1); j > i; j--) {

                    // dot product
                    dp = 0d;
                    for (k = (mValue - 1); k >= 0; k--) {
                        dp += (m[i][k] * m[j][k]);
                    }

                    // subtract
                    for (k = (mValue - 1); k >= 0; k--) {
                        m[i][k] -= (dp * m[j][k]);
                    }
                }

                // normalize
                dp = 0d;
                for (k = (mValue - 1); k >= 0; k--) {
                    t = m[i][k];
                    dp += (t * t);
                }

                // linear dependency -> restart
                if (dp <= 0d) {
                    continue outer;
                }
                dp = (1d / Math.sqrt(dp));

                for (k = (mValue - 1); k >= 0; k--) {
                    m[i][k] *= dp;
                }
            }

            return m;
        }
    }

    public static double[] Multiply(final double[] vector, final double[][] matrix) {
        //a square matrix is assumed, i.e. vector.length == matrix.length
        double result[] = new double[vector.length];
        /** TODO: Check code because this is an unusual way to multiply a matrix by a vector. 
         * Code extracted from
         * K. Tang, Xiaodong Li, P. N. Suganthan, Z. Yang and T. Weise, 
         * "Benchmark Functions for the CEC'2010 Special Session and Competition 
         * on Large Scale Global Optimization," Technical Report, Nature Inspired 
         * Computation and Applications Laboratory, USTC, China,
         * http://nical.ustc.edu.cn/cec10ss.php
         */
        int index = matrix.length - 1;
        for (int j = 0; j < vector.length; ++j) {
            for (int i = 0; i < vector.length; ++i) {
                result[index] += vector[i] * matrix[i][index];
            }
            --index;
        }
        return result;
    }

    public static int[] PermutationVector(int dimensions, Random random) {
        /** Code extracted from
         * K. Tang, Xiaodong Li, P. N. Suganthan, Z. Yang and T. Weise, 
         * "Benchmark Functions for the CEC'2010 Special Session and Competition 
         * on Large Scale Global Optimization," Technical Report, Nature Inspired 
         * Computation and Applications Laboratory, USTC, China,
         * http://nical.ustc.edu.cn/cec10ss.php
         */
        int[] result = new int[dimensions];
        for (int i = 0; i < dimensions; ++i) {
            result[i] = i;
        }

        for (int i = (dimensions << 3); i >= 0; i--) {
            int k, j = random.nextInt(dimensions);
            do {
                k = random.nextInt(dimensions);
            } while (k == j);

            int tmp = result[j];
            result[j] = result[k];
            result[k] = tmp;
        }
        return result;
    }

    
}
