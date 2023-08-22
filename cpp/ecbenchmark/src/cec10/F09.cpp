#include "ecbenchmark/cec10/F09.h"
#include "ecbenchmark/cec10/CecMath.h"
#include "ecbenchmark/operator/Permuted.h"
#include "ecbenchmark/operator/Grouped.h"
#include "ecbenchmark/operator/Shifted.h"
#include "ecbenchmark/cec10/CecRotated.h"
#include "ecbenchmark/function/Elliptic.h"

namespace ecb {
    namespace cec10 {

        F09::F09(int dimensions, int mValue)
        : CecFunction("F09", "(D/2m)-group Shifted and m-rotated Elliptic Function", dimensions,
        -100, 100, true, mValue, new CecRandom(9l)) {

            std::vector<scalar> shift = CecMath::ShiftVector(dimensions, minimumDomain(), maximumDomain(), randomizer());
            std::vector<int> permutation = CecMath::PermutationVector(dimensions, randomizer());
            std::vector<std::vector<scalar> > rotation = CecMath::RotationMatrix(mValue, randomizer());

            std::vector<scalar> permutedShift(dimensions, 0);
            for (int i = 0; i < dimensions; ++i) {
                permutedShift[i] = shift[permutation[i]];
            }

            for (int k = 0; k < dimensions / (2 * mValue); ++k) {
                sumElliptic.push_back(
                        new Permuted(permutation, new Grouped(k * mValue, (k + 1) * mValue,
                        new Shifted(permutedShift, k * mValue, new CecRotated(rotation, new Elliptic())))));
            }

            elliptic = new Permuted(permutation, new Grouped(dimensions / 2, dimensions,
                    new Shifted(permutedShift, dimensions / 2, new Elliptic())));


        }

        F09::~F09() {
            delete elliptic;
            for (size_t i = 0; i < sumElliptic.size(); ++i) {
                delete sumElliptic[i];
            }
        }

        scalar F09::f(const std::vector<scalar>& x) {
            scalar result = 0;
            for (size_t i = 0; i < sumElliptic.size(); ++i) {
                result += sumElliptic[i]->f(x);
            }
            return result + elliptic->f(x);
        }
    }
}

