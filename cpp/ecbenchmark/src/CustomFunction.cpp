#include "ecbenchmark/CustomFunction.h"

namespace ecb {

    CustomFunction::CustomFunction(Function* f, const std::string& name,
            const std::string& description, int dimensions, scalar min_domain,
            scalar max_domain, bool minimization)
    : AbstractFunction(name, description, dimensions, min_domain, max_domain, minimization),
    _function(f) {

    }

    CustomFunction::~CustomFunction() {
        if (_function) delete _function;
    }

    void CustomFunction::setFunction(Function* f) {
        this->_function = f;
    }

    Function* CustomFunction::function() const {
        return this->_function;
    }

    scalar CustomFunction::f(const std::vector<scalar>& x) {
        return function()->f(x);
    }

}
