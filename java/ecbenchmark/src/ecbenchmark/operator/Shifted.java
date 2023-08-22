/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.operator;

import ecbenchmark.Operator;
import ecbenchmark.Function;

/**
 *
 * @author jcrada
 */
public class Shifted extends Operator {

    private double[] shift;
    private int fromIndex;

    public Shifted(double[] shift, int fromIndex, Function f) {
        super(f);
        this.shift = shift;
        this.fromIndex = fromIndex;
    }

    @Override
    public double f(final double[] x) {
        double[] shifted_x = new double[x.length];
        int shiftIndex = fromIndex;
        for (int i = 0; i < x.length; ++i) {
            shifted_x[i] = x[i] - shift[shiftIndex++];
        }
        return innerFunction.f(shifted_x);
    }
}
