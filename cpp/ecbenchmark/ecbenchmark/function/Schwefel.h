/* 
 * File:   Schwefel.h
 * Author: jcrada
 *
 * Created on 26 May 2011, 1:01 PM
 */

#ifndef ECB_SCHWEFEL_H
#define	ECB_SCHWEFEL_H

#include "ecbenchmark/Function.h"

namespace ecb{
    class Schwefel : public Function{
    public :
        scalar f(const std::vector<scalar>& x)  ;
    };
}


#endif	/* ECB_SCHWEFEL_H */

