/* 
 * File:   Schaffers.h
 * Author: jcrada
 *
 * Created on 12 November 2010, 10:30 AM
 */

#ifndef ECB_SCHAFFERS_H
#define	ECB_SCHAFFERS_H

#include "ecbenchmark/Function.h"

namespace ecb{
    class Schaffers : public Function{
    public:

        scalar f(const std::vector<scalar>& position);
    };
}


#endif	/* ECB_SCHAFFERS_H */

