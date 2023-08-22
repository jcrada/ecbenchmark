/* 
 * File:   Schwefel2_22.h
 * Author: jcrada
 *
 * Created on 23 July 2011, 1:19 PM
 */

#ifndef ECB_SCHWEFEL2_22_H
#define	ECB_SCHWEFEL2_22_H

#include "ecbenchmark/Function.h"

namespace ecb{
    
    class Schwefel2_22 : public Function{
    public :
        scalar f(const std::vector<scalar>& x);
    };
    
}

#endif	/* ECB_SCHWEFEL2_22_H */

