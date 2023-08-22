#include "ecbenchmark/cec10/F12.h"
#include "ecbenchmark/cec10/CecMath.h"
#include "ecbenchmark/operator/Permuted.h"
#include "ecbenchmark/operator/Grouped.h"
#include "ecbenchmark/operator/Shifted.h"
#include "ecbenchmark/function/Schwefel1_2.h"
#include "ecbenchmark/function/Sphere.h"

namespace ecb {
    namespace cec10 {

        F12::F12(int dimensions, int mValue)
        : CecFunction("F12", "(D/2m)-group Shifted and m-rotated Rastrigin's Function", dimensions,
        -100, 100, true, mValue, new CecRandom(12l)) {

            std::vector<scalar> shift = CecMath::ShiftVector(dimensions, minimumDomain(), maximumDomain(), randomizer());
            std::vector<int> permutation = CecMath::PermutationVector(dimensions, randomizer());
            std::vector<scalar> permutedShift(dimensions, 0);
            for (int i = 0; i < dimensions; ++i) {
                permutedShift[i] = shift[permutation[i]];
            }
            for (int k = 0; k < dimensions / (2 * mValue); ++k) {
                sumSchwefel.push_back(
                        new Permuted(permutation, new Grouped(k * mValue, (k + 1) * mValue,
                        new Shifted(permutedShift, k * mValue, new Schwefel1_2()))));
            }

            sphere = new Permuted(permutation, new Grouped(dimensions / 2, dimensions,
                    new Shifted(permutedShift, dimensions / 2, new Sphere())));


        }

        F12::~F12() {
            delete sphere;
            for (size_t i = 0; i < sumSchwefel.size(); ++i){
                delete sumSchwefel[i];
            }
        }

        scalar F12::f(const std::vector<scalar>& x) {
            scalar result = 0;
            for (size_t i = 0 ; i < sumSchwefel.size() ; ++i) {
                result += sumSchwefel[i]->f(x);
            }
            return result + sphere->f(x);
        }
    }
}

