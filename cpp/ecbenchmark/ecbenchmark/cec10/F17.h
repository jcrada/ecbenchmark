/* 
 * File:   F17.h
 * Author: jcrada
 *
 * Created on 30 May 2011, 1:00 PM
 */

#ifndef ECB_CEC10_F17_H
#define	ECB_CEC10_F17_H

#include "CecFunction.h"

namespace ecb{
    namespace cec10{
        class F17 : public CecFunction{
        private :
            std::vector<Function*> sumSchweffel;
        public :
            F17(int dimensions, int mValue);
            ~F17();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* ECB_CEC10_F17_H */

