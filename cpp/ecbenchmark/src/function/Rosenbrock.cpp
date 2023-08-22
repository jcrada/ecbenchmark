#include "ecbenchmark/function/Rosenbrock.h"


/**
 * 
 * -2.048 - 2.048
 * 
 */
namespace ecb{
    scalar Rosenbrock::f(const std::vector<scalar>& x){
        scalar result = 0.0;
        for (size_t i = 0; i < x.size() - 1; ++i) {
            result += 100 * (x[i] * x[i] - x[i + 1]) * (x[i] * x[i] - x[i + 1]) + (x[i] - 1) * (x[i] - 1);
        }
        return result;
    }
}
