/* 
 * File:   AbstractFunction.h
 * Author: jcrada
 *
 * Created on 26 May 2011, 10:06 AM
 */

#ifndef ECB_ABSTRACTFUNCTION_H
#define	ECB_ABSTRACTFUNCTION_H

#include "Function.h"
#include <string>
#include <math.h>
#include <stdint.h>

namespace ecb {

    class AbstractFunction : public Function {
    protected:
        std::string _description;
        int _dimensions;
        scalar _minimum, _maximum;
        bool _minimization;
    public:
        AbstractFunction(const std::string& name = "", const std::string& description = "",
                int dimensions = 1, scalar minimum = -INFINITY, scalar maximum = INFINITY,
                bool minimization = true);

        virtual ~AbstractFunction();

        virtual void setDescription(const std::string& description);
        virtual std::string description() const;

        virtual void setDimensions(int dimensions);
        virtual int dimensions() const;

        virtual void setMinimumDomain(scalar min);
        virtual scalar minimumDomain() const;

        virtual void setMaximumDomain(scalar max);
        virtual scalar maximumDomain() const;

        virtual void setDomain(scalar min, scalar max);

        virtual void setMinimization(bool min);
        virtual bool isMinimization() const;

        virtual void setMaximization(bool max);
        virtual bool isMaximization() const;


        virtual bool isBetter(scalar aValue, scalar bValue) const;
        virtual bool isBetter(const Solution* a, const Solution* b) const;


        virtual scalar worstValue() const;

        virtual std::string rString();

        virtual std::string toString() const;


    };
}

#endif	/* ECB_ABSTRACTFUNCTION_H */

