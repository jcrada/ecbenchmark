#include "ecbenchmark/jrv11/F01.h"
#include "ecbenchmark/function/Schwefel1_2.h"
namespace ecb{
    namespace jrv11{
        F01::F01(int dimensions)
        : AbstractFunction("F01", "Quadric", dimensions,
        -100, 100, true) {
            schwefel = new Schwefel1_2;
        }

        F01::~F01() {
            delete schwefel;
        }

        scalar F01::f(const std::vector<scalar>& x) {
            return schwefel->f(x);
        }
    }
}
