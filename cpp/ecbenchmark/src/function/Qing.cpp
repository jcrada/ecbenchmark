#include "ecbenchmark/function/Qing.h"

namespace ecb {

   /*
    * Source:
   @book{bf:qings-book,
        author = {Qing, Anyong},
        title = {Differential Evolution: Fundamentals and Applications in Electrical Engineering},
        year = {2009},
        publisher = {Wiley-IEEE Press}} 
    */

//   Qing::Qing() : Function() {
//      setName("Qing");
//      setDescription("unimodal, differentiable, separable, asymmetric");
//      setMinimization(true);
//      setDomain(-500, 500);
//   }

   scalar Qing::f(const std::vector<scalar>& x) {
      scalar result = 0;
      for (size_t i = 0; i < x.size(); ++i) {
         result += (x[i] * x[i] - i) * (x[i] * x[i] - i);
      }
      return result;
   }

}

