/* 
 * File:   Quartic.h
 * Author: jcrada
 *
 * Created on 12 November 2010, 8:49 AM
 */

#ifndef ECB_QUARTIC_H
#define	ECB_QUARTIC_H

#include "ecbenchmark/Function.h"

namespace ecb {

    class Quartic : public Function {
    public:
        scalar f(const std::vector<scalar>& position);
    };

}


#endif	/* ECB_QUARTIC_H */

