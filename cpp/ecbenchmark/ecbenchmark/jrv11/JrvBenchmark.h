/* 
 * File:   JrvBenchmark.h
 * Author: jcrada
 *
 * Created on 23 July 2011, 1:44 PM
 */

#ifndef ECB_JRVBENCHMARK_H
#define	ECB_JRVBENCHMARK_H


#include "ecbenchmark/Benchmark.h"

namespace ecb {
    namespace jrv11 {

        class JrvBenchmark : public Benchmark {
        protected:
            int _dimensions;
        public:
            JrvBenchmark(int dimensions);

            int dimensions() const;

        };
    }
}

#endif	/* ECB_JRVBENCHMARK_H */

