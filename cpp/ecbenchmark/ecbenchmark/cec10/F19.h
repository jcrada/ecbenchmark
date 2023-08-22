/* 
 * File:   F19.h
 * Author: jcrada
 *
 * Created on 30 May 2011, 1:02 PM
 */

#ifndef ECB_CEC10_F19_H
#define	ECB_CEC10_F19_H

#include "CecFunction.h"

namespace ecb{
    namespace cec10{
        class F19 : public CecFunction{
        private :
            Function* schwefel;
        public :
            F19(int dimensions, int mValue);
            ~F19();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}


#endif	/* ECB_CEC10_F19_H */

