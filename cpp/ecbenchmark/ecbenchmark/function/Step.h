/* 
 * File:   Step.h
 * Author: jcrada
 *
 * Created on 28 April 2011, 3:52 PM
 */

#ifndef ECB_STEP_H
#define	ECB_STEP_H

#include "ecbenchmark/Function.h"

namespace ecb {

    class Step : public Function {
    public:

        scalar f(const std::vector<scalar>& x);
    };
}

#endif	/* ECB_STEP_H */

