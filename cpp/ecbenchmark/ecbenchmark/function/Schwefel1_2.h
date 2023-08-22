/* 
 * File:   Schwefel1_2.h
 * Author: jcrada
 *
 * Created on 26 May 2011, 1:01 PM
 */

#ifndef ECB_SCHWEFEL1_2_H
#define	ECB_SCHWEFEL1_2_H

#include "ecbenchmark/Function.h"

namespace ecb{
    class Schwefel1_2 : public Function{
    public :
        scalar f(const std::vector<scalar>& x)  ;
    };
}


#endif	/* ECB_SCHWEFEL1_2_H */
