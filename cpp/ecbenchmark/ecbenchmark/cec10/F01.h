/* 
 * File:   F01.h
 * Author: jcrada
 *
 * Created on 27 May 2011, 3:00 PM
 */

#ifndef ECB_CEC10_F01_H
#define	ECB_CEC10_F01_H

#include "CecFunction.h"

namespace ecb{
    namespace cec10{
        class F01 : public CecFunction{
        private :
            Function* elliptic;
        public:
            F01 (int dimensions);
            ~F01();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* ECB_CEC10_F01_H */

