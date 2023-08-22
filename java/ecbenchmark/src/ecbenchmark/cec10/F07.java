/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import ecbenchmark.function.Sphere;
import ecbenchmark.operator.Grouped;
import ecbenchmark.function.Schwefel;
import ecbenchmark.Function;
import ecbenchmark.operator.Permuted;
import ecbenchmark.operator.Shifted;

/**
 *
 * @author jcrada
 */
public class F07 extends CecFunction {

    private Function schweffel, sphere;

    public F07(int dimensions, int mValue) {
        super("F07", "Single-group Shifted m-dimensional Schwefel's Function", dimensions,
                -100, 100, true, new CecRandom(7l), mValue);

        double[] shift = CecMath.ShiftVector(dimensions, getMinimum(), getMaximum(), getRandomizer());
        int[] permutation = CecMath.PermutationVector(dimensions, getRandomizer());
        double[] permutedShift = new double[dimensions];
        for (int i = 0; i < dimensions; ++i) {
            permutedShift[i] = shift[permutation[i]];
        }
        schweffel = new Permuted(permutation, new Grouped(0, mValue,
                new Shifted(permutedShift, 0, new Schwefel())));

        sphere = new Permuted(permutation, new Grouped(mValue, dimensions,
                new Shifted(permutedShift, mValue, new Sphere())));

    }

    public double f(final double[] x) {
        return schweffel.f(x) * 1e6 + sphere.f(x);
    }
}