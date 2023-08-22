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
public interface Encoding extends Encoder, Decoder {

    public int numberOfVariables(final Network network);
}
