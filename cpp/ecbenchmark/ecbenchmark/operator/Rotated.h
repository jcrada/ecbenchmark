/* 
 * File:   Rotated.h
 * Author: jcrada
 *
 * Created on 26 May 2011, 12:49 PM
 */

#ifndef ECB_ROTATED_H
#define	ECB_ROTATED_H

#include "ecbenchmark/Operator.h"

namespace ecb {

    class Rotated : public Operator {
    protected:
        std::vector<std::vector<scalar> > _rotation;
    public:
        Rotated(const std::vector<std::vector<scalar> >& rotation = std::vector<std::vector<scalar> >(), Function* = NULL);
        
        void setRotation(const std::vector<std::vector<scalar> >& rotation);
        std::vector<std::vector<scalar> > rotation() const;
        
        scalar f(const std::vector<scalar>& x);

    };
}

#endif	/* ECB_ROTATED_H */

