/* 
 * File:   Rana.h
 * Author: jcrada
 *
 * Created on 28 April 2011, 2:25 PM
 */

#ifndef ECB_RANA_H
#define	ECB_RANA_H

#include "ecbenchmark/Function.h"

namespace ecb{
    class Rana : public Function{
    public :
       
        scalar f(const std::vector<scalar>& x);
    };
}

#endif	/* ECB_RANA_H */

