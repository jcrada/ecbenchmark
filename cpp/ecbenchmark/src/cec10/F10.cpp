#include "ecbenchmark/cec10/F10.h"
#include "ecbenchmark/cec10/CecMath.h"
#include "ecbenchmark/operator/Permuted.h"
#include "ecbenchmark/operator/Grouped.h"
#include "ecbenchmark/operator/Shifted.h"
#include "ecbenchmark/cec10/CecRotated.h"
#include "ecbenchmark/function/Rastrigin.h"

namespace ecb {
    namespace cec10 {

        F10::F10(int dimensions, int mValue)
        : CecFunction("F10", "(D/2m)-group Shifted and m-rotated Rastrigin's Function", dimensions,
        -5, 5, true, mValue, new CecRandom(10l)) {

            std::vector<scalar> shift = CecMath::ShiftVector(dimensions, minimumDomain(), maximumDomain(), randomizer());
            std::vector<int> permutation = CecMath::PermutationVector(dimensions, randomizer());
            std::vector<std::vector<scalar> > rotation = CecMath::RotationMatrix(mValue, randomizer());
            std::vector<scalar> permutedShift(dimensions, 0);
            for (int i = 0; i < dimensions; ++i) {
                permutedShift[i] = shift[permutation[i]];
            }

            for (int k = 0; k < dimensions / (2 * mValue); ++k) {
                sumRastrigin.push_back(
                        new Permuted(permutation, new Grouped(k * mValue, (k + 1) * mValue,
                        new Shifted(permutedShift, k * mValue, new CecRotated(rotation, new Rastrigin())))));
            }


            rastrigin = new Permuted(permutation, new Grouped(dimensions / 2, dimensions,
                    new Shifted(permutedShift, dimensions / 2, new Rastrigin())));

        }

        F10::~F10() {
            delete rastrigin;
            for (size_t i = 0; i < sumRastrigin.size(); ++i) {
                delete sumRastrigin[i];
            }
        }

        scalar F10::f(const std::vector<scalar>& x) {
            scalar result = 0;
            for (size_t i = 0; i < sumRastrigin.size(); ++i) {
                result += sumRastrigin[i]->f(x);
            }
            return result + rastrigin->f(x);
        }
    }
}

