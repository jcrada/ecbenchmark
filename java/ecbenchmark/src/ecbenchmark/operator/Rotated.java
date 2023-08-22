/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.operator;

import ecbenchmark.Function;
import ecbenchmark.Operator;
import ecbenchmark.util.CommonMath;

/**
 *
 * @author jcrada
 */
public class Rotated extends Operator{

    protected double[][] rotation;

    public Rotated(double[][] rotation, Function f) {
        super(f);
        this.rotation = rotation;
    }

    @Override
    public double f(final double[] x) {
        double[] rotated_x = CommonMath.Multiply(x, rotation);
        return innerFunction.f(rotated_x);
    }
}
