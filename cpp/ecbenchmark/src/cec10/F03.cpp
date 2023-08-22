#include "ecbenchmark/cec10/F03.h"
#include "ecbenchmark/cec10/CecMath.h"
#include "ecbenchmark/operator/Shifted.h"
#include "ecbenchmark/function/Ackley.h"

namespace ecb {
    namespace cec10 {

        F03::F03(int dimensions)
        : CecFunction("F03", "Shifted Ackley's Function", dimensions,
        -32, 32, true, 0, new CecRandom(3l)) {
            std::vector<scalar> shift = CecMath::ShiftVector(dimensions, minimumDomain(), maximumDomain(), randomizer());
            ackley = new Shifted(shift, 0, new Ackley());
        }

        F03::~F03() {
            delete ackley;
        }

        scalar F03::f(const std::vector<scalar>& x) {
            return ackley->f(x);
        }
    }
}

