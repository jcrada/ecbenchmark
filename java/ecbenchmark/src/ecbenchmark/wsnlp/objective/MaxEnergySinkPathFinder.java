/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp.objective;

import ecbenchmark.util.Matrix;
import ecbenchmark.util.Pair;
import ecbenchmark.wsnlp.model.Network;
import ecbenchmark.wsnlp.model.Node;
import ecbenchmark.wsnlp.model.Sink;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jcrada
 */
public class MaxEnergySinkPathFinder implements SingleSinkPathFinder {

    @Override
    public void computeSinkPaths(Network network) {
//network.getConnectivity()
        Matrix<Float> inverseEnergy = network.distances();
        for (int i = 0; i < network.getNodes().size(); ++i) {
            for (int j = 0; j < network.getNodes().size(); ++j) {
                if (!Float.isInfinite(inverseEnergy.get(i, j))) {
                    inverseEnergy.set(i, j, (float) (1.0 / network.getNode(j).getEnergy()));
                }
            }
        }
        
        int indexOfSink = 0;
        for (Node node : network.getNodes()){
            if (node instanceof Sink) break;
            else indexOfSink++;
        }
        
        
        Matrix<Integer> energyPaths = Matrix.ShortestDistancesAndPaths(inverseEnergy);
        
        for (int i = 0; i < network.getNodes().size(); ++i) {
            if (i == indexOfSink) {
                continue;
            }

            List<Integer> shortestCommPath = Matrix.ShortestPath(i, indexOfSink, energyPaths);

            List<Node> shortestNodePath = new ArrayList<>();
            for (Integer nodeIndex : shortestCommPath) {
                shortestNodePath.add(network.getNode(nodeIndex));
            }
            network.getNode(i).setPathToSink(shortestNodePath);
        }
        

    }
}
