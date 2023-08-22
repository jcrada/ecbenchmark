#include "ecbenchmark/cec10/CecRandom.h"
#include "ecbenchmark/util/CommonMath.h"


#include <limits>
#include <math.h>
#include <sstream>

namespace ecb {
    namespace cec10 {
        const int64_t CecRandom::M = 0x5DEECE66DLL;
        const int64_t CecRandom::A = 0xBLL;
        const int64_t CecRandom::MASK = (1LL << 48) - 1;

        CecRandom::CecRandom(int64_t seed)
        : _seed(seed), _nextNextGaussian(0), _hasNextNextGaussian(false) {

        }

        void CecRandom::setSeed(int64_t seed) {
            this->_seed = seed;
        }

        int64_t CecRandom::seed() const {
            return this->_seed;
        }

        int CecRandom::next(int bits) {
            int64_t s = (((this->_seed * M) + A) & MASK);
            _seed = s;
            return (int) ((int64_t) s >> (48 - bits));
        }

        scalar CecRandom::nextScalar() {
#ifdef ECB_USE_DOUBLE_PRECISION
            return (((int64_t) (next(26)) << 27) + next(27))
                    / (double) (1LL << 53);
#else
            return next(24) / ((float) (1 << 24));
#endif
        }

        scalar CecRandom::nextGaussian() {

            double multiplier, v1, v2, s;

            if (_hasNextNextGaussian) {
                _hasNextNextGaussian = false;
                return _nextNextGaussian;
            }
            do {
                v1 = ((2 * nextScalar()) - 1);
                v2 = ((2 * nextScalar()) - 1);
                s = ((v1 * v1) + (v2 * v2));
            } while (CommonMath::IsGEq(s, 1.0) || CommonMath::IsEq(s, 0.0));

            multiplier = sqrt(-2.0 * log(s) / s);

            _nextNextGaussian = (v2 * multiplier);
            _hasNextNextGaussian = true;

            return (v1 * multiplier);
        }

        bool CecRandom::nextBool() {
            return next(1) != 0;
        }

        int CecRandom::nextInt(int max) {
            int bits, val;

            if ((max & (-max)) == max) {// i.e., n is a power of 2
                return (int) ((max * (long) next(31)) >> 31);
            }

            do {
                bits = next(31);
                val = bits % max;
            } while (bits - val + (max - 1) < 0);

            return val;
        }

        int CecRandom::nextInt() {
            return next(32);
        }

        int64_t CecRandom::nextLong() {
            return ((int64_t) (next(32)) << 32) +next(32);
        }

        std::string CecRandom::toString() const {
            std::stringstream ss;
            ss << "cec10::CecRandom[seed=" << seed() << "]";
            return ss.str();
        }

        void CecRandom::main() {
            CecRandom x(1l);
            CM_LOG(x.next(32));
            CM_LOG("A: " << A);
            CM_LOG("M: " << M);
            CM_LOG("Mask: " << MASK);

        }
    }

}
