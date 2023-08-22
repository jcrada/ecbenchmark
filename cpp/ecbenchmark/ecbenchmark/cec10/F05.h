/* 
 * File:   F05.h
 * Author: jcrada
 *
 * Created on 30 May 2011, 12:12 PM
 */

#ifndef ECB_CEC10_F05_H
#define	ECB_CEC10_F05_H

#include "CecFunction.h"

namespace ecb {
    namespace cec10 {

        class F05 : public CecFunction {
        private:
            Function* rotatedRastrigin, *rastrigin;
        public:
            F05(int dimensions, int mValue);
            ~F05();

            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* ECB_CEC10_F05_H */

