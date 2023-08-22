#include "ecbenchmark/cec10/F04.h"
#include "ecbenchmark/cec10/CecMath.h"
#include "ecbenchmark/operator/Permuted.h"
#include "ecbenchmark/operator/Grouped.h"
#include "ecbenchmark/operator/Shifted.h"
#include "ecbenchmark/cec10/CecRotated.h"
#include "ecbenchmark/function/Elliptic.h"

namespace ecb {
    namespace cec10 {

        F04::F04(int dimensions, int mValue)
        : CecFunction("F04", "Single-group Shifted and m-rotated Elliptic Function",
        dimensions, -100, 100, true, mValue, new CecRandom(4l)) {
            std::vector<scalar> shift = CecMath::ShiftVector(dimensions, minimumDomain(), maximumDomain(), randomizer());
            std::vector<int> permutation = CecMath::PermutationVector(dimensions, randomizer());
            std::vector<std::vector<scalar> > rotation = CecMath::RotationMatrix(mValue, randomizer());

            std::vector<scalar> permutedShift(dimensions, 0);
            for (int i = 0; i < dimensions; ++i) {
                permutedShift[i] = shift[permutation[i]];
            }

            rotatedElliptic = new Permuted(permutation, new Grouped(0, mValue,
                    new Shifted(permutedShift, 0,
                    new CecRotated(rotation, new Elliptic()))));

            elliptic = new Permuted(permutation, new Grouped(mValue, dimensions,
                    new Shifted(permutedShift, mValue,
                    new Elliptic())));
        }

        F04::~F04() {
            delete elliptic;
            delete rotatedElliptic;
        }

        scalar F04::f(const std::vector<scalar>& x) {
            return rotatedElliptic->f(x) * 1e6 + elliptic->f(x);
        }
    }
}

