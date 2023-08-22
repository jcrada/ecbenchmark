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
public class F11 extends CecFunction {

    private Function ackley;
    private List<Function> sumAckley = new ArrayList<Function>();

    public F11(int dimensions, int mValue) {
        super("F11", "(D/2m)-group Shifted and m-rotated Ackley's Function", dimensions,
                -32, 32, true, new CecRandom(11l), mValue);

        double[] shift = CecMath.ShiftVector(dimensions, getMinimum(), getMaximum(), getRandomizer());
        int[] permutation = CecMath.PermutationVector(dimensions, getRandomizer());
        double[][] rotation = CecMath.RotationMatrix(mValue, getRandomizer());
        double[] permutedShift = new double[dimensions];
        for (int i = 0; i < dimensions; ++i) {
            permutedShift[i] = shift[permutation[i]];
        }

        for (int k = 0; k < dimensions / (2 * mValue); ++k) {
            sumAckley.add(
                    new Permuted(permutation, new Grouped(k * mValue, (k + 1) * mValue,
                    new Shifted(permutedShift, k * mValue, new CecRotated(rotation, new Ackley())))));
        }


        ackley = new Permuted(permutation, new Grouped(dimensions / 2, dimensions,
                new Shifted(permutedShift, dimensions / 2, new Ackley())));

    }

    public double f(final double[] x) {
        double result = 0;
        for (Function rotatedAckley : sumAckley) {
            result += rotatedAckley.f(x);
        }
        return result + ackley.f(x);
    }
}

