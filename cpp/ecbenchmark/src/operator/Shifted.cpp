#include "ecbenchmark/operator/Shifted.h"

namespace ecb {

    Shifted::Shifted(const std::vector<scalar>& shift, int fromIndex,  Function* f)
    : Operator(f), _shift(shift), _fromIndex(fromIndex){

    }
    

    void Shifted::setShift(const std::vector<scalar>& shift) {
        this->_shift = shift;
    }

    std::vector<scalar> Shifted::shift() const {
        return this->_shift;
    }

    void Shifted::setFromIndex(int index) {
        this->_fromIndex = index;
    }

    int Shifted::fromIndex() const {
        return this->_fromIndex;
    }

    scalar Shifted::f(const std::vector<scalar>& x) {
        std::vector<scalar> shifted_x;
        shifted_x.resize(x.size());
        int shiftIndex = _fromIndex;
        for (size_t i = 0; i < x.size(); ++i) {
            shifted_x[i] = x[i] - _shift[shiftIndex++];
        }
        return _innerFunction->f(shifted_x);
    }
}
