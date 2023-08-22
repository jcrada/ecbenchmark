/* 
 * File:   F09.h
 * Author: jcrada
 *
 * Created on 23 July 2011, 12:58 PM
 */

#ifndef JRV_F09_H
#define	JRV_F09_H

#include "ecbenchmark/AbstractFunction.h"

namespace ecb{
    namespace jrv11{
        class F09 : public AbstractFunction{
        private :
            Function* salomon;
        public:
            F09 (int dimensions);
            ~F09();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* JRV_F09_H */

