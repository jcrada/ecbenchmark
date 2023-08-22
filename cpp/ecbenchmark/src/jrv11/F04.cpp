#include "ecbenchmark/jrv11/F04.h"
#include "ecbenchmark/function/Sphere.h"
namespace ecb{
    namespace jrv11{
        F04::F04(int dimensions)
        : AbstractFunction("F04", "Sphere", dimensions,
        -5.12, 5.12, true) {
            sphere = new Sphere;
        }

        F04::~F04() {
            delete sphere;
        }

        scalar F04::f(const std::vector<scalar>& x) {
            return sphere->f(x);
        }
    }
}
