#include "ecbenchmark/jrv11/F03a.h"
#include "ecbenchmark/function/Schwefel2_22.h"
namespace ecb{
    namespace jrv11{
        F03a::F03a(int dimensions)
        : AbstractFunction("F03", "Schwefel2_22", dimensions,
        -5.12, 5.12, true) {
            schwefel = new Schwefel2_22;
        }

        F03a::~F03a() {
            delete schwefel;
        }

        scalar F03a::f(const std::vector<scalar>& x) {
            scalar result = 
            schwefel->f(x);
            return result ;
        }
    }
}

