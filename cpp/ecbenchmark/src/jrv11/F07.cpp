#include "ecbenchmark/jrv11/F07.h"
#include "ecbenchmark/function/Griewank.h"
namespace ecb{
    namespace jrv11{
        F07::F07(int dimensions)
        : AbstractFunction("F07", "Griewank", dimensions,
        -600, 600, true) {
            griewank = new Griewank;
        }

        F07::~F07() {
            delete griewank;
        }

        scalar F07::f(const std::vector<scalar>& x) {
            return griewank->f(x);
        }
    }
}
