#include "ecbenchmark/jrv11/F05.h"
#include "ecbenchmark/function/HyperEllipsoid.h"
namespace ecb{
    namespace jrv11{
        F05::F05(int dimensions)
        : AbstractFunction("F05", "HyperEllipsoid", dimensions,
        -5.12, 5.12, true) {
            hyperellipsoid = new HyperEllipsoid;
        }

        F05::~F05() {
            delete hyperellipsoid;
        }

        scalar F05::f(const std::vector<scalar>& x) {
            return hyperellipsoid->f(x);
        }
    }
}
