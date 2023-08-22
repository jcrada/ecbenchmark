#include "ecbenchmark/cec10/F15.h"
#include "ecbenchmark/cec10/CecMath.h"
#include "ecbenchmark/operator/Permuted.h"
#include "ecbenchmark/operator/Grouped.h"
#include "ecbenchmark/operator/Shifted.h"
#include "ecbenchmark/cec10/CecRotated.h"
#include "ecbenchmark/function/Rastrigin.h"

namespace ecb {
    namespace cec10 {

        F15::F15(int dimensions, int mValue)
        : CecFunction("F15", "(D/m)-group Shifted and m-rotated Rastrigin's Function", dimensions,
        -5, 5, true, mValue, new CecRandom(15l)) {

            std::vector<scalar>  shift = CecMath::ShiftVector(dimensions, minimumDomain(), maximumDomain(), randomizer());
            std::vector<int>  permutation = CecMath::PermutationVector(dimensions, randomizer());
            std::vector<std::vector<scalar> >  rotation = CecMath::RotationMatrix(mValue, randomizer());
            std::vector<scalar>  permutedShift (dimensions,0);
            for (int i = 0; i < dimensions; ++i) {
                permutedShift[i] = shift[permutation[i]];
            }
            for (int k = 0; k < dimensions / mValue; ++k) {
                sumRastrigin.push_back(
                        new Permuted(permutation, new Grouped(k * mValue, (k + 1) * mValue,
                        new Shifted(permutedShift, k * mValue, new CecRotated(rotation, new Rastrigin())))));
            }

        }

        F15::~F15() {
            for (size_t i = 0 ; i < sumRastrigin.size(); ++i){
                delete sumRastrigin[i];
            }
        }

        scalar F15::f(const std::vector<scalar>& x) {
            scalar result = 0;
            for (size_t i = 0 ;i < sumRastrigin.size(); ++i) {
                result += sumRastrigin[i]->f(x);
            }
            return result;
        }
    }
}

