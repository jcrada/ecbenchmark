#include "ecbenchmark/function/Michalewicz.h"
#include <math.h>


namespace ecb {

   /* Source:
    * @inproceedings{bf:michalewicz, 
        author={Bersini, H. and Dorigo, M. and Langerman, S. and Seront, G. and Gambardella, L.}, 
        booktitle={Evolutionary Computation, 1996., Proceedings of IEEE International Conference on},
    *  title={Results of the first international contest on evolutionary optimisation (1st ICEO)}, 
        year={1996}, 
        month={may}, 
        volume={}, 
        number={}, 
        pages={611 -615}}
    * 
    * Range: 0-Pi
    */

   Michalewicz::Michalewicz(scalar m) : Function(), _m(m) {
      setName("Michalewicz");
   }

   void Michalewicz::setM(scalar m) {
      this->_m = m;
   }

   scalar Michalewicz::m() const {
      return this->_m;
   }

   scalar Michalewicz::f(const std::vector<scalar>& x) {
      scalar result = 0;
      for (size_t i = 0; i < x.size(); ++i) {
         result += sin(x[i]) * pow(sin((i + 1) * x[i] * x[i] / M_PI), 2 * m());
      }
      return result;
   }


}
