/* 
 * File:   Griewank.h
 * Author: jcrada
 *
 * Created on 12 November 2010, 8:47 AM
 */

#ifndef ECB_GRIEWANK_H
#define	ECB_GRIEWANK_H

#include "ecbenchmark/Function.h"

namespace ecb {

    class Griewank : public Function {
    public:        
        scalar f(const std::vector<scalar>& position);
    };

}

#endif	/* ECB_GRIEWANK_H */

