/* 
 * File:   Rosenbrock.h
 * Author: jcrada
 *
 * Created on 26 May 2011, 1:01 PM
 */

#ifndef ECB_ROSENBROCK_H
#define	ECB_ROSENBROCK_H

#include "ecbenchmark/Function.h"

namespace ecb{
    class Rosenbrock : public Function{
    public :
        scalar f(const std::vector<scalar>& x)  ;
    };
}


#endif	/* ECB_ROSENBROCK_H */

