/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp.objective;

import ecbenchmark.wsnlp.NetworkObjective;
import ecbenchmark.wsnlp.model.Network;
import ecbenchmark.wsnlp.model.Node;
import ecbenchmark.wsnlp.model.Sink;

/**
 *
 * @author jcrada
 */
public class GridCoverage implements NetworkObjective {

    @Override
    public double f(Network network) {
        network.getGrid().clear();
        for (Node node : network.getNodes()) {
            if (node instanceof Sink) {
                continue;
            }
            network.getGrid().applyCoverage(node);
        }

        return -network.getGrid().coveredArea();
    }
}
