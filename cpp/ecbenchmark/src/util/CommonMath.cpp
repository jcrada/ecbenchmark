#include "ecbenchmark/util/CommonMath.h"

#include <sstream>
#include <math.h>


namespace ecb {

    CommonMath::CommonMath() {
    }

    scalar CommonMath::Scale(scalar src_min, scalar src_max, scalar value,
            scalar target_min, scalar target_max) {
        return value / ((src_max - src_min) / (target_max - target_min)) + target_min;
    }

    bool CommonMath::IsEq(scalar x1, scalar x2, scalar epsilon) {
        //http://stackoverflow.com/questions/17333/most-effective-way-for-float-and-scalar-comparison
        //        return fabs(x1 - x2) < std::numeric_limits<scalar>::epsilon() ;
        return fabs(x1 - x2) < epsilon;
    }

    bool CommonMath::IsLEq(scalar x1, scalar x2, scalar epsilon) {
        return x1 < x2 || IsEq(x1, x2, epsilon);
    }

    bool CommonMath::IsGEq(scalar x1, scalar x2, scalar epsilon) {
        return x1 > x2 || IsEq(x1, x2, epsilon);
    }

    bool CommonMath::IsLower(scalar x1, scalar x2, scalar epsilon) {
        return x1 < x2 && !IsEq(x1, x2, epsilon);
    }

    bool CommonMath::IsGreater(scalar x1, scalar x2, scalar epsilon) {
        return x1 > x2 && !IsEq(x1, x2, epsilon);
    }

    std::vector<scalar> CommonMath::Multiply(const std::vector<scalar>& vector,
            const std::vector<std::vector<scalar> >& matrix) {
        std::vector<scalar> result;
        result.resize(vector.size());
        for (size_t row = 0; row < matrix.size(); ++row) {
            for (size_t j = 0; j < vector.size(); ++j) {
                result[row] += vector[j] * matrix[row][j];
            }
        }
        return result;
    }

    std::string CommonMath::ToString(const std::vector<scalar>& x) {
        std::stringstream ss;
        ss << "[";
        for (size_t i = 0; i < x.size(); ++i) {
            ss << x[i];
            if (i < x.size() - 1) ss << " ";
        }
        ss << "]";
        return ss.str();
    }
    
    std::string CommonMath::C(std::vector<scalar> list, int line_break){
        std::stringstream c;
        c << "c(";
        for (size_t i = 0; i < list.size(); ++i) {
            c << list[i];
            if (i < list.size() - 1) c << ", ";
            if ((i+1) % line_break == 0) c << "\n";
        }
        c << ")";
        return c.str();
    }
}
