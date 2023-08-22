/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import java.util.ArrayList;
import java.util.List;
import ecbenchmark.function.Schwefel;
import ecbenchmark.Function;
import ecbenchmark.operator.Grouped;
import ecbenchmark.operator.Permuted;
import ecbenchmark.operator.Shifted;

/**
 *
 * @author jcrada
 */
public class F17 extends CecFunction {

    private List<Function> sumSchweffel = new ArrayList<Function>();

    public F17(int dimensions, int mValue) {
        super("F17", "(D/m)-group Shifted and m-rotated Schwefel's Function", dimensions,
                -100, 100, true, new CecRandom(17l), mValue);

        double[] shift = CecMath.ShiftVector(dimensions, getMinimum(), getMaximum(), getRandomizer());
        int[] permutation = CecMath.PermutationVector(dimensions, getRandomizer());
        double[] permutedShift = new double[dimensions];
        for (int i = 0; i < dimensions; ++i) {
            permutedShift[i] = shift[permutation[i]];
        }
        for (int k = 0; k < dimensions / mValue; ++k) {
            sumSchweffel.add(
                    new Permuted(permutation, new Grouped(k * mValue, (k + 1) * mValue,
                    new Shifted(permutedShift, k * mValue, new Schwefel()))));
        }

    }

    public double f(final double[] x) {
        double result = 0;
        for (Function schwefel : sumSchweffel) {
            result += schwefel.f(x);
        }
        return result;
    }
}