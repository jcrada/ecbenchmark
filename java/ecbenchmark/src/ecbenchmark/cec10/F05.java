/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import ecbenchmark.function.Rastrigin;
import ecbenchmark.operator.Grouped;
import ecbenchmark.Function;
import ecbenchmark.operator.Permuted;
import ecbenchmark.operator.Shifted;

/**
 *
 * @author jcrada
 */
public class F05 extends CecFunction {

    private Function rotatedRastrigin, rastrigin;

    public F05(int dimensions, int mValue) {
        super("F05", "Single-group Shifted and m-rotated Rastrigin's Function", dimensions,
                -5, 5, true, new CecRandom(5l), mValue);

        double[] shift = CecMath.ShiftVector(dimensions, getMinimum(), getMaximum(), getRandomizer());
        int[] permutation = CecMath.PermutationVector(dimensions, getRandomizer());
        double[] permutedShift = new double[dimensions];
        for (int i = 0; i < dimensions; ++i) {
            permutedShift[i] = shift[permutation[i]];
        }

        double[][] rotation = CecMath.RotationMatrix(mValue, getRandomizer());

        rotatedRastrigin = new Permuted(permutation, new Grouped(0, mValue,
                new Shifted(permutedShift, 0, new CecRotated(rotation, new Rastrigin()))));

        rastrigin = new Permuted(permutation, new Grouped(mValue, dimensions,
                new Shifted(permutedShift, mValue, new Rastrigin())));

    }

    public double f(final double[] x) {
        return rotatedRastrigin.f(x) * 1e6 + rastrigin.f(x);
    }
}