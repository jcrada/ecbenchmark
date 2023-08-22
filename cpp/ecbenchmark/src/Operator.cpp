#include "ecbenchmark/Operator.h"

namespace ecb {

    Operator::Operator(Function* f) : _innerFunction(f) {

    }

    Operator::~Operator() {
        if (_innerFunction) delete _innerFunction;
    }

    void Operator::setInnerFunction(Function* f) {
        this->_innerFunction = f;
    }

    Function* Operator::innerFunction() const {
        return this->_innerFunction;
    }
    

}
