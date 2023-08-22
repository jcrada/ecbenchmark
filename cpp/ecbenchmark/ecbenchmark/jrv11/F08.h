/* 
 * File:   F08.h
 * Author: jcrada
 *
 * Created on 23 July 2011, 12:58 PM
 */

#ifndef JRV_F08_H
#define	JRV_F08_H

#include "ecbenchmark/AbstractFunction.h"

namespace ecb{
    namespace jrv11{
        class F08 : public AbstractFunction{
        private :
            Function* rastrigin;
        public:
            F08 (int dimensions);
            ~F08();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* JRV_F08_H */

