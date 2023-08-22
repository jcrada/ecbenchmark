/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import ecbenchmark.function.Rosenbrock;
import ecbenchmark.Function;
import ecbenchmark.operator.Shifted;

/**
 *
 * @author jcrada
 */
public class F20 extends CecFunction {

    private Function rosenbrock;

    public F20(int dimensions, int mValue) {
        super("F20", "Shifted Rosenbrock's Function", dimensions,
                -100, 100, true, new CecRandom(20l), mValue);

        double[] shift = CecMath.ShiftVector(dimensions, getMinimum(), getMaximum() - 1d, getRandomizer());
        rosenbrock = new Shifted(shift, 0, new Rosenbrock());
    }

    public double f(final double[] x) {
        return rosenbrock.f(x);
    }
}