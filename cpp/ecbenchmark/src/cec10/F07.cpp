#include "ecbenchmark/cec10/F07.h"
#include "ecbenchmark/cec10/CecMath.h"
#include "ecbenchmark/operator/Permuted.h"
#include "ecbenchmark/operator/Grouped.h"
#include "ecbenchmark/operator/Shifted.h"
#include "ecbenchmark/function/Schwefel1_2.h"
#include "ecbenchmark/function/Sphere.h"

namespace ecb {
    namespace cec10 {

        F07::F07(int dimensions, int mValue)
        : CecFunction("F07", "Single-group Shifted m-dimensional Schwefel's Function", dimensions,
        -100, 100, true, mValue, new CecRandom(7l)) {

            std::vector<scalar> shift = CecMath::ShiftVector(dimensions, minimumDomain(), maximumDomain(), randomizer());
            std::vector<int> permutation = CecMath::PermutationVector(dimensions, randomizer());
            std::vector<scalar> permutedShift(dimensions, 0);
            for (int i = 0; i < dimensions; ++i) {
                permutedShift[i] = shift[permutation[i]];
            }
            schweffel = new Permuted(permutation, new Grouped(0, mValue,
                    new Shifted(permutedShift, 0, new Schwefel1_2())));

            sphere = new Permuted(permutation, new Grouped(mValue, dimensions,
                    new Shifted(permutedShift, mValue, new Sphere())));

        }

        F07::~F07() {
            delete sphere;
            delete schweffel;
        }

        scalar F07::f(const std::vector<scalar>& x) {
            return schweffel->f(x) * 1e6 + sphere->f(x);
        }
    }
}

