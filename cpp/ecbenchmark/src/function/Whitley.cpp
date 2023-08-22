#include "ecbenchmark/function/Whitley.h"
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

//   Whitley::Whitley() : Function() {
//      setName("Whitley");
//      setDescription("multimodal, differentiable, non-separable, symmetric");
//      setMinimization(true);
//      setDomain(-100, 100);
//   }

   scalar Whitley::f(const std::vector<scalar>& x) {
      scalar result = 0;
      for (size_t i = 0; i < x.size(); ++i) {
         scalar sum = 0;
         for (size_t j = 0; j < x.size(); ++j) {
            scalar a = 100 * (x[i] - x[j] * x[j]) * (x[i] - x[j] * x[j])
                    + (1 - x[j]) * (1 - x[j]);
            sum += a * a / 4000 - cos(a) + 1;
         }
         result += sum;
      }

      return result;
   }


}
