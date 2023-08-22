/* 
 * File:   F12.h
 * Author: jcrada
 *
 * Created on 30 May 2011, 12:22 PM
 */

#ifndef ECB_CEC10_F12_H
#define	ECB_CEC10_F12_H

#include "CecFunction.h"

namespace ecb {
    namespace cec10 {

        class F12 : public CecFunction {
        private:
            Function* sphere;
            std::vector<Function*> sumSchwefel;
        public:
            F12(int dimensions, int mValue);
            ~F12();

            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* ECB_CEC10_F12_H */

