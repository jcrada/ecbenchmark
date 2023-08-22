/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import ecbenchmark.Operator;
import ecbenchmark.Function;

/**
 *
 * @author jcrada
 */
public class CecRotated extends Operator {

    protected double[][] rotation;

    public CecRotated(double[][] rotation, Function f) {
        super(f);
        this.rotation = rotation;
    }

    public double f(final double[] x) {
        double[] rotated_x = CecMath.Multiply(x, rotation);
        return innerFunction.f(rotated_x);
    }
}
