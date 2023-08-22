#include "ecbenchmark/jrv11/F06.h"
#include "ecbenchmark/function/Ackley.h"
namespace ecb{
    namespace jrv11{
        F06::F06(int dimensions)
        : AbstractFunction("F06", "Ackley", dimensions,
        -32.768, 32.768, true) {
            ackley = new Ackley;
        }

        F06::~F06() {
            delete ackley;
        }

        scalar F06::f(const std::vector<scalar>& x) {
            return ackley->f(x);
        }
    }
}
