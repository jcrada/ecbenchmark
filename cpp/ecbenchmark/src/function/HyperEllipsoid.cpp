#include "ecbenchmark/function/HyperEllipsoid.h"
#include <math.h>
namespace ecb {
   
   //Source: http://www.maths.uq.edu.au/CEToolBox/

   scalar HyperEllipsoid::f(const std::vector<scalar>& x) {
      scalar result = 0.0;
      for (size_t i = 0; i < x.size(); i++) {
          result += (i + 1) * x[i] * x[i];
      }
      return result;
   }
}

