#include "ecbenchmark/Solution.h"
#include <sstream>
namespace ecb {

    Solution::Solution(int dimensions, scalar objectiveValue) :
    _vector(std::vector<scalar>(dimensions)),
    _objectiveValue(objectiveValue) { }

    Solution::Solution(const Solution& copy) {
        this->_vector = copy.getVector();
        this->_objectiveValue = copy.getObjectiveValue();
    }

    Solution::~Solution() { }

    void Solution::setVector(const std::vector<scalar>& vector) {
        this->_vector = vector;
    }

    const std::vector<scalar>& Solution::getVector() const {
        return this->_vector;
    }

    void Solution::add(scalar value) {
        this->_vector.push_back(value);
    }

    void Solution::set(int index, scalar value) {
        this->_vector[index] = value;
    }

    scalar Solution::get(int index) const {
        return this->_vector.at(index);
    }

    void Solution::setNumberOfDimensions(int dimensions) {
        this->_vector = std::vector<scalar > (dimensions);
    }

    int Solution::numberOfDimensions() const {
        return this->_vector.size();
    }

    void Solution::setObjectiveValue(scalar value) {
        this->_objectiveValue = value;
    }

    scalar Solution::getObjectiveValue() const {
        return this->_objectiveValue;
    }

    void Solution::copyFrom(const Solution* solution) {
        this->_objectiveValue = solution->getObjectiveValue();
        this->_vector = solution->getVector();
    }

    void Solution::copyInto(Solution* solution) const {
        solution->setObjectiveValue(this->_objectiveValue);
        solution->setVector(this->_vector);
    }

    Solution* Solution::clone() const {
        return new Solution(*this);
    }

    std::string Solution::toString() const {
        std::stringstream ss;
        ss << "Solution[objective=" << getObjectiveValue() << "; vector={";
        for (size_t i = 0; i < this->_vector.size(); ++i) {
            ss << i;
            if (i < this->_vector.size() - 1) {
                ss << ", ";
            }
        }
        ss << "}]";
        return ss.str();
    }

}
