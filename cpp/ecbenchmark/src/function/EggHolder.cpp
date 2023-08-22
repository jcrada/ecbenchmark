#include "ecbenchmark/function/EggHolder.h"
#include <math.h>
namespace ecb {
   
   //Source: http://www.maths.uq.edu.au/CEToolBox/

   scalar EggHolder::f(const std::vector<scalar>& x) {
      scalar result = 0.0;
      for (size_t i = 0; i < x.size() - 1; i++) {
         result += (-(x[i + 1] + 47)
                 * sin(sqrt(fabs(x[i + 1] + x[i] / 2 + 47)))
                 + sin(sqrt(fabs(x[i] - (x[i + 1] + 47))))
                 *(-x[i]));
      }
      return result;
   }
}
