/* 
 * File:   F18.h
 * Author: jcrada
 *
 * Created on 30 May 2011, 1:01 PM
 */

#ifndef ECB_CEC10_F18_H
#define	ECB_CEC10_F18_H

#include "CecFunction.h"

namespace ecb{
    namespace cec10{
        class F18 : public CecFunction{
        private :
            std::vector<Function*> sumRosenbrock;
        public :
            F18(int dimensions, int mValue);
            ~F18();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}


#endif	/* ECB_CEC10_F18_H */

