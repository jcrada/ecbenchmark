#include "ecbenchmark/function/Schaffers.h"
#include <math.h>
namespace ecb {

//   Schaffers::Schaffers() : Function() {
//      setName("Schaffers");
//      setDescription("multimodal");
//      setMinimization(true);
//      setDomain(-100, 100);
//   }

   scalar Schaffers::f(const std::vector<scalar>& x) {
      scalar sum = 0;
      scalar xi, xj, sin_2, sq_val;
      
      for (size_t i = 0; i < x.size() - 1; i++) {
         xi = x[i];
         xj = x[i + 1];
         sin_2 = sin(sqrt((100.0 * (xi * xi)) + (xj * xj)));
         sin_2 *= sin_2;

         sq_val = (xi * xi) - (2.0 * xi * xj) + (xj * xj);
         sq_val *= sq_val;

         sum += 0.5 + ((sin_2 - 0.5) / (1.0 + (0.001 * sq_val)));
      }

      return sum;
   }
}
