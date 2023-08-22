/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import ecbenchmark.operator.Grouped;
import ecbenchmark.Function;
import ecbenchmark.function.Ackley;
import ecbenchmark.operator.Permuted;
import ecbenchmark.operator.Shifted;

/**
 *
 * @author jcrada
 */
public class F06 extends CecFunction {

    private Function rotatedAckley, ackley;

    public F06(int dimensions, int mValue) {
        super("F06", "Single-group Shifted and m-rotated Ackley's Function", dimensions,
                -32, 32, true, new CecRandom(6l), mValue);

        double[] shift = CecMath.ShiftVector(dimensions, getMinimum(), getMaximum(), getRandomizer());
        int[] permutation = CecMath.PermutationVector(dimensions, getRandomizer());
        double[][] rotation = CecMath.RotationMatrix(mValue, getRandomizer());
        double[] permutedShift = new double[dimensions];
        for (int i = 0; i < dimensions; ++i) {
            permutedShift[i] = shift[permutation[i]];
        }

        rotatedAckley = new Permuted(permutation, new Grouped(0, mValue,
                new Shifted(permutedShift, 0, new CecRotated(rotation, new Ackley()))));

        ackley = new Permuted(permutation, new Grouped(mValue, dimensions,
                new Shifted(permutedShift, mValue, new Ackley())));

    }

    public double f(final double[] x) {
        return rotatedAckley.f(x) * 1e6 + ackley.f(x);
    }
}