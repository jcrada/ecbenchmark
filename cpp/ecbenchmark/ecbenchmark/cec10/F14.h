/* 
 * File:   F14.h
 * Author: jcrada
 *
 * Created on 30 May 2011, 12:23 PM
 */

#ifndef ECB_CEC10_F14_H
#define	ECB_CEC10_F14_H

#include "CecFunction.h"

namespace ecb{
    namespace cec10{
        class F14 : public CecFunction{
        private :
            std::vector<Function*> sumElliptic;
        public :
            F14(int dimensions, int mValue);
            ~F14();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}


#endif	/* ECB_CEC10_F14_H */

