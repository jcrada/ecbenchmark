#include "ecbenchmark/cec10/F19.h"
#include "ecbenchmark/cec10/CecMath.h"
#include "ecbenchmark/operator/Shifted.h"
#include "ecbenchmark/function/Schwefel1_2.h"


namespace ecb {
    namespace cec10 {

        F19::F19(int dimensions, int mValue)
        : CecFunction("F19", "Shifted Schwefel's Function", dimensions,
        -100, 100, true, mValue, new CecRandom(19l)) {

            std::vector<scalar>  shift = CecMath::ShiftVector(dimensions, minimumDomain(), maximumDomain(), randomizer());
            schwefel = new Shifted(shift, 0, new Schwefel1_2());

        }

        F19::~F19() {
            delete schwefel;
        }

        scalar F19::f(const std::vector<scalar>& x) {
            return schwefel->f(x);
        }
    }
}

