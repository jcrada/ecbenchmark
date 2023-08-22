/* 
 * File:   Neumaier3.h
 * Author: jcrada
 *
 * Created on 11 April 2011, 10:40 AM
 */

#ifndef ECB_NEUMAIER3_H
#define	ECB_NEUMAIER3_H

#include "ecbenchmark/Function.h"

namespace ecb{
    class Neumaier3 : public Function {
    public:
        scalar f(const std::vector<scalar>& position);
    };
}

#endif	/* PSO_NEUMAIER3_H */

