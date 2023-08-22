/* 
 * File:   MultiObjectiveFunction.h
 * Author: jcrada
 *
 * Created on 16 February 2012, 11:31 AM
 */

#ifndef ECB_MULTIOBJECTIVEFUNCTION_H
#define	ECB_MULTIOBJECTIVEFUNCTION_H

#include "scalar.h"
#include <vector>
#include "Solution.h"
namespace ecb {

    class MultiObjectiveFunction {
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

        virtual std::vector<scalar> f(const std::vector<scalar>& x) = 0;

        virtual void f(Solution* solution) { }
    };
}


#endif	/* ECB_MULTIOBJECTIVEFUNCTION_H */

