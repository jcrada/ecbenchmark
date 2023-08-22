/* 
 * File:   Rastrigin.h
 * Author: jcrada
 *
 * Created on 26 May 2011, 1:00 PM
 */

#ifndef ECB_RASTRIGIN_H
#define	ECB_RASTRIGIN_H

#include "ecbenchmark/Function.h"

namespace ecb{
    class Rastrigin : public Function{
    public :
        scalar f(const std::vector<scalar>& x)  ;
    };
}


#endif	/* ECB_RASTRIGIN_H */

