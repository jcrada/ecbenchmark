#include "ecbenchmark/operator/Rotated.h"
#include "ecbenchmark/util/CommonMath.h"

namespace ecb {

    Rotated::Rotated(const std::vector<std::vector<scalar> >& rotation, Function* f)
    : Operator(f), _rotation(rotation) {

    }

    void Rotated::setRotation(const std::vector<std::vector<scalar> >& rotation) {
        this->_rotation = rotation;
    }

    std::vector<std::vector<scalar> > Rotated::rotation() const {
        return this->_rotation;
    }

    scalar Rotated::f(const std::vector<scalar>& x) {
        std::vector<scalar> rotated_x = CommonMath::Multiply(x, _rotation);
        return _innerFunction->f(rotated_x);
    }
}
