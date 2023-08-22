/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.function;

import ecbenchmark.Function;
import java.util.Random;

/**
 *
 * @author jcrada
 */
public class Rastrigin implements Function {

    public Rastrigin() {
    }

    public double f(final double[] x) {
        double result = 0.0;
        for (int i = 0; i < x.length; ++i) {
            result += x[i] * x[i] - 10 * Math.cos(2 * Math.PI * x[i]) + 10;
        }
        return result;
    }
}
