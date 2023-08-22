#include "ecbenchmark/function/Quartic.h"

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
   
//   Quartic::Quartic() : Function() {
//      setName("Quartic");
//      setDescription("unimodal");
//      setMinimization(true);
//      setDomain(-1.28, 1.28);
//   }

   //Notice that the original Quartic function has Gaussian noise!
   scalar Quartic::f(const std::vector<scalar>& x) {
      scalar result = 0;
      for (size_t i = 0; i < x.size(); ++i) {
         scalar square = x[i] * x[i];
         scalar square2 = square * square;

         result += (i + 1.0) * square2;
      }
      return result;
   }
}
