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
public class FullConnectivity implements Connectivity{

    @Override
    public double connectionDistance(Node a, Node b) {
        return a.getPosition().euclideanDistanceTo(b.getPosition());
    }

    @Override
    public boolean isConnected(Node a, Node b) {
        return true;
    }

    
    
    
}
