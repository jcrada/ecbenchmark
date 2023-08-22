/* 
 * File:   Whitley.h
 * Author: jcrada
 *
 * Created on 28 April 2011, 10:56 AM
 */

#ifndef ECB_WHITLEY_H
#define	ECB_WHITLEY_H

#include "ecbenchmark/Function.h"
namespace ecb {

    class Whitley : public Function {
    public:

        scalar f(const std::vector<scalar>& x);
    };



}
#endif	/* ECB_WHITLEY_H */

