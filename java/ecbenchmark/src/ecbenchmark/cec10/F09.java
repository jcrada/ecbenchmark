/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import java.util.ArrayList;
import java.util.List;
import ecbenchmark.function.Elliptic;
import ecbenchmark.Function;
import ecbenchmark.operator.Grouped;
import ecbenchmark.operator.Permuted;
import ecbenchmark.operator.Shifted;

/**
 *
 * @author jcrada
 */
public class F09 extends CecFunction {

    private Function elliptic;
    private List<Function> sumElliptic = new ArrayList<Function>();

    public F09(int dimensions, int mValue) {
        super("F09", "(D/2m)-group Shifted and m-rotated Elliptic Function", dimensions,
                -100, 100, true, new CecRandom(9l), mValue);

        double[] shift = CecMath.ShiftVector(dimensions, getMinimum(), getMaximum(), getRandomizer());
        int[] permutation = CecMath.PermutationVector(dimensions, getRandomizer());
        double[][] rotation = CecMath.RotationMatrix(mValue, getRandomizer());

        double[] permutedShift = new double[dimensions];
        for (int i = 0; i < dimensions; ++i) {
            permutedShift[i] = shift[permutation[i]];
        }

        for (int k = 0; k < dimensions / (2 * mValue); ++k) {
            sumElliptic.add(
                    new Permuted(permutation, new Grouped(k * mValue, (k + 1) * mValue,
                    new Shifted(permutedShift, k * mValue, new CecRotated(rotation, new Elliptic())))));
        }

        elliptic = new Permuted(permutation, new Grouped(dimensions / 2, dimensions,
                new Shifted(permutedShift, dimensions / 2, new Elliptic())));

    }

    public double f(final double[] x) {
        double result = 0;
        for (Function rotatedElliptic : sumElliptic) {
            result += rotatedElliptic.f(x);
        }
        return result + elliptic.f(x);
    }
}