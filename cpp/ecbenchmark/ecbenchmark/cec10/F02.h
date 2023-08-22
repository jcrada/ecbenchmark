/* 
 * File:   F02.h
 * Author: jcrada
 *
 * Created on 30 May 2011, 10:53 AM
 */

#ifndef ECB_CEC10_F02_H
#define	ECB_CEC10_F02_H

#include "CecFunction.h"

namespace ecb{
    namespace cec10{
        class F02 : public CecFunction{
        private :
            Function* rastrigin;
        public :
            F02(int dimensions);
            ~F02();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* ECB_CEC10_F02_H */

