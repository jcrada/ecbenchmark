/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp.objective;

import ecbenchmark.wsnlp.model.EnergyModel;
import ecbenchmark.wsnlp.NetworkObjective;
import ecbenchmark.wsnlp.model.Network;
import ecbenchmark.wsnlp.model.Node;
import ecbenchmark.wsnlp.model.Sink;
import org.apache.log4j.Logger;

/**
 *
 * @author jcrada
 */
public class Lifetime implements NetworkObjective {

    private static final Logger log = Logger.getLogger(Lifetime.class);
    private EnergyModel energyModel;

    public Lifetime(EnergyModel energyModel) {
        this.energyModel = energyModel;
    }

    @Override
    public double f(Network network) {
        for (Node node : network.getNodes()) {
            energyModel.initialize(node);
        }
        double minEnergy = Double.POSITIVE_INFINITY;
        int cycles = 0;
        while (minEnergy >= 0.0) {
            for (Node node : network.getNodes()) {
                try {
                    node.process(energyModel);
                } catch (Exception ex) {
//                    log.debug(ex.getMessage());
                }
                double energy = node.getEnergy();
                if (energy < minEnergy) {
                    minEnergy = energy;
                }
            }
            cycles++;
        }
        return -cycles;
    }
}
