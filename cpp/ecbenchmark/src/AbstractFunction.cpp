#include "ecbenchmark/AbstractFunction.h"
#include "ecbenchmark/util/CommonMath.h"

#include <sstream>


namespace ecb {

    AbstractFunction::AbstractFunction(const std::string& name,
            const std::string& description, int dimensions,
            scalar minimum, scalar maximum, bool minimization)
    : Function(name), _description(description), _dimensions(dimensions),
    _minimum(minimum), _maximum(maximum), _minimization(minimization) { }

    AbstractFunction::~AbstractFunction() { }

    void AbstractFunction::setDescription(const std::string& description) {
        this->_description = description;
    }

    std::string AbstractFunction::description() const {
        return this->_description;
    }

    void AbstractFunction::setDimensions(int dimensions) {
        this->_dimensions = dimensions;
    }

    int AbstractFunction::dimensions() const {
        return this->_dimensions;
    }

    void AbstractFunction::setMinimumDomain(scalar min) {
        this->_minimum = min;
    }

    scalar AbstractFunction::minimumDomain() const {
        return this->_minimum;
    }

    void AbstractFunction::setMaximumDomain(scalar max) {
        this->_maximum = max;
    }

    scalar AbstractFunction::maximumDomain() const {
        return this->_maximum;
    }

    void AbstractFunction::setDomain(scalar min, scalar max) {
        setMinimumDomain(min);
        setMaximumDomain(max);
    }

    void AbstractFunction::setMinimization(bool min) {
        this->_minimization = min;
    }

    bool AbstractFunction::isMinimization() const {
        return this->_minimization;
    }

    void AbstractFunction::setMaximization(bool max) {
        this->_minimization = !max;
    }

    bool AbstractFunction::isMaximization() const {
        return !this->_minimization;
    }

    //returns positive if a is better than b, negative otherwise

    bool AbstractFunction::isBetter(scalar aValue, scalar bValue) const {
        return isMinimization() ? aValue < bValue : aValue > bValue;
        //        if (isMinimization()) {
        //            return (a - b > 0.0) ? -1 : 1;
        //        }else{
        //            return (a - b < 0.0) ? -1 : 1;
        //        }
    }

    bool AbstractFunction::isBetter(const Solution* a, const Solution* b) const {
        if (a != NULL && b != NULL) {
            return isBetter(a->getObjectiveValue(), b->getObjectiveValue());
        } else {
            throw "NULL pointer exception";
        }
    }

    scalar AbstractFunction::worstValue() const {
        return isMinimization() ? INFINITY : -INFINITY;
    }

    std::string AbstractFunction::toString() const {
        std::stringstream ss;
        ss << name() << "[dimensions= " << dimensions() << "; "
                << "(" << minimumDomain() << ", " << maximumDomain() << "); "
                << "minimization=" << isMinimization() << "]";
        return ss.str();

    }

    std::string AbstractFunction::rString() {
        std::stringstream ss;
        ss << "cols = colorRampPalette(c('black','white'));\n";
        std::vector<scalar> x_y;
        int samples = 100;
        for (int i = 1; i <= samples; ++i) {
            x_y.push_back(CommonMath::Scale(1.0, samples, i, minimumDomain(), maximumDomain()));
        }



        ss << name() + "_x = " + name() + "_y = " + CommonMath::C(x_y) + ";\n";

        std::vector<scalar> z;
        for (size_t i = 0; i < x_y.size(); ++i) {
            for (size_t j = 0; j < x_y.size(); ++j) {
                std::vector<scalar> input;
                input.push_back(x_y[i]);
                input.push_back(x_y[j]);
                z.push_back(f(input));
            }
        }

        ss << name() << "_z = matrix(" << CommonMath::C(z) << ", " << x_y.size() << ", " << x_y.size() << ", "
                << "byrow=TRUE);\n\n";
        ss << "image(" << name() << "_x, " << name() << "_y, " << name() << "_z,"
                << "col=cols(255),axes=FALSE,xlab=NA,ylab=NA,asp=1,main='" << name() << "');\n";

        return ss.str();
    }




}
