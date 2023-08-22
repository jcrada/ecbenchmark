/* 
 * File:   Elliptic.h
 * Author: jcrada
 *
 * Created on 26 May 2011, 1:00 PM
 */

#ifndef ECB_ELLIPTIC_H
#define	ECB_ELLIPTIC_H

#include "ecbenchmark/Function.h"

namespace ecb{
    class Elliptic : public Function{
    protected:
        std::vector<scalar> _powCache;
    public :
        scalar f(const std::vector<scalar>& x) ;
    };
}


#endif	/* ECB_ELLIPTIC_H */

