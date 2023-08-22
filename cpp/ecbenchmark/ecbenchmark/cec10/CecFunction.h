/* 
 * File:   CecFunction.h
 * Author: jcrada
 *
 * Created on 27 May 2011, 9:04 AM
 */

#ifndef ECB_CEC10_CECFUNCTION_H
#define	ECB_CEC10_CECFUNCTION_H

#include "ecbenchmark/AbstractFunction.h"
#include "CecRandom.h"

namespace ecb {
    namespace cec10 {

        class CecFunction : public AbstractFunction {
        protected:
            int _mValue;
            CecRandom* _randomizer;
        public:
            CecFunction(const std::string& name = "", const std::string& description = "",
                    int dimensions = 1000, scalar minimum = -INFINITY,
                    scalar maximum = INFINITY, bool minimization = true,
                    int mValue = 50, CecRandom* randomizer = NULL);
            virtual ~CecFunction();

            void setMValue(int m);
            int mValue() const;

            void setRandomizer(CecRandom* randomizer);
            CecRandom* randomizer() const;


        };
    }
}

#endif	/* ECB_CEC10_FUNCTION_H */

