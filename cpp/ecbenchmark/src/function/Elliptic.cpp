#include "ecbenchmark/function/Elliptic.h"

#include <math.h>
namespace ecb{
    
    scalar Elliptic::f(const std::vector<scalar>& x) {
        
        if (x.size() != _powCache.size()) {
            _powCache = std::vector<scalar>(x.size(), 0);
            for (size_t i = 0; i < x.size(); ++i) {
                _powCache[i] = pow(1e6, i / (x.size() - 1.0));
            }
        }
        scalar result = 0;
        for (size_t i = 0; i < x.size(); ++i) {
            result += _powCache[i] * x[i] * x[i];
        }
        return result;
    }
}
