/* 
 * File:   Michalewicz.h
 * Author: jcrada
 *
 * Created on 28 April 2011, 2:49 PM
 */

#ifndef ECB_MICHALEWICZ_H
#define	ECB_MICHALEWICZ_H

#include "ecbenchmark/Function.h"

namespace ecb {

    class Michalewicz : public Function {
    private:
        scalar _m;
    public:
        Michalewicz(scalar m = 10);

        void setM(scalar m);
        scalar m() const;

        scalar f(const std::vector<scalar>& x);
    };
}

#endif	/* ECB_MICHALEWICZ_H */

