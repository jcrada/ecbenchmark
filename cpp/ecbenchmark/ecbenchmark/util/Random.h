/* 
 * File:   Random.h
 * Author: jcrada
 *
 * Created on 27 May 2011, 9:09 AM
 */

#ifndef ECB_RANDOM_H
#define	ECB_RANDOM_H

#include "ecbenchmark/scalar.h"

#include <string>
#include <stdint.h>



namespace ecb {

    class Random {
    public:
        Random(){}
        virtual ~Random(){}
        
        virtual void setSeed(int64_t seed) = 0;
        virtual int64_t seed() const = 0;

        virtual scalar nextScalar() = 0;
        virtual bool nextBool() = 0;

        virtual int nextInt(int max) = 0;
        virtual int nextInt() = 0;
        
        virtual int64_t nextLong() = 0;

        virtual std::string toString() const = 0;
    };
}

#endif	/* ECB_RANDOM_H */

