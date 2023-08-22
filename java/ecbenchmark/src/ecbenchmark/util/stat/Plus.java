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
public class Plus implements StatOperator{
    
    StatOperator a,b;
    
    public Plus(StatOperator a, StatOperator b){
        this.a =a;
        this.b= b;
    }

    @Override
    public double compute(List<? extends Number> list) {
        return a.compute(list) + b.compute(list);
    }
    
    
    
}
