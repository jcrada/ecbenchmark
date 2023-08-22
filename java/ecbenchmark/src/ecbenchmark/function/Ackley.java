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
public class Ackley implements Function {

    public Ackley() {
    }

    public double f(final double[] x) {
        double sumA = 0.0, sumB = 0.0;
        for (int i = 0; i < x.length; ++i) {
            sumA += x[i] * x[i];
            sumB += Math.cos(2 * Math.PI * x[i]);
        }
        return -20 * Math.exp(-0.2 * Math.sqrt((1.0 / x.length) * sumA))
                - Math.exp((1.0 / x.length) * sumB)
                + 20 + Math.E;
    }
}
