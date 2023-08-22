/* 
 * File:   F07.h
 * Author: jcrada
 *
 * Created on 23 July 2011, 12:58 PM
 */

#ifndef JRV_F07_H
#define	JRV_F07_H

#include "ecbenchmark/AbstractFunction.h"

namespace ecb{
    namespace jrv11{
        class F07 : public AbstractFunction{
        private :
            Function* griewank;
        public:
            F07 (int dimensions);
            ~F07();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* JRV_F07_H */

