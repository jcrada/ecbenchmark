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
public class HopCountModel implements Connectivity{

    @Override
    public double connectionDistance(Node a, Node b) {
        if (a == b){
            return Double.POSITIVE_INFINITY;
        }
        return  a.getPosition().euclideanDistanceTo(b.getPosition()) <= a.getCommunicationRange() 
                ? 1.0 : Double.POSITIVE_INFINITY;
    }

    @Override
    public boolean isConnected(Node a, Node b) {
        return !Double.isInfinite(connectionDistance(a, b));
    }
    
}
