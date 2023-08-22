/* 
 * File:   F04.h
 * Author: jcrada
 *
 * Created on 23 July 2011, 12:58 PM
 */

#ifndef JRV_F04_H
#define	JRV_F04_H

#include "ecbenchmark/AbstractFunction.h"

namespace ecb{
    namespace jrv11{
        class F04 : public AbstractFunction{
        private :
            Function* sphere;
        public:
            F04 (int dimensions);
            ~F04();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* JRV_F04_H */

