#include "ecbenchmark/operator/Grouped.h"

namespace ecb {

    Grouped::Grouped(int from, int to, Function* f)
    : Operator(f), _fromIndex(from), _toIndex(to) {

    }

    void Grouped::setFromIndex(int from) {
        this->_fromIndex = from;
    }

    int Grouped::fromIndex() const {
        return this->_fromIndex;
    }

    void Grouped::setToIndex(int to) {
        this->_toIndex = to;
    }

    int Grouped::toIndex() const {
        return this->_toIndex;
    }

    scalar Grouped::f(const std::vector<scalar>& x) {
        std::vector<scalar> grouped_x(x.begin() + _fromIndex, x.begin() + _toIndex);
        return _innerFunction->f(grouped_x);
    }

}

