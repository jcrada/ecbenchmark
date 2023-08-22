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
public class Elliptic implements Function {

    public double[] powCache = null;

    public Elliptic() {
    }

    public double f(final double[] x) {
        if (powCache == null) {
            powCache = new double[x.length];
            for (int i = 0; i < x.length; ++i) {
                powCache[i] = Math.pow(1e6, i / (x.length - 1.0));
            }
        }
        double result = 0;
        for (int i = 0; i < x.length; ++i) {
            result += powCache[i] * x[i] * x[i];
        }
        return result;
    }
}
