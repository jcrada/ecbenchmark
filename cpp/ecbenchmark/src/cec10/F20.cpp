#include "ecbenchmark/cec10/F20.h"
#include "ecbenchmark/cec10/CecMath.h"
#include "ecbenchmark/operator/Shifted.h"
#include "ecbenchmark/function/Rosenbrock.h"

namespace ecb {
    namespace cec10 {

        F20::F20(int dimensions, int mValue)
        : CecFunction("F20", "Shifted Rosenbrock's Function", dimensions,
        -100, 100, true, mValue, new CecRandom(20l)) {

            std::vector<scalar>  shift = CecMath::ShiftVector(dimensions, minimumDomain(), maximumDomain() - 1, randomizer());
            rosenbrock = new Shifted(shift, 0, new Rosenbrock());

        }

        F20::~F20() {
            delete rosenbrock;
        }

        scalar F20::f(const std::vector<scalar>& x) {
            return rosenbrock->f(x);
        }
    }
}

