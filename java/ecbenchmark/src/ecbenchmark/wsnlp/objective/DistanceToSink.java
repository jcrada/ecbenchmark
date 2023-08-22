/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp.objective;

import ecbenchmark.util.stat.StatOperator;
import ecbenchmark.wsnlp.NetworkObjective;
import ecbenchmark.wsnlp.model.Network;
import ecbenchmark.wsnlp.model.Node;
import ecbenchmark.wsnlp.model.Sink;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jcrada
 */
public class DistanceToSink implements NetworkObjective {

    private StatOperator statistic;

    public DistanceToSink(StatOperator statistic) {
        this.statistic = statistic;
    }

    @Override
    public double f(Network network) {
        Sink sink = null;
        for (Node node : network.getNodes()) {
            if (node instanceof Sink) {
                sink = (Sink) node;
                break;
            }
        }
        List<Double> distances = new ArrayList<>();
        for (Node node : network.getNodes()){
            if (node instanceof Sink) continue;
            distances.add(node.getPosition().euclideanDistanceTo(sink.getPosition()));
        }

        return statistic.compute(distances);
    }
}
