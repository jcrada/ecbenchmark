/* 
 * File:   Sphere.h
 * Author: jcrada
 *
 * Created on 26 May 2011, 1:02 PM
 */

#ifndef ECB_SPHERE_H
#define	ECB_SPHERE_H

#include "ecbenchmark/Function.h"

namespace ecb{
    class Sphere : public Function{
    public :
        scalar f(const std::vector<scalar>& x)  ;
    };
}


#endif	/* ECB_SPHERE_H */

