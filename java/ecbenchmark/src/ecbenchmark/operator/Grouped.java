/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.operator;

import ecbenchmark.Operator;
import java.util.Arrays;
import ecbenchmark.Function;

/**
 *
 * @author jcrada
 */
public class Grouped extends Operator {

    private int fromIndex, toIndex;

    public Grouped(int from, int to, Function f) {
        super(f);
        this.fromIndex = from;
        this.toIndex = to;
    }

    @Override
    public double f(final double[] x) {
        double[] group = Arrays.copyOfRange(x, fromIndex, toIndex);
        return innerFunction.f(group);
    }
}
