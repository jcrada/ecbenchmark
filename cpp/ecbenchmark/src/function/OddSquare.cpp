#include "ecbenchmark/function/OddSquare.h"
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

//   OddSquare::OddSquare() : Function() {
//      setName("OddSquare");
//      setDescription("multimodal");
//      setMinimization(true);
//      setDomain(-5 * M_PI, 5 * M_PI);
//   }

   void OddSquare::distanceToCenter(const std::vector<scalar>& x,
           scalar& euclidean, scalar& h) {
      static const scalar CENTER[10] = {1, 1.3, 0.8, -0.4, -1.3, 1.6, -0.2, -0.6, 0.5, 1.4};
      scalar max = -INFINITY;
      euclidean = 0;
      for (size_t i = 0; i < x.size(); ++i) {
         scalar diff = x[i] - CENTER[i % 10];
         euclidean += diff * diff;

         if (max < fabs(diff)) max = fabs(diff);
      }

      euclidean = sqrt(euclidean);
      //      h = sqrt(x.size() * max * max); //according to code...
      h = x.size() * max * max; //according to reference
   }

   scalar OddSquare::f(const std::vector<scalar>& x) {
      scalar d, h;
      distanceToCenter(x, d, h);
      return -exp(-d / (2 * M_PI)) * cos(M_PI * d) * (1 + (0.02 * h) / (d + 0.01));
   }

}
