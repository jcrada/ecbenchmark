/* 
 * File:   scalar.h
 * Author: jcrada
 *
 * Created on 26 May 2011, 10:03 AM
 */

#ifndef ECB_SCALAR_H
#define	ECB_SCALAR_H

#include <iostream>

namespace ecb {
#ifdef ECB_USE_DOUBLE_PRECISION
    typedef double scalar;
#define ECB_EPSILON 1e-15
#else 
    typedef float scalar;
#define ECB_EPSILON 1e-6
#endif


#define ECB_AT __FILE__, __LINE__, __FUNCTION__

#define CM_LOG_PREFIX __FILE__ << " [" << __LINE__ << "]:"
#define CM_LOG(message) std::cout << CM_LOG_PREFIX << message << std::endl
#define CM_LOGW(message) std::cout << CM_LOG_PREFIX << "WARNING: " << message << std::endl
#define CM_LOGP(message) std::cout << message << std::endl


}
#endif	/* ECB_SCALAR_H */

