#include "ecbenchmark/cec10/F17.h"
#include "ecbenchmark/cec10/CecMath.h"
#include "ecbenchmark/operator/Permuted.h"
#include "ecbenchmark/operator/Grouped.h"
#include "ecbenchmark/operator/Shifted.h"
#include "ecbenchmark/function/Schwefel1_2.h"

namespace ecb {
    namespace cec10 {

        F17::F17(int dimensions, int mValue)
        : CecFunction("F17", "(D/m)-group Shifted and m-rotated Schwefel's Function", dimensions,
        -100, 100, true, mValue, new CecRandom(17l)) {

            std::vector<scalar> shift = CecMath::ShiftVector(dimensions, minimumDomain(), maximumDomain(), randomizer());
            std::vector<int> permutation = CecMath::PermutationVector(dimensions, randomizer());
            std::vector<scalar> permutedShift(dimensions, 0);
            for (int i = 0; i < dimensions; ++i) {
                permutedShift[i] = shift[permutation[i]];
            }
            for (int k = 0; k < dimensions / mValue; ++k) {
                sumSchweffel.push_back(
                        new Permuted(permutation, new Grouped(k * mValue, (k + 1) * mValue,
                        new Shifted(permutedShift, k * mValue, new Schwefel1_2()))));
            }

        }

        F17::~F17() {
            for (size_t i = 0; i < sumSchweffel.size(); ++i) {
                delete sumSchweffel[i];
            }
        }

        scalar F17::f(const std::vector<scalar>& x) {
            scalar result = 0;
            for (size_t i  =0 ; i < sumSchweffel.size(); ++i) {
                result += sumSchweffel[i]->f(x);
            }
            return result;
        }
    }
}

