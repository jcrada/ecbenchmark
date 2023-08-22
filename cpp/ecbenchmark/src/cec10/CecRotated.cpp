#include "ecbenchmark/cec10/CecRotated.h"
#include "ecbenchmark/cec10/CecMath.h"

namespace ecb {

    namespace cec10 {

        CecRotated::CecRotated(const std::vector<std::vector<scalar> >& rotation, Function* f)
        : Operator(f), _rotation(rotation) {

        }

        void CecRotated::setRotation(const std::vector<std::vector<scalar> >& rotation) {
            this->_rotation = rotation;
        }

        std::vector<std::vector<scalar> > CecRotated::rotation() const {
            return this->_rotation;
        }

        scalar CecRotated::f(const std::vector<scalar>& x) {
            std::vector<scalar> rotated_x = CecMath::Multiply(x, _rotation);
            return _innerFunction->f(rotated_x);
        }
    }
}
