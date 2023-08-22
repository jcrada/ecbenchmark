#include "ecbenchmark/operator/Permuted.h"

namespace ecb {

    Permuted::Permuted(const std::vector<int>& permutation, Function* f)
    : Operator(f), _permutation(permutation) {

    }
    
    void Permuted::setPermutation(const std::vector<int>& permutation){
        this->_permutation = permutation;
    }
    
    std::vector<int> Permuted::permutation() const {
        return this->_permutation;
    }
    
    scalar Permuted::f(const std::vector<scalar>& x){
        std::vector<scalar> permuted_x;
        permuted_x.reserve(x.size());
        for (size_t i = 0 ; i  < x.size() ; ++i){
            permuted_x.push_back(x[_permutation[i]]);
        }
        return _innerFunction->f(permuted_x);
    }
}
