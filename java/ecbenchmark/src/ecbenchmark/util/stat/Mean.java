/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.util.stat;

import java.util.List;

/**
 *
 * @author jcrada
 */
public class Mean implements StatOperator {

    @Override
    public double compute(List<? extends Number> list) {
        double result = 0;
        for (Number x : list) {
            result += x.doubleValue();
        }
        return result / list.size();
    }
}
