#include "ecbenchmark/cec10/F18.h"
#include "ecbenchmark/cec10/CecMath.h"
#include "ecbenchmark/operator/Permuted.h"
#include "ecbenchmark/operator/Grouped.h"
#include "ecbenchmark/operator/Shifted.h"
#include "ecbenchmark/function/Rosenbrock.h"

namespace ecb {
    namespace cec10 {

        F18::F18(int dimensions, int mValue)
        : CecFunction("F18", "(D/m)-group Shifted and m-rotated Rosenbrock's Function", dimensions,
        -100, 100, true, mValue, new CecRandom(18l)) {

            std::vector<scalar>  shift = CecMath::ShiftVector(dimensions, minimumDomain(), maximumDomain() - 1, randomizer());
            std::vector<int>  permutation = CecMath::PermutationVector(dimensions, randomizer());

            std::vector<scalar>  permutedShift (dimensions,0);
            for (int i = 0; i < dimensions; ++i) {
                permutedShift[i] = shift[permutation[i]];
            }
            for (int k = 0; k < dimensions / mValue; ++k) {
                sumRosenbrock.push_back(
                        new Permuted(permutation, new Grouped(k * mValue, (k + 1) * mValue,
                        new Shifted(permutedShift, k * mValue, new Rosenbrock()))));
            }


        }

        F18::~F18() {
            for (size_t i = 0 ; i < sumRosenbrock.size(); ++i){
                delete sumRosenbrock[i];
            }
        }

        scalar F18::f(const std::vector<scalar>& x) {
            scalar result = 0;
            for (size_t i = 0 ; i < sumRosenbrock.size(); ++i) {
                result += sumRosenbrock[i]->f(x);
            }
            return result;
        }
    }
}

