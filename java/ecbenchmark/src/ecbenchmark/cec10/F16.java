/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import java.util.ArrayList;
import java.util.List;
import ecbenchmark.function.Ackley;
import ecbenchmark.Function;
import ecbenchmark.operator.Grouped;
import ecbenchmark.operator.Permuted;
import ecbenchmark.operator.Shifted;

/**
 *
 * @author jcrada
 */
public class F16 extends CecFunction {

    private List<Function> sumAckley = new ArrayList<Function>();

    public F16(int dimensions, int mValue) {
        super("F16", "(D/m)-group Shifted and m-rotated Ackley's Function", dimensions,
                -32, 32, true, new CecRandom(16l), mValue);

        double[] shift = CecMath.ShiftVector(dimensions, getMinimum(), getMaximum(), getRandomizer());
        int[] permutation = CecMath.PermutationVector(dimensions, getRandomizer());
        double[][] rotation = CecMath.RotationMatrix(mValue, getRandomizer());
        double[] permutedShift = new double[dimensions];
        for (int i = 0; i < dimensions; ++i) {
            permutedShift[i] = shift[permutation[i]];
        }
        for (int k = 0; k < dimensions / mValue; ++k) {
            sumAckley.add(
                    new Permuted(permutation, new Grouped(k * mValue, (k + 1) * mValue,
                    new Shifted(permutedShift, k * mValue, new CecRotated(rotation, new Ackley())))));
        }

    }

    public double f(final double[] x) {
        double result = 0;
        for (Function rotatedAckley : sumAckley) {
            result += rotatedAckley.f(x);
        }
        return result;
    }
}