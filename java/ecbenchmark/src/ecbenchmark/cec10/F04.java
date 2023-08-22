/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import ecbenchmark.operator.Grouped;
import ecbenchmark.function.Elliptic;
import ecbenchmark.Function;
import ecbenchmark.operator.Permuted;
import ecbenchmark.operator.Shifted;

/**
 *
 * @author jcrada
 */
public class F04 extends CecFunction {

    private Function rotatedElliptic, elliptic;

    public F04(int dimensions, int mValue) {
        super("F04", "Single-group Shifted and m-rotated Elliptic Function", dimensions,
                -100, 100, true, new CecRandom(4l), mValue);

        double[] shift = CecMath.ShiftVector(dimensions, getMinimum(), getMaximum(), getRandomizer());
        int[] permutation = CecMath.PermutationVector(dimensions, getRandomizer());
        double[][] rotation = CecMath.RotationMatrix(mValue, getRandomizer());
        double[] permutedShift = new double[dimensions];
        for (int i = 0; i < dimensions; ++i) {
            permutedShift[i] = shift[permutation[i]];
        }

        rotatedElliptic = new Permuted(permutation, new Grouped(0, mValue,
                new Shifted(permutedShift, 0,
                new CecRotated(rotation, new Elliptic()))));

        elliptic = new Permuted(permutation, new Grouped(mValue, dimensions,
                new Shifted(permutedShift, mValue,
                new Elliptic())));
    }

    public double f(final double[] x) {
        return rotatedElliptic.f(x) * 1e6 + elliptic.f(x);
    }
}