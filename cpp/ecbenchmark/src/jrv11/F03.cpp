#include "ecbenchmark/jrv11/F03.h"
#include "ecbenchmark/function/Rosenbrock.h"
namespace ecb{
    namespace jrv11{
        F03::F03(int dimensions)
        : AbstractFunction("F03", "Rosenbrock", dimensions,
        -2.048, 2.048, true) {
            rosenbrock = new Rosenbrock;
        }

        F03::~F03() {
            delete rosenbrock;
        }

        scalar F03::f(const std::vector<scalar>& x) {
            return rosenbrock->f(x);
        }
    }
}
