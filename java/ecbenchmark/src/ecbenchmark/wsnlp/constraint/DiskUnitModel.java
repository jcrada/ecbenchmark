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
public class DiskUnitModel implements Connectivity{

    @Override
    public double connectionDistance(final Node a, final Node b) {
        double distance = a.getPosition().euclideanDistanceTo(b.getPosition());
        if (distance > a.getCommunicationRange()){
            distance = Double.POSITIVE_INFINITY;
        }
        return distance;
    }

    @Override
    public boolean isConnected(final Node a, final Node b) {
        return !Double.isInfinite(connectionDistance(a, b));
    }
    
}
