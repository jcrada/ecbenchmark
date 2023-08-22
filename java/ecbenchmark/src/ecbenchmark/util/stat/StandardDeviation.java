/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.util.stat;

import ecbenchmark.util.CommonMath;
import java.util.List;

/**
 *
 * @author jcrada
 */
public class StandardDeviation implements StatOperator {

    @Override
    public double compute(List<? extends Number> list) {
        return compute(list, new Mean().compute(list));
    }

    public double compute(List<? extends Number> list, double mean) {
        double result = 0;
        for (Number x : list) {
            result += CommonMath.Pow2(x.doubleValue() - mean);
        }
        return Math.sqrt(result / list.size());
    }
}
