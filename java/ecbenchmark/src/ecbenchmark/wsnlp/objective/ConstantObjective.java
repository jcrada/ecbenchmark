/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp.objective;

import ecbenchmark.wsnlp.NetworkObjective;
import ecbenchmark.wsnlp.model.Network;

/**
 *
 * @author jcrada
 */
public class ConstantObjective implements NetworkObjective{

    private double constant;
    public ConstantObjective(double constant){
        this.constant = constant;
    }
    @Override
    public double f(Network network) {
        return constant;
    }
    
}
