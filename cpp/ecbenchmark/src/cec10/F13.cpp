#include "ecbenchmark/cec10/F13.h"
#include "ecbenchmark/cec10/CecMath.h"
#include "ecbenchmark/operator/Permuted.h"
#include "ecbenchmark/operator/Grouped.h"
#include "ecbenchmark/operator/Shifted.h"
#include "ecbenchmark/function/Rosenbrock.h"
#include "ecbenchmark/function/Sphere.h"

namespace ecb {
    namespace cec10 {

        F13::F13(int dimensions, int mValue)
        : CecFunction("F13", "(D/2m)-group Shifted and m-rotated Rosenbrocks's Function", dimensions,
        -100, 100, true, mValue, new CecRandom(13l)) {

            std::vector<scalar> shift = CecMath::ShiftVector(dimensions, minimumDomain(), maximumDomain() - 1, randomizer());
            std::vector<int> permutation = CecMath::PermutationVector(dimensions, randomizer());
            std::vector<scalar> permutedShift(dimensions, 0);
            for (int i = 0; i < dimensions; ++i) {
                permutedShift[i] = shift[permutation[i]];
            }
            for (int k = 0; k < dimensions / (2 * mValue); ++k) {
                sumRosenbrock.push_back(
                        new Permuted(permutation, new Grouped(k * mValue, (k + 1) * mValue,
                        new Shifted(permutedShift, k * mValue, new Rosenbrock()))));
            }


            sphere = new Permuted(permutation, new Grouped(dimensions / 2, dimensions,
                    new Shifted(permutedShift, dimensions / 2, new Sphere())));

        }

        F13::~F13() {
            delete sphere;
            for (size_t i = 0; i < sumRosenbrock.size(); ++i) {
                delete sumRosenbrock[i];
            }
        }

        scalar F13::f(const std::vector<scalar>& x) {
            scalar result = 0;
            for (size_t i = 0 ; i < sumRosenbrock.size(); ++i) {
                result += sumRosenbrock[i]->f(x);
            }
            return result + sphere->f(x);
        }
    }
}

