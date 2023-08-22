/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import ecbenchmark.function.Elliptic;
import ecbenchmark.Function;
import ecbenchmark.operator.Shifted;

/**
 *
 * @author jcrada
 */
public class F01 extends CecFunction {

    private Function elliptic;

    public F01(int dimensions) {
        super("F01", "Shifted Elliptic Function", dimensions,
                -100, 100, true, new CecRandom(1l), 0);
        double[] shift = CecMath.ShiftVector(dimensions, getMinimum(), getMaximum(), getRandomizer());
        elliptic = new Shifted(shift, 0, new Elliptic());
    }

    public double f(final double[] x) {
        return elliptic.f(x);
    }
}