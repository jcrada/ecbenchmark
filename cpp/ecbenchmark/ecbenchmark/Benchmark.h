/* 
 * File:   Benchmark.h
 * Author: jcrada
 *
 * Created on 31 May 2011, 9:51 AM
 */

#ifndef ECB_BENCHMARK_H
#define	ECB_BENCHMARK_H

#include <string>
#include <vector>
#include "Function.h"

namespace ecb {

    class Benchmark {
    protected:
        std::string _name, _description;
        std::vector<Function*> _functions;
    public:
        Benchmark(const std::string& name = "", const std::string description = "");
        virtual ~Benchmark();

        std::string name() const;
        std::string description() const;
        Function* function(int index) const;
        std::vector<Function*> functions() const;
        int numberOfFunctions() const;

    protected:
        void add(Function* f);

    };
}


#endif	/* ECB_BENCHMARK_H */

