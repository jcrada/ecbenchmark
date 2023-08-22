/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp.objective;

import ecbenchmark.wsnlp.model.HopCounter;
import ecbenchmark.wsnlp.NetworkObjective;
import ecbenchmark.util.stat.StatOperator;
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
public class HopCounterWithDescStat implements NetworkObjective {

    private static Logger log = Logger.getLogger(HopCounterWithDescStat.class);
    private HopCounter hopCounter;
    private StatOperator statistic;

    public HopCounterWithDescStat(HopCounter hopCounter, StatOperator statistic) {
        this.hopCounter = hopCounter;
        this.statistic = statistic;
    }

    @Override
    public double f(final Network network) {

        List<Integer> hops = new ArrayList<>();

        Sink sink = null;
        for (Node node : network.getNodes()) {
            if (node instanceof Sink) {
                sink = (Sink) node;
                break;
            }
        }

        for (Node node : network.getNodes()) {
            if (node instanceof Sink) {
                continue;
            }
            int numberOfHops = node.getPathToSink().size();
            if (!node.getPathToSink().contains(sink)) {
                numberOfHops = Integer.MAX_VALUE / 2;
            }
            hops.add(numberOfHops);
        }
        return statistic.compute(hops);
    }

    public HopCounter getHopCounter() {
        return hopCounter;
    }

    public void setHopCounter(HopCounter hopCounter) {
        this.hopCounter = hopCounter;
    }
}
