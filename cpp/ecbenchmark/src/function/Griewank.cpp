#include "ecbenchmark/function/Griewank.h"
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
//    Griewank::Griewank() : Function() {
//        setName("Griewank");
//        setDescription("multimodal, differentiable, non-separable, symmetric");
//        setMinimization(true);
//        setDomain(-600,600);
//    }

    scalar Griewank::f(const std::vector<scalar>& x) {
        scalar sum = 0.0, prod = 1.0;
        for (size_t i = 0; i < x.size(); ++i) {
            sum += x[i] * x[i];
            prod *= cos(x[i] / sqrt(i + 1.0));
        }
        return 1.0 + (1.0/4000.0) * sum  - prod;
    }
    
}
