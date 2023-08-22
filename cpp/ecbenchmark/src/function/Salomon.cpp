#include "ecbenchmark/function/Salomon.h"
#include <math.h>

namespace ecb {

   /* Source:
    * @incollection{springer-dif.evo,
     Author = {Price, Kenneth and Storn, Rainer M. and Lampinen, Jouni A.},
     title={{Appendix}},
     booktitle = {Differential Evolution: A Practical Approach to Global Optimization},
     series={Natural Computing Series},
     editor={Rozenberg, G. and Bäck, Th. and Eiben, A. E. and Kok, J. N. and Spaink, H. P.},
     publisher = {Springer-Verlag Berlin Heidelberg},
     pages = {513-533},
     year = {2005}
     }
    */

//   Salomon::Salomon() : Function() {
//      setName("Salomon");
//      setDescription("multimodal, differentiable, non-separable, symmetric");
//      setMinimization(true);
//      setDomain(-100, 100);
//   }

   scalar Salomon::f(const std::vector<scalar>& x) {
      scalar sum = 0.0;
      for (size_t i = 0; i < x.size(); ++i) {
         sum += x[i] * x[i];
      }
      return -cos(2.0 * M_PI * sqrt(sum)) + 0.1 * sqrt(sum) + 1.0;
   }
}
