/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.operator;

import ecbenchmark.Operator;
import ecbenchmark.Function;

/**
 *
 * @author jcrada
 */
public class Permuted extends Operator {

    protected int[] permutation;

    public Permuted(int[] permutation, Function f) {
        super(f);
        this.permutation = permutation;
    }

    @Override
    public double f(final double[] x) {
        double[] permuted_x = new double[x.length];
        for (int i = 0; i < permutation.length; ++i) {
            permuted_x[i] = x[permutation[i]];
        }
        return innerFunction.f(permuted_x);
    }
}
