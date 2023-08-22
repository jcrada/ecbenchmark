/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp.model;

import common.Util;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jcrada
 */
public class Node {

    private String id;
    private Position position;
    private double sensorRange;
    private double communicationRange;
    private double energy;
    private double power;
    private int messagesTransmitted;
    private int messagesReceived;
    private List<Node> pathToSink = new ArrayList<>();

    public Node() {
    }

    public Node(String id, Position position) {
        this.id = id;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public double getSensorRange() {
        return sensorRange;
    }

    public void setSensorRange(double sensorRange) {
        this.sensorRange = sensorRange;
    }

    public double getCommunicationRange() {
        return communicationRange;
    }

    public void setCommunicationRange(double communicationRange) {
        this.communicationRange = communicationRange;
    }

    public List<Node> getPathToSink() {
        return pathToSink;
    }

    public void setPathToSink(List<Node> pathToSink) {
        this.pathToSink = pathToSink;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public int getMessagesTransmitted() {
        return messagesTransmitted;
    }

    public void setMessagesTransmitted(int messagesTransmitted) {
        this.messagesTransmitted = messagesTransmitted;
    }

    public int getMessagesReceived() {
        return messagesReceived;
    }

    public void setMessagesReceived(int messagesReceived) {
        this.messagesReceived = messagesReceived;
    }

    public void receiveMessage(EnergyModel energy) throws Exception{
        this.messagesReceived++;
        energy.onReceiveMessage(this);
        transmitMessage(energy);
    }

    public void transmitMessage(EnergyModel energy) throws Exception {
        this.messagesTransmitted++;
        energy.onTransmitMessage(this);
        if (getEnergy() <= 0.0) throw new Exception("Node Out of Energy");
        if (!pathToSink.isEmpty()) {
            pathToSink.get(0).receiveMessage(energy);
        }
    }

    public void process(EnergyModel energy) throws Exception{
        energy.onProcess(this);
        transmitMessage(energy);

    }

    @Override
    public String toString() {
        return Util.ToString(this);
//        String result = "Node[id=" + id + "; position=" + getPosition() + "; r_s=" + getSensorRange() + "; "
//                + "r_c=" + getCommunicationRange() + "; pathToSink={ ";
//        for (Pair<Node, Double> n : pathToSink) {
//            result += n.first.getId() + "[" + n.second + "]; ";
//        }
//        result += "} ]";
//        return result;

    }

    public static void main(String[] args) {
//        System.out.println(new Node());
    }
}
