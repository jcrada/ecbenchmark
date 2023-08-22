#include "ecbenchmark/jrv11/F10.h"
#include "ecbenchmark/function/EggHolder.h"
namespace ecb{
    namespace jrv11{
        F10::F10(int dimensions)
        : AbstractFunction("F10", "EggHolder", dimensions,
        -512, 512, true) {
            eggholder = new EggHolder;
        }

        F10::~F10() {
            delete eggholder;
        }

        scalar F10::f(const std::vector<scalar>& x) {
            return eggholder->f(x);
        }
    }
}
