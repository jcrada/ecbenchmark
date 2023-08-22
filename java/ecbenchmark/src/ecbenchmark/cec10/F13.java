/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import java.util.ArrayList;
import java.util.List;
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
public class F13 extends CecFunction {

    private Function sphere;
    private List<Function> sumRosenbrock = new ArrayList<Function>();

    public F13(int dimensions, int mValue) {
        super("F13", "(D/2m)-group Shifted and m-rotated Rosenbrocks's Function", dimensions,
                -100, 100, true, new CecRandom(13l), mValue);

        double[] shift = CecMath.ShiftVector(dimensions, getMinimum(), getMaximum() - 1d, getRandomizer());
        int[] permutation = CecMath.PermutationVector(dimensions, getRandomizer());
        double[] permutedShift = new double[dimensions];
        for (int i = 0; i < dimensions; ++i) {
            permutedShift[i] = shift[permutation[i]];
        }
        for (int k = 0; k < dimensions / (2 * mValue); ++k) {
            sumRosenbrock.add(
                    new Permuted(permutation, new Grouped(k * mValue, (k + 1) * mValue,
                    new Shifted(permutedShift, k * mValue, new Rosenbrock()))));
        }


        sphere = new Permuted(permutation, new Grouped(dimensions / 2, dimensions,
                new Shifted(permutedShift, dimensions / 2, new Sphere())));

    }

    public double f(final double[] x) {
        double result = 0;
        for (Function rosenbrock : sumRosenbrock) {
            result += rosenbrock.f(x);
        }
        return result + sphere.f(x);
    }
}

