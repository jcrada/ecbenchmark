/* 
 * File:   F06.h
 * Author: jcrada
 *
 * Created on 30 May 2011, 12:14 PM
 */

#ifndef ECB_CEC10_F06_H
#define	ECB_CEC10_F06_H

#include "CecFunction.h"


namespace ecb{
    namespace cec10{
        class F06 : public CecFunction{
        private :
            Function* rotatedAckley, *ackley;
        public :
            F06(int dimensions, int mValue);
            ~F06();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* ECB_CEC10_F06_H */

