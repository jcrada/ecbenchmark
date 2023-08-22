/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import ecbenchmark.function.Ackley;
import ecbenchmark.Function;
import ecbenchmark.operator.Shifted;

/**
 *
 * @author jcrada
 */
public class F03 extends CecFunction {

    private Function ackley;

    public F03(int dimensions) {
        super("F03", "Shifted Ackley's Function", dimensions,
                -32, 32, true, new CecRandom(3l), 0);
        double[] shift = CecMath.ShiftVector(dimensions, getMinimum(), getMaximum(), getRandomizer());
        ackley = new Shifted(shift, 0, new Ackley());
    }

    public double f(final double[] x) {
        return ackley.f(x);
    }
}