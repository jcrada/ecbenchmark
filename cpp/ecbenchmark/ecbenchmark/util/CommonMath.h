/* 
 * File:   CommonMath.h
 * Author: jcrada
 *
 * Created on 26 May 2011, 10:23 AM
 */

#ifndef ECB_COMMONMATH_H
#define	ECB_COMMONMATH_H

#include "ecbenchmark/scalar.h"
#include <vector>
#include <string>
namespace ecb {

    class CommonMath {
    private:
        CommonMath();
    public:
        static scalar Scale(scalar src_min, scalar src_max, scalar value,
                scalar target_min, scalar target_max);

        static bool IsEq(scalar x1, scalar x2, scalar epsilon = ECB_EPSILON);

        static bool IsLEq(scalar x1, scalar x2, scalar epsilon = ECB_EPSILON);
        static bool IsGEq(scalar x1, scalar x2, scalar epsilon = ECB_EPSILON);

        static bool IsLower(scalar x1, scalar x2, scalar epsilon = ECB_EPSILON);
        static bool IsGreater(scalar x1, scalar x2, scalar epsilon = ECB_EPSILON);
        
        static std::string ToString(const std::vector<scalar>&x);

        static std::vector<scalar> Multiply(const std::vector<scalar>& vector,
                const std::vector<std::vector<scalar> >& matrix);
        
        static std::string C(std::vector<scalar> list, int line_break = 50);
    };
}
#endif	/* ECB_COMMONMATH_H */

