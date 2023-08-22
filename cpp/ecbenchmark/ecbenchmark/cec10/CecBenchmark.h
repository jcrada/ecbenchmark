/* 
 * File:   CecBenchmark.h
 * Author: jcrada
 *
 * Created on 31 May 2011, 10:05 AM
 */

#ifndef ECB_CECBENCHMARK_H
#define	ECB_CECBENCHMARK_H

#include "ecbenchmark/Benchmark.h"

namespace ecb{
    namespace cec10{
        class CecBenchmark : public Benchmark{
        protected :
            int _dimensions, _mValue;
        public:
            CecBenchmark(int dimensions, int mValue);
            
            int dimensions() const ;
            int mValue() const ;
            
        };
    }
}

#endif	/* ECB_CECBENCHMARK_H */

