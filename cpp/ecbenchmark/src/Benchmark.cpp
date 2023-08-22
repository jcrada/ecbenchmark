#include "ecbenchmark/Benchmark.h"

namespace ecb {

    Benchmark::Benchmark(const std::string& name, const std::string description)
    : _name(name), _description(description) {

    }

    Benchmark::~Benchmark() {
        for (size_t i = 0; i < _functions.size(); ++i) {
            delete _functions[i];
        }
    }

    std::string Benchmark::name() const {
        return this->_name;
    }

    std::string Benchmark::description() const {
        return this->_description;
    }

    Function* Benchmark::function(int index) const {
        return this->_functions[index];
    }

    std::vector<Function*> Benchmark::functions() const{
        return this->_functions;
    }
    
    int Benchmark::numberOfFunctions() const {
        return this->_functions.size();
    }

    void Benchmark::add(Function* f) {
        this->_functions.push_back(f);
    }


}
