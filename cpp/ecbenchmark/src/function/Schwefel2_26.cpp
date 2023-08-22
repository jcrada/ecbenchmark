#include "ecbenchmark/function/Schwefel2_26.h"
#include <math.h>
namespace ecb {

   /* Source:
   @book{bf:qings-book,
        author = {Qing, Anyong},
        title = {Differential Evolution: Fundamentals and Applications in Electrical Engineering},
        year = {2009},
        publisher = {Wiley-IEEE Press}} 
    */

//   Schwefel2_26::Schwefel2_26() : Function() {
//      setName("Schwefel2_26");
//      setDescription("multimodal, non-differentiable, separable, symmetric");
//      setMinimization(true);
//      setDomain(-500, 500);
//   }

   scalar Schwefel2_26::f(const std::vector<scalar>& x) {
      scalar sum = 0;
      for (size_t i = 0; i < x.size(); ++i) {
         sum += x[i] * sin(sqrt(fabs(x[i])));
      }
      return -(1.0 / x.size()) * sum;
   }
}
