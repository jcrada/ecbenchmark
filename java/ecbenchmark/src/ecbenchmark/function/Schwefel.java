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
public class Schwefel implements Function {

    public Schwefel() {
    }

    public double f(final double[] x) {
        double result = 0.0;
        for (int i = 0; i < x.length; ++i) {
            double sum = 0.0;
            for (int j = 0; j <= i; ++j) {
                sum += x[j];
            }
            result += sum * sum;
        }
        return result;
    }
}
