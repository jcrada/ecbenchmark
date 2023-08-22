/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import ecbenchmark.function.Rastrigin;
import ecbenchmark.Function;
import ecbenchmark.operator.Shifted;

/**
 *
 * @author jcrada
 */
public class F02 extends CecFunction {

    private Function rastrigin;

    public F02(int dimensions) {
        super("F02", "Shifted Rastrigin's Function", dimensions,
                -5, 5, true, new CecRandom(2l), 0);
        double[] shift = CecMath.ShiftVector(dimensions, getMinimum(), getMaximum(), getRandomizer());
        rastrigin = new Shifted(shift, 0, new Rastrigin());
    }

    public double f(final double[] x) {
        return rastrigin.f(x);
    }
}
