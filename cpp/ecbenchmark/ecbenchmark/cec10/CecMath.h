/* 
 * File:   CecMath.h
 * Author: jcrada
 *
 * Created on 27 May 2011, 2:09 PM
 */

#ifndef ECB_CEC10_CECMATH_H
#define	ECB_CEC10_CECMATH_H

#include "ecbenchmark/scalar.h"
#include "CecRandom.h"

#include <vector>
#include <stdint.h>

namespace ecb {
    namespace cec10 {

        class CecMath {
        private:
            CecMath();
        public:
            static std::vector<scalar> ShiftVector(int size, scalar min, scalar max, CecRandom* random);
            
            static std::vector<int> PermutationVector(int dimensions, CecRandom* random);

            static std::vector<std::vector<scalar> > RotationMatrix(int mValue, CecRandom* random);
            
            static std::vector<scalar> Multiply(const std::vector<scalar>& vector,
                    const std::vector<std::vector<scalar> >& matrix);

            

        };
    }
}

#endif	/* ECB_CEC10_CECMATH_H */

