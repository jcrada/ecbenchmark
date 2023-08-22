/* 
 * File:   F11.h
 * Author: jcrada
 *
 * Created on 30 May 2011, 12:19 PM
 */

#ifndef ECB_CEC10_F11_H
#define	ECB_CEC10_F11_H

#include "CecFunction.h"

namespace ecb{
    namespace cec10{
        class F11 : public CecFunction{
        private :
            Function* ackley;
            std::vector<Function*> sumAckley;
        public :
            F11(int dimensions, int mValue);
            ~F11();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}


#endif	/* ECB_CEC10_F11_H */

