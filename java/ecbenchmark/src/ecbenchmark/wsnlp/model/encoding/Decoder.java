/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp.model.encoding;

import ecbenchmark.wsnlp.model.Network;

/**
 *
 * @author jcrada
 */
public interface Decoder {
    
    public void decode(double[] encoding, Network network);
//    public int numberOfNodes(double[] encoding);
}
