#include "ecbenchmark/ECBenchmarkFunctions.h"

#include "ecbenchmark/CustomFunction.h"
#include "ecbenchmark/function/Ackley.h"
#include "ecbenchmark/function/EggHolder.h"
#include "ecbenchmark/function/Elliptic.h"
#include "ecbenchmark/function/Griewank.h"
#include "ecbenchmark/function/HyperEllipsoid.h"
#include "ecbenchmark/function/Michalewicz.h"
#include "ecbenchmark/function/Neumaier3.h"
#include "ecbenchmark/function/OddSquare.h"
#include "ecbenchmark/function/Qing.h"
#include "ecbenchmark/function/Quartic.h"
#include "ecbenchmark/function/Rana.h"
#include "ecbenchmark/function/Rastrigin.h"
#include "ecbenchmark/function/Rosenbrock.h"
#include "ecbenchmark/function/Salomon.h"
#include "ecbenchmark/function/Schaffers.h"
#include "ecbenchmark/function/Schwefel2_22.h"
#include "ecbenchmark/function/Schwefel2_26.h"
#include "ecbenchmark/function/Schwefel1_2.h"
#include "ecbenchmark/function/Sphere.h"
#include "ecbenchmark/function/Step2.h"
#include "ecbenchmark/function/Step.h"
#include "ecbenchmark/function/Whitley.h"


#include <math.h>
#include <sstream>

namespace ecb {

    ECBenchmarkFunctions::ECBenchmarkFunctions(int dim)
    : Benchmark("ECBenchmark", "ECBenchmarkFunctions") {

        add(new CustomFunction(new Elliptic, "Elliptic", "unimodal", dim, -5.12, 5.12, true));
        add(new CustomFunction(new HyperEllipsoid, "HyperEllipsoid", "unimodal", dim, -5.12, 5.12, true));
        add(new CustomFunction(new Neumaier3, "Neumaier3", "unimodal", dim, -dim*dim, dim*dim, true));
        add(new CustomFunction(new Qing, "Qing", "unimodal, differentiable, separable, asymmetric",
                dim, -500, 500, true));
        add(new CustomFunction(new Quartic, "Quartic", "unimodal", dim, -1.28, 1.28, true));
        add(new CustomFunction(new Rosenbrock, "Rosenbrock", "unimodal for n < 4", dim, -2.048, 2.048, true));
        add(new CustomFunction(new Schwefel1_2, "Schwefel1_2", "unimodal a.k.a. Quadric", dim, -100, 100, true));
        add(new CustomFunction(new Schwefel2_22, "Schwefel2_22", "unimodal, non-differentiable, separable, symmetric",
                dim, -100, 100, true));
        add(new CustomFunction(new Sphere, "Sphere", "unimodal", dim, -5.12, 5.12, true));
        add(new CustomFunction(new Step2, "Step2", "unimodal, discontinuous, non-differentiable, separable, symmetric",
                dim, -500, 500, true));
        add(new CustomFunction(new Step, "Step", "unimodal", dim, -5.12, 5.12, true));

        add(new CustomFunction(new Ackley, "Ackley", "multimodal", dim, -32.768, 32.768, true));
        add(new CustomFunction(new EggHolder, "EggHolder", "multimodal", dim, -512, 512, true));
        add(new CustomFunction(new Griewank, "Griewank", "multimodal, differentiable, non-separable, symmetric",
                dim, -600, 600, true));
        add(new CustomFunction(new Michalewicz, "Michalewicz", "multimodal", dim, 0, M_PI, true));
        add(new CustomFunction(new OddSquare, "OddSquare", "multimodal", dim, -5 * M_PI, 5 * M_PI, true));
        add(new CustomFunction(new Rana, "Rana", "multimodal", dim, -512, 512, true));
        add(new CustomFunction(new Rastrigin, "Rastrigin", "multimodal", dim, -5.12, 5.12, true));
        add(new CustomFunction(new Salomon, "Salomon", "multimodal, differentiable, non-separable, symmetric",
                dim, -100, 100, true));
        add(new CustomFunction(new Schaffers, "Schaffers", "multimodal", dim, -100, 100, true));
        add(new CustomFunction(new Schwefel2_26, "Schwefel2_26", "multimodal, non-differentiable, separable, symmetric",
                dim, -500, 500, true));
        
        add(new CustomFunction(new Whitley, "Whitley", "multimodal, differentiable, non-separable, symmetric",
                dim, -100, 100, true));
        
        for (int i = 0 ; i < numberOfFunctions(); ++i){
            CustomFunction* f = dynamic_cast<CustomFunction*>(function(i));
            f->setDescription(f->name() + ": " + f->description());

            std::stringstream f_name;
            f_name << "F";
            if (i + 1 < 10) f_name << "0";
            f_name << (i+1);
            f->setName(f_name.str());
        }
        
        //WARNING: Qing and Quartic are similar
        //WARNING: Sphere and Step2 are similar
        //WARNING: Whitley and Schaffers are weird
        
    }



}
