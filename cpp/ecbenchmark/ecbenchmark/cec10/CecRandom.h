/* 
 * File:   CecRandom.h
 * Author: jcrada
 *
 * Created on 27 May 2011, 9:11 AM
 */

#ifndef ECB_CEC10_CECRANDOM_H
#define	ECB_CEC10_CECRANDOM_H

#include "ecbenchmark/util/Random.h"



namespace ecb {
    namespace cec10 {

        class CecRandom : public Random {
        private:
            static const int64_t M, A, MASK;

            int64_t _seed;
            scalar _nextNextGaussian;
            bool _hasNextNextGaussian;

        public:
            CecRandom(int64_t seed = 0);

        protected:
            int next(int bits);

        public:
            void setSeed(int64_t seed);
            int64_t seed() const;

            scalar nextScalar();
            scalar nextGaussian();
            bool nextBool();

            int nextInt(int max);
            int nextInt();

            int64_t nextLong();

            std::string toString() const;
            
            static void main();
        };
    }
}

#endif	/* ECB_CEC10_CECRANDOM_H */

