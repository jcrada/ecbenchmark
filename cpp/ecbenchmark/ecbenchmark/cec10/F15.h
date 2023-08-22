/* 
 * File:   F15.h
 * Author: jcrada
 *
 * Created on 30 May 2011, 12:59 PM
 */

#ifndef ECB_CEC10_F15_H
#define	ECB_CEC10_F15_H

#include "CecFunction.h"

namespace ecb{
    namespace cec10{
        class F15 : public CecFunction{
        private :
            std::vector<Function*> sumRastrigin;
        public :
            F15(int dimensions, int mValue);
            ~F15();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* ECB_CEC10_F15_H */

