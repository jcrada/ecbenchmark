/* 
 * File:   Ackley.h
 * Author: jcrada
 *
 * Created on 26 May 2011, 12:58 PM
 */

#ifndef ECB_ACKLEY_H
#define	ECB_ACKLEY_H

#include "ecbenchmark/Function.h"

namespace ecb{
    class Ackley : public Function{
    public :
        scalar f(const std::vector<scalar>& x)  ;
    };
}

#endif	/* ECB_ACKLEY_H */

