/* 
 * File:   Salomon.h
 * Author: jcrada
 *
 * Created on 12 November 2010, 8:50 AM
 */

#ifndef ECB_SALOMON_H
#define	ECB_SALOMON_H

#include "ecbenchmark/Function.h"

namespace ecb {

    class Salomon : public Function {
    public:
        scalar f(const std::vector<scalar>& position);
    };

}


#endif	/* ECB_SALOMON_H */

