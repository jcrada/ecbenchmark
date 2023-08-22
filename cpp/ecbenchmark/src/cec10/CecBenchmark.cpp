#include "ecbenchmark/cec10/CecBenchmark.h"
#include "ecbenchmark/cec10/F01.h"
#include "ecbenchmark/cec10/F02.h"
#include "ecbenchmark/cec10/F03.h"
#include "ecbenchmark/cec10/F04.h"
#include "ecbenchmark/cec10/F05.h"
#include "ecbenchmark/cec10/F06.h"
#include "ecbenchmark/cec10/F07.h"
#include "ecbenchmark/cec10/F08.h"
#include "ecbenchmark/cec10/F09.h"
#include "ecbenchmark/cec10/F10.h"
#include "ecbenchmark/cec10/F11.h"
#include "ecbenchmark/cec10/F12.h"
#include "ecbenchmark/cec10/F13.h"
#include "ecbenchmark/cec10/F14.h"
#include "ecbenchmark/cec10/F15.h"
#include "ecbenchmark/cec10/F16.h"
#include "ecbenchmark/cec10/F17.h"
#include "ecbenchmark/cec10/F18.h"
#include "ecbenchmark/cec10/F19.h"
#include "ecbenchmark/cec10/F20.h"

namespace ecb {
    namespace cec10 {

        CecBenchmark::CecBenchmark(int dimensions, int mValue)
        : Benchmark("cec10", std::string ("K. Tang, Xiaodong Li, P. N. Suganthan, Z. Yang and T. Weise, ")
        + std::string("Benchmark Functions for the CEC'2010 Special Session and Competition on Large Scale Global Optimization, ")
        + std::string("Technical Report, Nature Inspired Computation and Applications Laboratory, USTC, China,")
        + std::string("http://nical.ustc.edu.cn/cec10ss.php")),
        _dimensions(dimensions), _mValue(mValue) {
            add(new F01(dimensions));
            add(new F02(dimensions));
            add(new F03(dimensions));
            add(new F04(dimensions, mValue));
            add(new F05(dimensions, mValue));
            add(new F06(dimensions, mValue));
            add(new F07(dimensions, mValue));
            add(new F08(dimensions, mValue));
            add(new F09(dimensions, mValue));
            add(new F10(dimensions, mValue));
            add(new F11(dimensions, mValue));
            add(new F12(dimensions, mValue));
            add(new F13(dimensions, mValue));
            add(new F14(dimensions, mValue));
            add(new F15(dimensions, mValue));
            add(new F16(dimensions, mValue));
            add(new F17(dimensions, mValue));
            add(new F18(dimensions, mValue));
            add(new F19(dimensions, mValue));
            add(new F20(dimensions, mValue));
        }

        int CecBenchmark::dimensions() const {
            return this->_dimensions;
        }

        int CecBenchmark::mValue() const {
            return this->_mValue;
        }

    }
}
