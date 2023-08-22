/* 
 * File:   Grouped.h
 * Author: jcrada
 *
 * Created on 26 May 2011, 10:29 AM
 */

#ifndef ECB_GROUPED_H
#define	ECB_GROUPED_H

#include "ecbenchmark/Operator.h"
#include <stdint.h>

namespace ecb {

    class Grouped : public Operator {
    private:
        int _fromIndex, _toIndex;

    public:
        Grouped(int from = 0, int to = 0, Function* f = NULL);
        
        void setFromIndex(int from);
        int fromIndex() const;

        void setToIndex(int to);
        int toIndex() const;
        
        scalar f(const std::vector<scalar>& x);
        
    };
}

#endif	/* ECB_GROUPED_H */

