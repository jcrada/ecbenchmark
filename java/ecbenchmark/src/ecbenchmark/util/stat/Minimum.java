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
public class Minimum implements StatOperator {

    @Override
    public double compute(List<? extends Number> list) {
        double min = Double.POSITIVE_INFINITY;
        for (Number x: list){
            if (x.doubleValue() < min){
                min = x.doubleValue();
            }
        }
        return min;
    }
    
}
