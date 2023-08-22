/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp.objective;

import ecbenchmark.wsnlp.model.HopCounter;
import ecbenchmark.util.Matrix;
import ecbenchmark.wsnlp.model.Network;
import ecbenchmark.wsnlp.model.Node;
import ecbenchmark.wsnlp.model.Sink;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author jcrada
 */
public class SingleSinkHopCounter implements HopCounter {
    private static final Logger log = Logger.getLogger(SingleSinkHopCounter.class);
    

    @Override
    public void computeNumberOfHops(Network network) {
        int indexOfSink = -1;
        for (int i = 0; i < network.numberOfNodes(); ++i) {
            if (network.getNode(i) instanceof Sink) {
                indexOfSink = i;
                break;
            }
        }
        if (indexOfSink == -1) {
            throw new RuntimeException("Network has no sink");
        }
        Matrix<Float> distances = network.distances();

        Matrix<Double> commDistances = network.distances(network.getConnectivity());
        Matrix<Integer> commPath = Matrix.ShortestDistancesAndPaths(commDistances);


        for (int i = 0; i < network.numberOfNodes(); ++i) {
            if (i == indexOfSink) {
                continue;
            }

            List<Integer> shortestCommPath = Matrix.ShortestPath(i, indexOfSink, commPath, distances);
            List<Node> shortestNodePath = new ArrayList<>();
            for (Integer nodeIndex : shortestCommPath) {
                shortestNodePath.add(network.getNode(nodeIndex));
            }
            network.getNode(i).setPathToSink(shortestNodePath);
        }
    }
}
