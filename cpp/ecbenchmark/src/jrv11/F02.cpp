#include "ecbenchmark/jrv11/F02.h"
#include "ecbenchmark/function/Quartic.h"

namespace ecb{
    namespace jrv11{
        F02::F02(int dimensions)
        : AbstractFunction("F02", "Quartic", dimensions,
        -1.28, 1.28, true) {
            quartic = new Quartic;
        }

        F02::~F02() {
            delete quartic;
        }

        scalar F02::f(const std::vector<scalar>& x) {
            return quartic->f(x);
        }
    }
}
