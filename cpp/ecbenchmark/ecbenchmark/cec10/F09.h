/* 
 * File:   F09.h
 * Author: jcrada
 *
 * Created on 30 May 2011, 12:18 PM
 */

#ifndef ECB_CEC10_F09_H
#define	ECB_CEC10_F09_H

#include "CecFunction.h"

namespace ecb{
    namespace cec10{
        class F09 : public CecFunction{
        private :
            Function* elliptic;
            std::vector<Function*> sumElliptic;
        public :
            F09(int dimensions, int mValue);
            ~F09();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* ECB_CEC10_F09_H */

