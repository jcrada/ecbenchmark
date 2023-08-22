/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.function;

import ecbenchmark.Function;

/**
 *
 * @author jcrada
 */
public class Rosenbrock implements Function {

    public Rosenbrock() {
    }

    public double f(final double[] x) {
        double result = 0.0;
        for (int i = 0; i < x.length - 1; ++i) {
            result += 100 * (x[i] * x[i] - x[i + 1]) * (x[i] * x[i] - x[i + 1])
                    + (x[i] - 1) * (x[i] - 1);
        }
        return result;
    }
}
