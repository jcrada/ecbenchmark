/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp.model.encoding;

import ecbenchmark.wsnlp.model.Network;
import ecbenchmark.wsnlp.model.Node;
import ecbenchmark.wsnlp.model.Sink;

/**
 *
 * @author jcrada
 */
public class CommRangeEncoding implements Encoding {
    
    @Override
    public double[] encode(Network network) {
        double[] result = new double[numberOfVariables(network)];
        int index = 0;
        for (Node node : network.getNodes()) {
            if (node instanceof Sink) {
                continue;
            }
            result[index++] = node.getCommunicationRange();
        }
        return result;
    }
    
    @Override
    public void decode(double[] encoding, Network network) {
        int index = 0;
        for (Node node : network.getNodes()) {
            if (node instanceof Sink) {
                continue;
            }
            node.setCommunicationRange(encoding[index++]);
        }
    }

    @Override
    public int numberOfVariables(Network network) {
        //one to one except sink
        return network.getNodes().size() - 1;
    }

  
}
