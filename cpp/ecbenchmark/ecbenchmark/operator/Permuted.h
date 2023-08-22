/* 
 * File:   Permuted.h
 * Author: jcrada
 *
 * Created on 26 May 2011, 12:45 PM
 */

#ifndef ECB_PERMUTED_H
#define	ECB_PERMUTED_H

#include "ecbenchmark/Operator.h"

namespace ecb {

    class Permuted : public Operator {
    protected:
        std::vector<int> _permutation;

    public:
        Permuted(const std::vector<int>& permutation = std::vector<int>(),
                Function* f = NULL);
        
        void setPermutation(const std::vector<int>& permutation);
        std::vector<int> permutation() const;

        scalar f(const std::vector<scalar>& x);

    };
}

#endif	/* ECB_PERMUTED_H */

