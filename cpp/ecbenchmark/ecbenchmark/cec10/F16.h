/* 
 * File:   F16.h
 * Author: jcrada
 *
 * Created on 30 May 2011, 1:00 PM
 */

#ifndef ECB_CEC10_F16_H
#define	ECB_CEC10_F16_H

#include "CecFunction.h"

namespace ecb{
    namespace cec10{
        class F16 : public CecFunction{
        private :
            std::vector<Function*> sumAckley;
        public :
            F16(int dimensions, int mValue);
            ~F16();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}


#endif	/* ECB_CEC10_F16_H */

