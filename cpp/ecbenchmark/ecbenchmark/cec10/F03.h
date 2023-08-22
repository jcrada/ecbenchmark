/* 
 * File:   F03.h
 * Author: jcrada
 *
 * Created on 30 May 2011, 12:04 PM
 */

#ifndef ECB_CEC10_F03_H
#define	ECB_CEC10_F03_H

#include "CecFunction.h"

namespace ecb{
    namespace cec10{
        class F03 : public CecFunction{
        private:
            Function* ackley;
        public :
            F03(int dimensions);
            ~F03();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* ECB_CEC10_F03_H */

