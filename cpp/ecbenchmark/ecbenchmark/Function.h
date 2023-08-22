/* 
 * File:   Function.h
 * Author: jcrada
 *
 * Created on 26 May 2011, 10:02 AM
 */

#ifndef ECB_FUNCTION_H
#define	ECB_FUNCTION_H

#include "scalar.h"
#include <vector>
#include "Solution.h"
namespace ecb {

    class Function {
    private:
        std::string _name;
    public:

        Function(const std::string& name = "") : _name(name) { }

        virtual ~Function() { };

        void setName(const std::string& name) {
            this->_name = name;
        }

        std::string name() const {
            return this->_name;
        }

        virtual scalar f(const std::vector<scalar>& x) = 0;

        virtual void evaluate(Solution* solution) { 
            solution->setObjectiveValue(f(solution->getVector()));
        }
    };
}

#endif	/* ECB_FUNCTION_H */

