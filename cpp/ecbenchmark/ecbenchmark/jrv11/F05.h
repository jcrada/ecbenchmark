/* 
 * File:   F05.h
 * Author: jcrada
 *
 * Created on 23 July 2011, 12:58 PM
 */

#ifndef JRV_F05_H
#define	JRV_F05_H

#include "ecbenchmark/AbstractFunction.h"

namespace ecb{
    namespace jrv11{
        class F05 : public AbstractFunction{
        private :
            Function* hyperellipsoid;
        public:
            F05 (int dimensions);
            ~F05();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* JRV_F05_H */

