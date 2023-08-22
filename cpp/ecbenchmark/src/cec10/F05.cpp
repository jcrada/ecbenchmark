#include "ecbenchmark/cec10/F05.h"
#include "ecbenchmark/cec10/CecMath.h"
#include "ecbenchmark/operator/Permuted.h"
#include "ecbenchmark/operator/Grouped.h"
#include "ecbenchmark/operator/Shifted.h"
#include "ecbenchmark/cec10/CecRotated.h"
#include "ecbenchmark/function/Rastrigin.h"


namespace ecb {
    namespace cec10 {

        F05::F05(int dimensions, int mValue)
        : CecFunction("F05", "Single-group Shifted and m-rotated Rastrigin's Function",
        dimensions, -5, 5, true, mValue, new CecRandom(5l)) {
            
            std::vector<scalar> shift = CecMath::ShiftVector(dimensions, minimumDomain(), maximumDomain(), randomizer());
            std::vector<int> permutation = CecMath::PermutationVector(dimensions, randomizer());
            std::vector<scalar> permutedShift(dimensions, 0);
            for (int i = 0; i < dimensions; ++i) {
                permutedShift[i] = shift[permutation[i]];
            }

            std::vector<std::vector<scalar> > rotation = CecMath::RotationMatrix(mValue, randomizer());

            rotatedRastrigin = new Permuted(permutation, new Grouped(0, mValue,
                    new Shifted(permutedShift, 0, new CecRotated(rotation, new Rastrigin()))));

            rastrigin = new Permuted(permutation, new Grouped(mValue, dimensions,
                    new Shifted(permutedShift, mValue, new Rastrigin())));
        }

        F05::~F05() {
            delete rastrigin;
            delete rotatedRastrigin;
        }

        scalar F05::f(const std::vector<scalar>& x) {
            return rotatedRastrigin->f(x) * 1e6 + rastrigin->f(x);
        }
    }
}

