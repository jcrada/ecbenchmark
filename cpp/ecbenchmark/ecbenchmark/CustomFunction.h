/* 
 * File:   CustomFunction.h
 * Author: jcrada
 *
 * Created on 24 July 2011, 1:33 PM
 */

#ifndef ECB_CUSTOMFUNCTION_H
#define	ECB_CUSTOMFUNCTION_H

#include "AbstractFunction.h"

namespace ecb{
    class CustomFunction : public AbstractFunction{
    private :
        Function* _function;
        
    public :

        CustomFunction(Function* f = NULL, const std::string& name = "CustomFunction",
                const std::string& description = "Customizable Function",
                int dimensions = 0, scalar min_domain = -INFINITY,
                scalar max_domain = INFINITY, bool minimization = true);
        ~CustomFunction();
        
        void setFunction(Function* f);
        Function* function() const ;
        
        scalar f(const std::vector<scalar>& x);
        
    };
}

#endif	/* ECB_CUSTOMFUNCTION_H */

