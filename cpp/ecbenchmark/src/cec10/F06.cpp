#include "ecbenchmark/cec10/F06.h"
#include "ecbenchmark/cec10/CecMath.h"
#include "ecbenchmark/operator/Permuted.h"
#include "ecbenchmark/operator/Grouped.h"
#include "ecbenchmark/operator/Shifted.h"
#include "ecbenchmark/cec10/CecRotated.h"
#include "ecbenchmark/function/Ackley.h"

namespace ecb {
    namespace cec10 {

        F06::F06(int dimensions, int mValue)
        : CecFunction("F06", "Single-group Shifted and m-rotated Ackley's Function", dimensions,
        -32, 32, true, mValue, new CecRandom(6l)) {

            std::vector<scalar> shift = CecMath::ShiftVector(dimensions, minimumDomain(), maximumDomain(), randomizer());
            std::vector<int> permutation = CecMath::PermutationVector(dimensions, randomizer());
            std::vector<std::vector<scalar> > rotation = CecMath::RotationMatrix(mValue, randomizer());
            std::vector<scalar> permutedShift(dimensions, 0);
            for (int i = 0; i < dimensions; ++i) {
                permutedShift[i] = shift[permutation[i]];
            }

            rotatedAckley = new Permuted(permutation, new Grouped(0, mValue,
                    new Shifted(permutedShift, 0, new CecRotated(rotation, new Ackley()))));

            ackley = new Permuted(permutation, new Grouped(mValue, dimensions,
                    new Shifted(permutedShift, mValue, new Ackley())));

        }

        F06::~F06() {
            delete ackley;
            delete rotatedAckley;
        }

        scalar F06::f(const std::vector<scalar>& x) {
            return rotatedAckley->f(x) * 1e6 + ackley->f(x);
        }
    }
}

