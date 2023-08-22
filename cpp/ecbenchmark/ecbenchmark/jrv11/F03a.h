/* 
 * File:   F03a.h
 * Author: jcrada
 *
 * Created on 25 July 2011, 12:24 PM
 */

#ifndef JRV_F03A_H
#define	JRV_F03A_H
#include "ecbenchmark/AbstractFunction.h"

namespace ecb{
    namespace jrv11{
        class F03a : public AbstractFunction{
        private :
            Function* schwefel;
        public:
            F03a (int dimensions);
            ~F03a();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* JRV_F03A_H */

