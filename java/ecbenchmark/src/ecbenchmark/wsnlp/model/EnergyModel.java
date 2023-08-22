/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp.model;

import ecbenchmark.wsnlp.model.Node;

/**
 *
 * @author jcrada
 */
public interface  EnergyModel {
    public void initialize(Node node);
    public void onReceiveMessage(Node node);
    public void onTransmitMessage(Node node);
    public void onProcess(Node node);
    
    
    
}
