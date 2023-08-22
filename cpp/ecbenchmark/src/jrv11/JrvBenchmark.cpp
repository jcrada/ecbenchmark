
#include "ecbenchmark/jrv11/JrvBenchmark.h"
#include "ecbenchmark/jrv11/F01.h"
#include "ecbenchmark/jrv11/F02.h"
#include "ecbenchmark/jrv11/F03a.h"
#include "ecbenchmark/jrv11/F04.h"
#include "ecbenchmark/jrv11/F05.h"
#include "ecbenchmark/jrv11/F06.h"
#include "ecbenchmark/jrv11/F07.h"
#include "ecbenchmark/jrv11/F08.h"
#include "ecbenchmark/jrv11/F09.h"
#include "ecbenchmark/jrv11/F10.h"

namespace ecb {
    namespace jrv11 {

        JrvBenchmark::JrvBenchmark(int dimensions)
        : Benchmark("jrv11", std::string ("J. Rada-Vilela, M. Zhang, W. Seah ")
        + std::string("A performance study on synchronous and asynchronous updates in particle swarm optimization")
        + std::string("Gecco'11. Dublin, Ireland. 2011")),
        _dimensions(dimensions){
            add(new F01(dimensions));
            add(new F02(dimensions));
            add(new F03a(dimensions));
            add(new F04(dimensions));
            add(new F05(dimensions));
            add(new F06(dimensions));
            add(new F07(dimensions));
            add(new F08(dimensions));
            add(new F09(dimensions));
            add(new F10(dimensions));
        }

        int JrvBenchmark::dimensions() const {
            return this->_dimensions;
        }
    }
}

