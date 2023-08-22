#include "ecbenchmark/cec10/F01.h"
#include "ecbenchmark/cec10/CecMath.h"
#include "ecbenchmark/operator/Shifted.h"
#include "ecbenchmark/function/Elliptic.h"
#include "ecbenchmark/util/CommonMath.h"
namespace ecb {
    namespace cec10 {

        F01::F01(int dimensions)
        : CecFunction("F01", "Shifted Elliptic Function", dimensions,
        -100, 100, true, 0, new CecRandom(1l)) {
            std::vector<scalar> shift = CecMath::ShiftVector(dimensions, minimumDomain(), maximumDomain(), randomizer());
            elliptic = new Shifted(shift, 0, new Elliptic());
        }

        F01::~F01() {
            delete elliptic;
        }

        scalar F01::f(const std::vector<scalar>& x) {
            return elliptic->f(x);
        }
    }
}
