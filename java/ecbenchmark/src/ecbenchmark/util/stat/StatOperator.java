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
public interface StatOperator {
    public double compute(List<? extends Number> list);
}


