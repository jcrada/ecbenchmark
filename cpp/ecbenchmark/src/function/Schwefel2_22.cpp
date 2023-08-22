#include "ecbenchmark/function/Schwefel2_22.h"
#include <math.h>
namespace ecb{
   /* Source:
   @book{bf:qings-book,
        author = {Qing, Anyong},
        title = {Differential Evolution: Fundamentals and Applications in Electrical Engineering},
        year = {2009},
        publisher = {Wiley-IEEE Press}} 
    */
//   Schwefel2_22::Schwefel2_22() : Function(){
//      setName("Schwefel2_22");
//      setDescription("unimodal, non-differentiable, separable, symmetric");
//      setMinimization(true);
//      setDomain(-100,100);
//   }
   
   scalar Schwefel2_22::f(const std::vector<scalar>& x){
       
      scalar sum = 0, prod = 1;
      for (size_t i = 0 ; i < x.size() ; ++i){
         sum += fabs(x[i]);
         prod *= fabs(x[i]);
      }
      
      return sum + prod ;
   }
   
}
