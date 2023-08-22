/* 
 * File:   EggHolder.h
 * Author: jcrada
 *
 * Created on 14 December 2010, 1:10 PM
 */

#ifndef ECB_EGGHOLDER_H
#define	ECB_EGGHOLDER_H

#include "ecbenchmark/Function.h"

namespace ecb{

    class EggHolder : public Function{
    public:
        scalar f(const std::vector<scalar>& position) ;
    };
}


#endif	/* ECB_EGGHOLDER_H */

