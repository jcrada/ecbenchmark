/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp.objective;

import ecbenchmark.wsnlp.model.EnergyModel;
import ecbenchmark.wsnlp.model.Node;
import org.apache.log4j.Logger;

/**
 *
 * @author jcrada
 */
public class KonstantinidisEnergyModel implements EnergyModel {
    
    private static Logger log = Logger.getLogger(KonstantinidisEnergyModel.class);
    private double alpha = 4.0, beta = 1.0;
    private double maxEnergy = 5.0;
    
    @Override
    public void initialize(Node a) {
        a.setEnergy(maxEnergy);
    }

    @Override
    public void onProcess(Node node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onReceiveMessage(Node node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onTransmitMessage(Node node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
//    @Override
    public double depleteEnergy(Node a) {
//        
//        
//        double p_i = getBeta() * Math.pow(a.getPathToSink().get(0), getAlpha());
//        double k = 1.0;
//        double energy = a.getEnergy();
        
        
        return 0;
        
    }
    
    public double getAlpha() {
        return alpha;
    }
    
    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }
    
    public double getBeta() {
        return beta;
    }
    
    public void setBeta(double beta) {
        this.beta = beta;
    }
}
