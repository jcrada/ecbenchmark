/* 
 * File:   Operator.h
 * Author: jcrada
 *
 * Created on 26 May 2011, 10:19 AM
 */

#ifndef ECB_OPERATOR_H
#define	ECB_OPERATOR_H

#include "Function.h"

namespace ecb {

    class Operator : public Function {
    protected:
        Function* _innerFunction;
    public:
        Operator(Function* f = NULL);
        virtual ~Operator();

        void setInnerFunction(Function* f);
        Function* innerFunction() const;

    };
}

#endif	/* ECB_OPERATOR_H */

