#include "ecbenchmark/cec10/CecMath.h"
#include "ecbenchmark/cec10/CecRandom.h"

#include "ecbenchmark/util/CommonMath.h"

#include <math.h>
namespace ecb {
    namespace cec10 {

        CecMath::CecMath() {

        }

        std::vector<scalar> CecMath::ShiftVector(int size, scalar min, scalar max, CecRandom* random) {
            /** Code extracted and adapted from
             * K. Tang, Xiaodong Li, P. N. Suganthan, Z. Yang and T. Weise, 
             * "Benchmark Functions for the CEC'2010 Special Session and Competition 
             * on Large Scale Global Optimization," Technical Report, Nature Inspired 
             * Computation and Applications Laboratory, USTC, China,
             * http://nical.ustc.edu.cn/cec10ss.php
             */
            std::vector<scalar> result(size, 0);
            scalar hw, middle;
            scalar s;
            int i;

            hw = (0.5 * (max - min));
            middle = (min + hw);

            for (i = (size - 1); i >= 0; i--) {
                do {
                    s = (middle + (random->nextGaussian() * hw));
                } while (CommonMath::IsLower(s, min) || CommonMath::IsGreater(s, max));
                result[i] = s;
            }

            return result;
        }

        std::vector<int> CecMath::PermutationVector(int dimensions, CecRandom* random) {
            /** Code extracted from
             * K. Tang, Xiaodong Li, P. N. Suganthan, Z. Yang and T. Weise, 
             * "Benchmark Functions for the CEC'2010 Special Session and Competition 
             * on Large Scale Global Optimization," Technical Report, Nature Inspired 
             * Computation and Applications Laboratory, USTC, China,
             * http://nical.ustc.edu.cn/cec10ss.php
             */
            std::vector<int> result(dimensions, 0);

            for (int i = 0; i < dimensions; ++i) {
                result[i] = i;
            }

            for (int i = (dimensions << 3); i >= 0; i--) {
                int k, j = random->nextInt(dimensions);
                do {
                    k = random->nextInt(dimensions);
                } while (k == j);

                int tmp = result[j];
                result[j] = result[k];
                result[k] = tmp;
            }
            return result;
        }

        std::vector<std::vector<scalar> > CecMath::RotationMatrix(int mValue, CecRandom* random) {
            /** Code extracted from
             * K. Tang, Xiaodong Li, P. N. Suganthan, Z. Yang and T. Weise, 
             * "Benchmark Functions for the CEC'2010 Special Session and Competition 
             * on Large Scale Global Optimization," Technical Report, Nature Inspired 
             * Computation and Applications Laboratory, USTC, China,
             * http://nical.ustc.edu.cn/cec10ss.php
             */
            std::vector<std::vector<scalar> > m(mValue, std::vector<scalar > (mValue, 0));
            
            int i, j, k;
            scalar dp, t;

outer:
            for (;;) {

                // initialize
                for (i = (mValue - 1); i >= 0; i--) {
                    for (j = (mValue - 1); j >= 0; j--) {
                        m[i][j] = random->nextGaussian();
                    }
                }

                // main loop of gram/schmidt
                for (i = (mValue - 1); i >= 0; i--) {

                    //
                    for (j = (mValue - 1); j > i; j--) {

                        // dot product
                        dp = 0;
                        for (k = (mValue - 1); k >= 0; k--) {
                            dp += (m[i][k] * m[j][k]);
                        }

                        // subtract
                        for (k = (mValue - 1); k >= 0; k--) {
                            m[i][k] -= (dp * m[j][k]);
                        }
                    }

                    // normalize
                    dp = 0;
                    for (k = (mValue - 1); k >= 0; k--) {
                        t = m[i][k];
                        dp += (t * t);
                    }

                    // linear dependency -> restart
                    if (dp <= 0) {
                        goto outer;
                    }
                    dp = (1.0 / sqrt(dp));

                    for (k = (mValue - 1); k >= 0; k--) {
                        m[i][k] *= dp;
                    }
                }

                return m;
            }
        }

        std::vector<scalar> CecMath::Multiply(const std::vector<scalar>& vector,
                const std::vector<std::vector<scalar> >& matrix) {
            //a square matrix is assumed, i.e. vector.length == matrix.length
            std::vector<scalar> result(vector.size(), 0);
            /** TODO: Check code because this is an unusual way to multiply a matrix by a vector. 
             * Code extracted from
             * K. Tang, Xiaodong Li, P. N. Suganthan, Z. Yang and T. Weise, 
             * "Benchmark Functions for the CEC'2010 Special Session and Competition 
             * on Large Scale Global Optimization," Technical Report, Nature Inspired 
             * Computation and Applications Laboratory, USTC, China,
             * http://nical.ustc.edu.cn/cec10ss.php
             */
            int index = matrix.size() - 1;
            for (size_t j = 0; j < vector.size(); ++j) {
                for (size_t i = 0; i < vector.size(); ++i) {
                    result[index] += vector[i] * matrix[i][index];
                }
                --index;
            }
            return result;
        }





    }

}
