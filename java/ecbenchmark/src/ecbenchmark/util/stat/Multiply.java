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
public class Multiply extends CombinedStatOperator{
    
    private double factor;
    
    public Multiply(double factor, StatOperator innerOperator){
        super(innerOperator);
        this.factor =factor;
    }

    @Override
    public double compute(List<? extends Number> list) {
        return factor * innerOperator.compute(list);
    }
    
    
    
}
