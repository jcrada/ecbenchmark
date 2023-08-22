#include "ecbenchmark/function/Rastrigin.h"

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
    * 
    * Range: -5.12 - 5.12
    */
    scalar Rastrigin::f(const std::vector<scalar>& x) {
        scalar result = 0.0;
        for (size_t  i = 0; i < x.size(); ++i) {
            result += x[i] * x[i] - 10 * cos(2 * M_PI * x[i]) + 10;
        }
        return result;
    }
}
