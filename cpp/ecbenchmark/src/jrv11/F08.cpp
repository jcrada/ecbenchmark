#include "ecbenchmark/jrv11/F08.h"
#include "ecbenchmark/function/Rastrigin.h"
namespace ecb{
    namespace jrv11{
        F08::F08(int dimensions)
        : AbstractFunction("F08", "Rastrigin", dimensions,
        -5.12, 5.12, true) {
            rastrigin = new Rastrigin;
        }

        F08::~F08() {
            delete rastrigin;
        }

        scalar F08::f(const std::vector<scalar>& x) {
            return rastrigin->f(x);
        }
    }
}
