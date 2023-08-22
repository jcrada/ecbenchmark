/* 
 * File:   HyperEllipsoid.h
 * Author: jcrada
 *
 * Created on 23 July 2011, 1:36 PM
 */

#ifndef ECB_HYPERELLIPSOID_H
#define	ECB_HYPERELLIPSOID_H

#include "ecbenchmark/Function.h"

namespace ecb {

    class HyperEllipsoid : public Function {
    public:
        scalar f(const std::vector<scalar>& x);
    };
}

#endif	/* ECB_HYPERELLIPSOID_H */

