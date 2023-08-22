#include "ecbenchmark/function/Step2.h"
#include <math.h>
namespace ecb {

   /* Source:
   @book{bf:qings-book,
        author = {Qing, Anyong},
        title = {Differential Evolution: Fundamentals and Applications in Electrical Engineering},
        year = {2009},
        publisher = {Wiley-IEEE Press}} 
    */

//   Step2::Step2() : Function() {
//      setName("Step2");
//      setDescription("unimodal, discontinuous, non-differentiable, separable, symmetric");
//      setMinimization(true);
//      setDomain(-500, 500);
//   }
//   
   scalar Step2::f(const std::vector<scalar>& x){
      scalar result = 0;
      for (size_t i = 0 ; i < x.size(); ++i){
         result += floor(x[i] + 0.5) * floor(x[i] + 0.5);
      }
      return result;
   }
}
