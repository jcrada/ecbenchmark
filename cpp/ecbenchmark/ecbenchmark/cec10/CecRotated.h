/* 
 * File:   CecRotated.h
 * Author: jcrada
 *
 * Created on 30 May 2011, 11:06 AM
 */

#ifndef ECB_CEC10_CECROTATED_H
#define	ECB_CEC10_CECROTATED_H

#include "ecbenchmark/Operator.h"
#include <vector>

namespace ecb {
    namespace cec10 {

        class CecRotated : public Operator {
        private:
            std::vector<std::vector<scalar> > _rotation;
        public:
            CecRotated(const std::vector<std::vector<scalar> >& rotation = std::vector<std::vector<scalar> >(),
                    Function* = NULL);

            void setRotation(const std::vector<std::vector<scalar> >& rotation);
            std::vector<std::vector<scalar> > rotation() const;

            scalar f(const std::vector<scalar>& x);
        };
    }
}

#endif	/* ECB_CEC10_ROTATED_H */

