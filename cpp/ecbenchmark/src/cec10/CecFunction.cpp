#include "ecbenchmark/cec10/CecFunction.h"

namespace ecb {
    namespace cec10 {

        CecFunction::CecFunction(const std::string& name, const std::string& description,
                int dimensions, scalar minimum, scalar maximum, bool minimization,
                int mValue, CecRandom* randomizer)
        : AbstractFunction(name, description, dimensions, minimum, maximum, minimization),
        _mValue(mValue), _randomizer(randomizer) {

        }

        CecFunction::~CecFunction() {
            delete _randomizer;
        }

        void CecFunction::setMValue(int m) {
            this->_mValue = m;
        }

        int CecFunction::mValue() const {
            return this->_mValue;
        }

        void CecFunction::setRandomizer(CecRandom* randomizer) {
            this->_randomizer = randomizer;
        }

        CecRandom* CecFunction::randomizer() const {
            return this->_randomizer;
        }
    }

}
