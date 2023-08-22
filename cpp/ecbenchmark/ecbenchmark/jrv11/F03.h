/* 
 * File:   F03.h
 * Author: jcrada
 *
 * Created on 23 July 2011, 12:58 PM
 */

#ifndef JRV_F03_H
#define	JRV_F03_H

#include "ecbenchmark/AbstractFunction.h"

namespace ecb{
    namespace jrv11{
        class F03 : public AbstractFunction{
        private :
            Function* rosenbrock;
        public:
            F03 (int dimensions);
            ~F03();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* JRV_F03_H */

