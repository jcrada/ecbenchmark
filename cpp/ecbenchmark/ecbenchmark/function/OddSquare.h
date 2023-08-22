/* 
 * File:   OddSquare.h
 * Author: jcrada
 *
 * Created on 28 April 2011, 12:03 PM
 */

#ifndef ECB_ODDSQUARE_H
#define	ECB_ODDSQUARE_H

#include "ecbenchmark/Function.h"

namespace ecb {

    class OddSquare : public Function {
    protected:
        void distanceToCenter(const std::vector<scalar>& x,
                scalar& euclidean, scalar& h);
    public:


        scalar f(const std::vector<scalar>& x);
    };
}

#endif	/* ECB_ODDSQUARE_H */

