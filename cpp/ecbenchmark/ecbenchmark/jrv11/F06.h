/* 
 * File:   F06.h
 * Author: jcrada
 *
 * Created on 23 July 2011, 12:58 PM
 */

#ifndef JRV_F06_H
#define	JRV_F06_H

#include "ecbenchmark/AbstractFunction.h"

namespace ecb{
    namespace jrv11{
        class F06 : public AbstractFunction{
        private :
            Function* ackley;
        public:
            F06 (int dimensions);
            ~F06();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* JRV_F06_H */

