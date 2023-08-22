/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import ecbenchmark.function.Rosenbrock;
import ecbenchmark.function.Sphere;
import ecbenchmark.Function;
import ecbenchmark.operator.Grouped;
import ecbenchmark.operator.Permuted;
import ecbenchmark.operator.Shifted;

/**
 *
 * @author jcrada
 */
public class F08 extends CecFunction {

    private Function rosenbrock, sphere;

    public F08(int dimensions, int mValue) {
        super("F08", "Single-group Shifted m-dimensional Rosenbrock's Function", dimensions,
                -100, 100, true, new CecRandom(8l), mValue);

        double[] shift = CecMath.ShiftVector(dimensions, getMinimum(), getMaximum() - 1.0,
                getRandomizer());
        int[] permutation = CecMath.PermutationVector(dimensions, getRandomizer());
        double[] permutedShift = new double[dimensions];
        for (int i = 0; i < dimensions; ++i) {
            permutedShift[i] = shift[permutation[i]];
        }

        rosenbrock = new Permuted(permutation, new Grouped(0, mValue,
                new Shifted(permutedShift, 0, new Rosenbrock())));

        sphere = new Permuted(permutation, new Grouped(mValue, dimensions,
                new Shifted(permutedShift, mValue, new Sphere())));

    }

    public double f(final double[] x) {
        return rosenbrock.f(x) * 1e6 + sphere.f(x);
    }
}