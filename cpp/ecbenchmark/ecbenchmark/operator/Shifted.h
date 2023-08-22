/* 
 * File:   Shifted.h
 * Author: jcrada
 *
 * Created on 26 May 2011, 12:56 PM
 */

#ifndef ECB_SHIFTED_H
#define	ECB_SHIFTED_H

#include "ecbenchmark/Operator.h"

namespace ecb {

    class Shifted : public Operator {
    protected:
        std::vector<scalar> _shift;
        int _fromIndex;
    public:
        Shifted(const std::vector<scalar>& shift = std::vector<scalar>(), int fromIndex = 0,
                Function* f = NULL);
        
        void setShift(const std::vector<scalar>& shift);
        std::vector<scalar> shift() const;
        
        void setFromIndex(int index);
        int fromIndex() const ;

        scalar f(const std::vector<scalar>& x);
    };
}

#endif	/* ECB_SHIFTED_H */

