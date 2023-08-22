/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp.objective;

import ecbenchmark.wsnlp.model.EnergyModel;
import ecbenchmark.wsnlp.model.Node;
import ecbenchmark.wsnlp.model.Sink;

/**
 *
 * @author jcrada
 */
public class ConstantEnergyModel implements EnergyModel {

    //From SunSpot in (mAh)
    private double processing = 86e-3;
    private double transmission = 18e-3;
    private double reception = 20e-3;
    private double maxEnergy = 720e-3;
    private double timeDepletionFactor = 1.0 / 60.0; // Minutes

    public ConstantEnergyModel() {
    }

    @Override
    public void initialize(Node node) {
        if (node instanceof Sink) {
            node.setEnergy(Double.POSITIVE_INFINITY);
        } else {
            node.setEnergy(getMaxEnergy());
        }
    }

    @Override
    public void onProcess(Node node) {
        double energy = node.getEnergy();
        energy -= getProcessing() * timeDepletionFactor;
        node.setEnergy(energy);
    }

    @Override
    public void onReceiveMessage(Node node) {
        double energy = node.getEnergy();
        energy -= getReception() * timeDepletionFactor;
        node.setEnergy(energy);
    }

    @Override
    public void onTransmitMessage(Node node) {
        double energy = node.getEnergy();
        energy -= getTransmission() * timeDepletionFactor
                * Math.pow(node.getCommunicationRange(), 2.0);
        node.setEnergy(energy);
    }

    public double getTimeDepletionFactor() {
        return timeDepletionFactor;
    }

    public void setTimeDepletionFactor(double timeDepletionFactor) {
        this.timeDepletionFactor = timeDepletionFactor;
    }

    public double getProcessing() {
        return processing;
    }

    public void setProcessing(double processor) {
        this.processing = processor;
    }

    public double getTransmission() {
        return transmission;
    }

    public void setTransmission(double transmission) {
        this.transmission = transmission;
    }

    public double getReception() {
        return reception;
    }

    public void setReception(double reception) {
        this.reception = reception;
    }

    public double getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(double maxEnergy) {
        this.maxEnergy = maxEnergy;
    }
}
