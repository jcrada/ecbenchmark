/* 
 * File:   F04.h
 * Author: jcrada
 *
 * Created on 30 May 2011, 12:11 PM
 */

#ifndef ECB_CEC10_F04_H
#define	ECB_CEC10_F04_H

#include "CecFunction.h"

namespace ecb{
    namespace cec10{
        class F04 : public CecFunction{
        private :
            Function* rotatedElliptic, *elliptic;
        public :
            F04(int dimensions, int mValue);
            ~F04();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* ECB_CEC10_F04_H */

