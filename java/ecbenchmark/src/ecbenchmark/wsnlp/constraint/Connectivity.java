/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp.constraint;

import ecbenchmark.wsnlp.model.Node;

/**
 *
 * @author jcrada
 */
public interface Connectivity {
    
    public boolean isConnected(final Node a, final Node b );
    public double connectionDistance(final Node a, final Node b);
    
}
