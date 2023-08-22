#include "ecbenchmark/function/Step.h"
#include <math.h>
namespace ecb {

   /* Source: (with Random Gaussian noise)
    * @phdthesis{bf:dejong,
        author = {De Jong, Kenneth Alan},
        title = {An analysis of the behavior of a class of genetic adaptive systems.},
        year = {1975},
        publisher = {University of Michigan},
        school={},
        address = {Ann Arbor, MI, USA}}   
    */


//   Step::Step() : Function() {
//      setName("Step");
//      setDescription("unimodal");
//      setMinimization(true);
//      setDomain(-5.12, 5.12);
//   }

   scalar Step::f(const std::vector<scalar>& x) {
      scalar result = 0;
      for (size_t i = 0; i < x.size(); ++i) {
         result += floor(x[i]);
      }
      return result;
   }

}
