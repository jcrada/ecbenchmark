#include "ecbenchmark/function/Schwefel1_2.h"


namespace ecb{
    
    /*
     -5.12 - 5.12
     */
    
    scalar Schwefel1_2::f(const std::vector<scalar>& x){
        scalar result = 0.0;
        for (size_t i = 0; i < x.size(); ++i) {
            scalar sum = 0.0;
            for (size_t  j = 0; j <= i; ++j) {
                sum += x[j];
            }
            result += sum * sum;
        }
        return result;
    }
}
