/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp.objective;

import ecbenchmark.wsnlp.model.HopCounter;
import ecbenchmark.wsnlp.NetworkObjective;
import ecbenchmark.util.CommonMath;
import ecbenchmark.wsnlp.model.Network;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jcrada
 */
public class HopCountStdDev implements NetworkObjective {

    private HopCounter hopCounter;

    public HopCountStdDev(HopCounter hopCounter) {
        this.hopCounter = hopCounter;
    }

    @Override
    public double f(final Network network) {
        hopCounter.computeNumberOfHops(network);
        List<Integer> hops = new ArrayList<Integer>();
        int sum = 0;
        for (int i = 0; i < network.numberOfNodes(); ++i) {
            int numberOfHops = network.getNode(i).getPathToSink().size();
            if (numberOfHops == 0) {
                System.out.println("WARNING: impossible to reach sink");
            }
            hops.add(numberOfHops);
            sum += numberOfHops;
        }
        return CommonMath.StDev(hops, (double) sum / network.numberOfNodes());
    }

    public HopCounter getHopCounter() {
        return hopCounter;
    }

    public void setHopCounter(HopCounter hopCounter) {
        this.hopCounter = hopCounter;
    }
}
