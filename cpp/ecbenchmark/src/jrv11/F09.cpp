#include "ecbenchmark/jrv11/F09.h"
#include "ecbenchmark/function/Salomon.h"
namespace ecb{
    namespace jrv11{
        F09::F09(int dimensions)
        : AbstractFunction("F09", "Salomon", dimensions,
        -600, 600, true) {
            salomon = new Salomon;
        }

        F09::~F09() {
            delete salomon;
        }

        scalar F09::f(const std::vector<scalar>& x) {
            return salomon->f(x);
        }
    }
}
