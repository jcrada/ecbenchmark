#include "ecbenchmark/cec10/F14.h"
#include "ecbenchmark/cec10/CecMath.h"
#include "ecbenchmark/operator/Permuted.h"
#include "ecbenchmark/operator/Grouped.h"
#include "ecbenchmark/operator/Shifted.h"
#include "ecbenchmark/cec10/CecRotated.h"
#include "ecbenchmark/function/Elliptic.h"

namespace ecb {
    namespace cec10 {

        F14::F14(int dimensions, int mValue)
        : CecFunction("F14", "(D/m)-group Shifted and m-rotated Elliptic's Function", dimensions,
        -100, 100, true, mValue, new CecRandom(14l)) {

            std::vector<scalar>  shift = CecMath::ShiftVector(dimensions, minimumDomain(), maximumDomain(), randomizer());
            std::vector<int>  permutation = CecMath::PermutationVector(dimensions, randomizer());
            std::vector<std::vector<scalar> >  rotation = CecMath::RotationMatrix(mValue, randomizer());
            std::vector<scalar>  permutedShift (dimensions,0);
            for (int i = 0; i < dimensions; ++i) {
                permutedShift[i] = shift[permutation[i]];
            }
            for (int k = 0; k < dimensions / mValue; ++k) {
                sumElliptic.push_back(
                        new Permuted(permutation, new Grouped(k * mValue, (k + 1) * mValue,
                        new Shifted(permutedShift, k * mValue, new CecRotated(rotation, new Elliptic())))));
            }

        }

        F14::~F14() {
            for (size_t i = 0 ; i < sumElliptic.size(); ++i){
                delete sumElliptic[i];
            }
        }

        scalar F14::f(const std::vector<scalar>& x) {
            scalar result = 0;
            for (size_t i = 0 ; i < sumElliptic.size() ; ++i) {
                result += sumElliptic[i]->f(x);
            }
            return result;
        }
    }
}

