/* 
 * File:   Solution.h
 * Author: jcrada
 *
 * Created on 7 April 2012, 1:59 PM
 */

#ifndef ECB_SOLUTION_H
#define	ECB_SOLUTION_H

#include <math.h>
#include <vector>
#include "ecbenchmark/scalar.h"

namespace ecb {

    class Solution {
    protected:
        std::vector<scalar> _vector;
        scalar _objectiveValue;

    public:
        Solution(int dimensions = 0, scalar objectiveValue = NAN);
        Solution(const Solution& copy);
        virtual ~Solution();

        virtual void setVector(const std::vector<scalar>& vector);
        virtual const std::vector<scalar>& getVector() const;

        virtual void add(scalar value);
        virtual void set(int index, scalar value);
        virtual scalar get(int index) const;

        virtual void setNumberOfDimensions(int dimensions);
        virtual int numberOfDimensions() const;

        virtual void setObjectiveValue(scalar value);
        virtual scalar getObjectiveValue() const;
        
        virtual void copyInto(Solution* solution) const;
        virtual void copyFrom(const Solution* solution);
        virtual Solution* clone() const;
        
        virtual std::string toString() const;
    };
}

#endif	/* PSO_SOLUTION_H */

