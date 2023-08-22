/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import java.util.ArrayList;
import java.util.List;
import ecbenchmark.function.Rosenbrock;
import ecbenchmark.Function;
import ecbenchmark.operator.Grouped;
import ecbenchmark.operator.Permuted;
import ecbenchmark.operator.Shifted;

/**
 *
 * @author jcrada
 */
public class F18 extends CecFunction {

    private List<Function> sumRosenbrock = new ArrayList<Function>();

    public F18(int dimensions, int mValue) {
        super("F18", "(D/m)-group Shifted and m-rotated Rosenbrock's Function", dimensions,
                -100, 100, true, new CecRandom(18l), mValue);

        double[] shift = CecMath.ShiftVector(dimensions, getMinimum(), getMaximum() - 1d, getRandomizer());
        int[] permutation = CecMath.PermutationVector(dimensions, getRandomizer());

        double[] permutedShift = new double[dimensions];
        for (int i = 0; i < dimensions; ++i) {
            permutedShift[i] = shift[permutation[i]];
        }
        for (int k = 0; k < dimensions / mValue; ++k) {
            sumRosenbrock.add(
                    new Permuted(permutation, new Grouped(k * mValue, (k + 1) * mValue,
                    new Shifted(permutedShift, k * mValue, new Rosenbrock()))));
        }

    }

    public double f(final double[] x) {
        double result = 0;
        for (Function rosenbrock : sumRosenbrock) {
            result += rosenbrock.f(x);
        }
        return result;
    }
}