/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import java.util.ArrayList;
import java.util.List;
import ecbenchmark.function.Schwefel;
import ecbenchmark.function.Sphere;
import ecbenchmark.Function;
import ecbenchmark.operator.Grouped;
import ecbenchmark.operator.Permuted;
import ecbenchmark.operator.Shifted;

/**
 *
 * @author jcrada
 */
public class F12 extends CecFunction {

    private Function sphere;
    private List<Function> sumSchwefel = new ArrayList<Function>();

    public F12(int dimensions, int mValue) {
        super("F12", "(D/2m)-group Shifted and m-rotated Rastrigin's Function", dimensions,
                -100, 100, true, new CecRandom(12l), mValue);

        double[] shift = CecMath.ShiftVector(dimensions, getMinimum(), getMaximum(), getRandomizer());
        int[] permutation = CecMath.PermutationVector(dimensions, getRandomizer());
        double[] permutedShift = new double[dimensions];
        for (int i = 0; i < dimensions; ++i) {
            permutedShift[i] = shift[permutation[i]];
        }
        for (int k = 0; k < dimensions / (2 * mValue); ++k) {
            sumSchwefel.add(
                    new Permuted(permutation, new Grouped(k * mValue, (k + 1) * mValue,
                    new Shifted(permutedShift, k * mValue, new Schwefel()))));
        }

        sphere = new Permuted(permutation, new Grouped(dimensions / 2, dimensions,
                new Shifted(permutedShift, dimensions / 2, new Sphere())));

    }

    public double f(final double[] x) {
        double result = 0;
        for (Function schwefel : sumSchwefel) {
            result += schwefel.f(x);
        }
        return result + sphere.f(x);
    }
}

