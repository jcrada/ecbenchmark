/* 
 * File:   F10.h
 * Author: jcrada
 *
 * Created on 23 July 2011, 12:58 PM
 */

#ifndef JRV_F10_H
#define	JRV_F10_H

#include "ecbenchmark/AbstractFunction.h"

namespace ecb{
    namespace jrv11{
        class F10 : public AbstractFunction{
        private :
            Function* eggholder;
        public:
            F10 (int dimensions);
            ~F10();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* JRV_F10_H */

