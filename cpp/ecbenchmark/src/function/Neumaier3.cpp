#include "ecbenchmark/function/Neumaier3.h"


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

//   Neumaier3::Neumaier3(int dimensions) : Function() {
//      setName("Neumaier3");
//      setDescription("unimodal");
//      setMinimization(true);
//      setDomain(-dimensions * dimensions, dimensions * dimensions);
//   }

   scalar Neumaier3::f(const std::vector<scalar>& x) {
      scalar sum_a, sum_b = 0;
      for (size_t i = 0; i < x.size(); ++i) {
         sum_a += (x[i] - 1) * (x[i] - 1);
         if (i > 0) {
            sum_b += x[i - 1] * x[i];
         }
      }
      return sum_a - sum_b;
   }

}
