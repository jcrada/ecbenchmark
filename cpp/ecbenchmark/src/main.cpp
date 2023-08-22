/* 
 * File:   mai2.cpp
 * Author: jcrada
 *
 * Created on 26 May 2011, 5:40 PM
 */

#include "ecbenchmark/Headers.h"
#include <iostream>
#include <vector>
#include <string>
#include <fstream>
/*
 * 
 */

using namespace ecb;

int main(int argc, char** argv) {
    //    int dimensions = 1000;
    //    int mValue = 50;
    //
    //    cec10::CecRandom random(99l);
    //
    //    std::vector<scalar> x(dimensions, 0);
    //
    //    for (int i = 0; i < dimensions; ++i) {
    //        x[i] = random.nextScalar();
    //    }
    //
    //    ecb::ECBenchmarkFunctions all(2);
    //    for (int i = 0; i < all.numberOfFunctions(); ++i) {
    //        AbstractFunction* f = dynamic_cast<AbstractFunction*> (all.function(i));
    //        CM_LOGP(f->rString());
    //    }
    //
    //    cec10::CecBenchmark b(dimensions, mValue);
    //
    //    for (int i = 0; i < b.numberOfFunctions(); ++i) {
    ////        CM_LOG("F" << (i + 1) << "(x) = " << b.function(i)->f(x));
    //    }
#define DIMENSIONS 2
    std::vector<AbstractFunction*> problems;
    problems.push_back(new ecb::CustomFunction(
            new ecb::Ackley, "F01", "Ackley",
            DIMENSIONS, -32.768, 32.768, true));
    problems.push_back(new ecb::CustomFunction(
            new ecb::EggHolder, "F02", "EggHolder",
            DIMENSIONS, -512, 512, true));
    problems.push_back(new ecb::CustomFunction(
            new ecb::Griewank, "F03", "Griewank",
            DIMENSIONS, -600, 600, true));
    problems.push_back(new ecb::CustomFunction(
            new ecb::HyperEllipsoid, "F04", "HyperEllipsoid",
            DIMENSIONS, -5.12, 5.12, true));
    problems.push_back(new ecb::CustomFunction(
            new ecb::Michalewicz, "F05", "Michalewicz",
            DIMENSIONS, 0, M_PI, true));
    problems.push_back(new ecb::CustomFunction(
            new ecb::Neumaier3, "F06", "Neumaier3",
            DIMENSIONS, -DIMENSIONS*DIMENSIONS, DIMENSIONS*DIMENSIONS, true));
    problems.push_back(new ecb::CustomFunction(
            new ecb::OddSquare, "F07", "OddSquare",
            DIMENSIONS, -5 * M_PI, 5 * M_PI, true));
    problems.push_back(new ecb::CustomFunction(
            new ecb::Qing, "F08", "Qing",
            DIMENSIONS, -500, 500, true));
    problems.push_back(new ecb::CustomFunction(
            new ecb::Quartic, "F09", "Quartic",
            DIMENSIONS, -1.28, 1.28, true));
    problems.push_back(new ecb::CustomFunction(
            new ecb::Rana, "F10", "Rana",
            DIMENSIONS, -512, 512, true));
    problems.push_back(new ecb::CustomFunction(
            new ecb::Rastrigin, "F11", "Rastrigin",
            DIMENSIONS, -5.12, 5.12, true));
    problems.push_back(new ecb::CustomFunction(
            new ecb::Rosenbrock, "F12", "Rosenbrock",
            DIMENSIONS, -2.048, 2.048, true));
    problems.push_back(new ecb::CustomFunction(
            new ecb::Salomon, "F13", "Salomon",
            DIMENSIONS, -100, 100, true));
    problems.push_back(new ecb::CustomFunction(
            new ecb::Schaffers, "F14", "Schaffers",
            DIMENSIONS, -100, 100, true));
    problems.push_back(new ecb::CustomFunction(
            new ecb::Schwefel1_2, "F15", "Schwefel1_2",
            DIMENSIONS, -5.12, 5.12, true));
    problems.push_back(new ecb::CustomFunction(
            new ecb::Schwefel2_22, "F16", "Schwefel2_22",
            DIMENSIONS, -100, 100, true));
    problems.push_back(new ecb::CustomFunction(
            new ecb::Schwefel2_26, "F17", "Schwefel2_26",
            DIMENSIONS, -500, 500, true));
    problems.push_back(new ecb::CustomFunction(
            new ecb::Sphere, "F18", "Sphere",
            DIMENSIONS, -5.12, 5.12, true));
    problems.push_back(new ecb::CustomFunction(
            new ecb::Step, "F19", "Step",
            DIMENSIONS, -5.12, 5.12, true));
    problems.push_back(new ecb::CustomFunction(
            new ecb::Step2, "F20", "Step2",
            DIMENSIONS, -500, 500, true));
    problems.push_back(new ecb::CustomFunction(
            new ecb::Whitley, "F21", "Whitley",
            DIMENSIONS, -100, 100, true));


    std::ofstream* log = new std::ofstream;
    for (size_t i = 0; i < problems.size(); ++i) {
        std::string filename = std::string("/tmp/") + problems[i]->name() + std::string(".R");
        log->open(filename.c_str());
        *log << problems[i]->rString();
        log->flush();
        log->close();
    }




}

