/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import java.util.ArrayList;
import java.util.List;
import ecbenchmark.function.Rastrigin;
import ecbenchmark.Function;
import ecbenchmark.operator.Grouped;
import ecbenchmark.operator.Permuted;
import ecbenchmark.operator.Shifted;

/**
 *
 * @author jcrada
 */
public class F15 extends CecFunction {

    private List<Function> sumRastrigin = new ArrayList<Function>();

    public F15(int dimensions, int mValue) {
        super("F15", "(D/m)-group Shifted and m-rotated Rastrigin's Function", dimensions,
                -5, 5, true, new CecRandom(15l), mValue);

        double[] shift = CecMath.ShiftVector(dimensions, getMinimum(), getMaximum(), getRandomizer());
        int[] permutation = CecMath.PermutationVector(dimensions, getRandomizer());
        double[][] rotation = CecMath.RotationMatrix(mValue, getRandomizer());
        double[] permutedShift = new double[dimensions];
        for (int i = 0; i < dimensions; ++i) {
            permutedShift[i] = shift[permutation[i]];
        }
        for (int k = 0; k < dimensions / mValue; ++k) {
            sumRastrigin.add(
                    new Permuted(permutation, new Grouped(k * mValue, (k + 1) * mValue,
                    new Shifted(permutedShift, k * mValue, new CecRotated(rotation, new Rastrigin())))));
        }
    }

    public double f(final double[] x) {
        double result = 0;
        for (Function rotatedRastrigin : sumRastrigin) {
            result += rotatedRastrigin.f(x);
        }
        return result;
    }
}