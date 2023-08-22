/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.util.stat;

/**
 *
 * @author jcrada
 */
public abstract class CombinedStatOperator implements StatOperator {
    protected StatOperator innerOperator;
    
    public CombinedStatOperator(StatOperator innerOperator){
        this.innerOperator = innerOperator;
    }
}
