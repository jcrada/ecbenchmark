/* 
 * File:   F10.h
 * Author: jcrada
 *
 * Created on 30 May 2011, 12:19 PM
 */

#ifndef ECB_CEC10_F10_H
#define	ECB_CEC10_F10_H

#include "CecFunction.h"

namespace ecb{
    namespace cec10{
        class F10 : public CecFunction{
        private :
            Function* rastrigin;
            std::vector<Function*> sumRastrigin;
        public :
            F10(int dimensions, int mValue);
            ~F10();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}


#endif	/* ECB_CEC10_F10_H */

