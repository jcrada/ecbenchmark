/* 
 * File:   Step2.h
 * Author: jcrada
 *
 * Created on 28 April 2011, 5:23 PM
 */

#ifndef ECB_STEP2_H
#define	ECB_STEP2_H

#include "ecbenchmark/Function.h"

namespace ecb{
    class  Step2 : public Function{
    public :
        
        scalar f(const std::vector<scalar>& x);
    };
}

#endif	/* ECB_STEP2_H */

