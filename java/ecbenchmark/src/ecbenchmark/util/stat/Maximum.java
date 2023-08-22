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
public class Maximum implements StatOperator{

    @Override
    public double compute(List<? extends Number> list) {
        double result = Double.NEGATIVE_INFINITY;
        for (Number x : list){
            if (x.doubleValue() > result){
                result = x.doubleValue();
            }
        }
        return result;
    }
    
}
