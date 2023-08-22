/* 
 * File:   Schwefel.h
 * Author: jcrada
 *
 * Created on 12 November 2010, 11:04 AM
 */

#ifndef ECB_SCHWEFEL2_26_H
#define	ECB_SCHWEFEL2_26_H

#include "ecbenchmark/Function.h"

namespace ecb{
    class Schwefel2_26 : public Function{
    public:

        scalar f(const std::vector<scalar>& position);
    };
}


#endif	/* ECB_SCHWEFEL2_26_H */

