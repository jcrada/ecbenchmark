/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import ecbenchmark.function.Schwefel;
import ecbenchmark.Function;
import ecbenchmark.operator.Shifted;

/**
 *
 * @author jcrada
 */
public class F19 extends CecFunction {

    private Function schwefel;

    public F19(int dimensions, int mValue) {
        super("F19", "Shifted Schwefel's Function", dimensions,
                -100, 100, true, new CecRandom(19l), mValue);

        double[] shift = CecMath.ShiftVector(dimensions, getMinimum(), getMaximum(), getRandomizer());
        schwefel = new Shifted(shift, 0, new Schwefel());
    }

    public double f(final double[] x) {
        return schwefel.f(x);
    }
}