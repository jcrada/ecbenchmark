/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp;

import ecbenchmark.wsnlp.model.Network;

/**
 *
 * @author jcrada
 */
public interface NetworkObjective {

    public double f(final Network network);
}
