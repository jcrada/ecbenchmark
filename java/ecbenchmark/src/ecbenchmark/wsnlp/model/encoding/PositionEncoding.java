/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp.model.encoding;

import ecbenchmark.wsnlp.model.Network;
import ecbenchmark.wsnlp.model.Node;
import ecbenchmark.wsnlp.model.Position;
import ecbenchmark.wsnlp.model.Sink;
import java.util.Iterator;

/**
 *
 * @author jcrada
 */
public class PositionEncoding implements Encoding {

    @Override
    public double[] encode(Network network) {
        double[] result = new double[numberOfVariables(network)];
        int index = 0;
        for (Node node : network.getNodes()) {
            if (node instanceof Sink) {
                continue;
            }
            result[index++] = node.getPosition().get(Position.X);
            result[index++] = node.getPosition().get(Position.Y);
        }
        return result;
    }
    
    @Override
    public int numberOfVariables(Network network) {
        // 2 for Positioning, except for sink (-1)
        return 2 * (network.numberOfNodes() - 1); 
    }

    @Override
    public void decode(double[] encoding, Network network) {
        Iterator<Node> it = network.getNodes().iterator();
        int index = 0;
        while (it.hasNext()) {
            Node node = it.next();
            if (node instanceof Sink) {
                continue;
            }
            Position position = node.getPosition();
            for (int i = 0; i < position.numberOfDimensions(); ++i) {
                position.set(i, encoding[index++]);
            }
        }
    }

    

    

    
}
