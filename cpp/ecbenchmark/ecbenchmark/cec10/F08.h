/* 
 * File:   F08.h
 * Author: jcrada
 *
 * Created on 30 May 2011, 12:16 PM
 */

#ifndef ECB_CEC10_F08_H
#define	ECB_CEC10_F08_H

#include "CecFunction.h"

namespace ecb{
    namespace cec10{
        class F08 : public CecFunction{
        private :
            Function* rosenbrock, *sphere;
        public :
            F08(int dimensions, int mValue);
            ~F08();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* ECB_CEC10_F08_H */

