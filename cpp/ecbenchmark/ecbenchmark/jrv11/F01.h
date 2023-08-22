/* 
 * File:   F01.h
 * Author: jcrada
 *
 * Created on 23 July 2011, 12:58 PM
 */

#ifndef JRV_F01_H
#define	JRV_F01_H

#include "ecbenchmark/AbstractFunction.h"

namespace ecb{
    namespace jrv11{
        class F01 : public AbstractFunction{
        private :
            Function* schwefel;
        public:
            F01 (int dimensions);
            ~F01();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* JRV_F01_H */

