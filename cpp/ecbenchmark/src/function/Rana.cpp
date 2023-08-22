#include "ecbenchmark/function/Rana.h"
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

//   Rana::Rana() : Function() {
//      setName("Rana");
//      setDescription("multimodal");
//      setMinimization(true);
//      setDomain(-512, 512);
//   }

   scalar Rana::f(const std::vector<scalar>& x) {
      scalar result = 0, alpha, beta;
      
      for (size_t i = 0 ; i < x.size() - 1; ++i){
         alpha = sqrt(fabs(x[i + 1] + 1 - x[i]));
         beta = sqrt(fabs(x[i + 1] + 1 + x[i]));
         result += x[i] * sin(alpha) * cos(beta) + x[i + 1] * cos(alpha) * sin(beta);
      }
      
      return result ;
   }


}
