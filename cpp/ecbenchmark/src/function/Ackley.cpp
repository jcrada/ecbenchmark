#include "ecbenchmark/function/Ackley.h"

#include <math.h>
namespace ecb {

    scalar Ackley::f(const std::vector<scalar>& x)  {
        scalar sumA = 0.0, sumB = 0.0;
        for (size_t  i = 0; i < x.size(); ++i) {
            sumA += x[i] * x[i];
            sumB += cos(2 * M_PI * x[i]);
        }
        return -20 * exp(-0.2 * sqrt((1.0 / x.size()) * sumA))
                - exp((1.0 / x.size()) * sumB)
                + 20 + M_E;
    }
}
