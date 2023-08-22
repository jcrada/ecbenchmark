#include "ecbenchmark/cec10/F11.h"
#include "ecbenchmark/cec10/CecMath.h"
#include "ecbenchmark/operator/Permuted.h"
#include "ecbenchmark/operator/Grouped.h"
#include "ecbenchmark/operator/Shifted.h"
#include "ecbenchmark/cec10/CecRotated.h"
#include "ecbenchmark/function/Ackley.h"

namespace ecb {
    namespace cec10 {

        F11::F11(int dimensions, int mValue)
        : CecFunction("F11", "(D/2m)-group Shifted and m-rotated Ackley's Function", dimensions,
        -32, 32, true, mValue, new CecRandom(11l)) {

            std::vector<scalar> shift = CecMath::ShiftVector(dimensions, minimumDomain(), maximumDomain(), randomizer());
            std::vector<int> permutation = CecMath::PermutationVector(dimensions, randomizer());
            std::vector<std::vector<scalar> > rotation = CecMath::RotationMatrix(mValue, randomizer());
            std::vector<scalar> permutedShift(dimensions, 0);
            for (int i = 0; i < dimensions; ++i) {
                permutedShift[i] = shift[permutation[i]];
            }

            for (int k = 0; k < dimensions / (2 * mValue); ++k) {
                sumAckley.push_back(
                        new Permuted(permutation, new Grouped(k * mValue, (k + 1) * mValue,
                        new Shifted(permutedShift, k * mValue, new CecRotated(rotation, new Ackley())))));
            }


            ackley = new Permuted(permutation, new Grouped(dimensions / 2, dimensions,
                    new Shifted(permutedShift, dimensions / 2, new Ackley())));

        }

        F11::~F11() {
            delete ackley;
            for (size_t i = 0; i < sumAckley.size(); ++i) {
                delete sumAckley[i];
            }
        }

        scalar F11::f(const std::vector<scalar>& x) {
            scalar result = 0;
            for (size_t i = 0; i < sumAckley.size(); ++i) {
                result += sumAckley[i]->f(x);
            }
            return result + ackley->f(x);
        }
    }
}

