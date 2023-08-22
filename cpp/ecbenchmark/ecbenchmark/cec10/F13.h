/* 
 * File:   F13.h
 * Author: jcrada
 *
 * Created on 30 May 2011, 12:22 PM
 */

#ifndef ECB_CEC10_F13_H
#define	ECB_CEC10_F13_H

#include "CecFunction.h"

namespace ecb{
    namespace cec10{
        class F13 : public CecFunction{
        private :
            Function* sphere;
            std::vector<Function*> sumRosenbrock;
        public :
            F13(int dimensions, int mValue);
            ~F13();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* ECB_CEC10_F13_H */

