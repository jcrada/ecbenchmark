/* 
 * File:   F07.h
 * Author: jcrada
 *
 * Created on 30 May 2011, 12:15 PM
 */

#ifndef ECB_CEC10_F07_H
#define	ECB_CEC10_F07_H

#include "CecFunction.h"

namespace ecb{
    namespace cec10{
        class F07 : public CecFunction{
        private :
            Function* schweffel, *sphere;
        public :
            F07(int dimensions, int mValue);
            ~F07();
            
            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* ECB_CEC10_F07_H */

