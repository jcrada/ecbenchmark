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
public class Sphere implements Function {

    public Sphere() {
    }

    public double f(final double[] x) {
        double result = 0.0;
        for (int i = 0; i < x.length; ++i) {
            result += x[i] * x[i];
        }
        return result;
    }
}
