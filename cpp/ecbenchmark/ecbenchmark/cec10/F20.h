/* 
 * File:   F20.h
 * Author: jcrada
 *
 * Created on 30 May 2011, 1:03 PM
 */

#ifndef ECB_CEC10_F20_H
#define	ECB_CEC10_F20_H

#include "CecFunction.h"

namespace ecb{
    namespace cec10{
        class F20 : public CecFunction{
        private :
            Function* rosenbrock;
        public :
            F20(int dimensions, int mValue);
            ~F20();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}


#endif	/* ECB_CEC10_F20_H */

