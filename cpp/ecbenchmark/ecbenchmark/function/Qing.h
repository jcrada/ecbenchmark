/* 
 * File:   Qing.h
 * Author: jcrada
 *
 * Created on 28 April 2011, 5:02 PM
 */

#ifndef ECB_QING_H
#define	ECB_QING_H

#include "ecbenchmark/Function.h"


namespace ecb{
    class Qing : public Function{
    public :
        scalar f(const std::vector<scalar>& x);
    };
    
}

#endif	/* PSO_QING_H */

