#include "ecbenchmark/cec10/F08.h"
#include "ecbenchmark/cec10/CecMath.h"
#include "ecbenchmark/operator/Permuted.h"
#include "ecbenchmark/operator/Grouped.h"
#include "ecbenchmark/operator/Shifted.h"
#include "ecbenchmark/cec10/CecRotated.h"
#include "ecbenchmark/function/Rosenbrock.h"
#include "ecbenchmark/function/Sphere.h"

namespace ecb {
    namespace cec10 {

        F08::F08(int dimensions, int mValue)
        : CecFunction("F08", "Single-group Shifted m-dimensional Rosenbrock's Function", dimensions,
        -100, 100, true, mValue, new CecRandom(8l)) {

            std::vector<scalar> shift = CecMath::ShiftVector(dimensions, minimumDomain(), maximumDomain() - 1.0,
                    randomizer());
            std::vector<int> permutation = CecMath::PermutationVector(dimensions, randomizer());
            std::vector<scalar> permutedShift(dimensions, 0);
            for (int i = 0; i < dimensions; ++i) {
                permutedShift[i] = shift[permutation[i]];
            }

            rosenbrock = new Permuted(permutation, new Grouped(0, mValue,
                    new Shifted(permutedShift, 0, new Rosenbrock())));

            sphere = new Permuted(permutation, new Grouped(mValue, dimensions,
                    new Shifted(permutedShift, mValue, new Sphere())));
        }

        F08::~F08() {
            delete sphere;
            delete rosenbrock;
        }

        scalar F08::f(const std::vector<scalar>& x) {
            return rosenbrock->f(x) * 1e6 + sphere->f(x);
        }
    }
}

