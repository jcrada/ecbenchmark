#include "ecbenchmark/cec10/F02.h"
#include "ecbenchmark/cec10/CecMath.h"
#include "ecbenchmark/operator/Shifted.h"
#include "ecbenchmark/function/Rastrigin.h"

namespace ecb {
    namespace cec10 {

        F02::F02(int dimensions)
        : CecFunction("F02", "Shifted Rastrigin's Function", dimensions,
        -5, 5, true, 0, new CecRandom(2l)) {
            std::vector<scalar> shift = CecMath::ShiftVector(dimensions, minimumDomain(),
                    maximumDomain(), randomizer());
            rastrigin = new Shifted(shift, 0, new Rastrigin());
        }

        F02::~F02() {
            delete rastrigin;
        }

        scalar F02::f(const std::vector<scalar>& x) {
            return rastrigin->f(x);
        }
    }
}
