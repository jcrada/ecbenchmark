/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp.constraint;

import ecbenchmark.util.Matrix;
import ecbenchmark.wsnlp.NetworkConstraintHandler;
import ecbenchmark.wsnlp.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.log4j.Logger;

/**
 *
 * @author jcrada
 */
public class ConnectivityByCommRange implements NetworkConstraintHandler {

    public static final Logger log = Logger.getLogger(ConnectivityByCommRange.class);

    @Override
    public boolean hasConstraintViolations(Network network) {
        int indexOfSink = 0;
        for (Node node : network.getNodes()) {
            if (node instanceof Sink) {
                break;
            }
            indexOfSink++;
        }

        Matrix<Double> commGraph = network.distances(network.getConnectivity());

        Matrix<Integer> commPaths = Matrix.ShortestDistancesAndPaths(commGraph);

        for (int i = 0; i < network.numberOfNodes(); ++i) {
            if (i == indexOfSink) {
                continue;
            }

            List<Integer> shortestCommPath = Matrix.ShortestPath(i, indexOfSink, commPaths);

            if (!shortestCommPath.contains(i)) {
                return true;
            }

            List<Node> shortestNodePath = new ArrayList<>();
            for (Integer nodeIndex : shortestCommPath) {
                shortestNodePath.add(network.getNode(nodeIndex));
            }
            network.getNode(i).setPathToSink(shortestNodePath);
        }
        return false;
    }

    @Override
    public int fixConstraintViolations(Network network) {

        int indexOfSink = 0;
        for (Node node : network.getNodes()) {
            if (node instanceof Sink) {
                break;
            }
            indexOfSink++;
        }

        Matrix<Float> distances = network.distances();

        boolean hasFixes;
        int numberOfFixes = 0;
        do {
            if (numberOfFixes > 0) {
                log.info("fixes: " + numberOfFixes);
            }
            numberOfFixes = 0;
            hasFixes = false;
            Matrix<Double> commGraph = network.distances(network.getConnectivity());
            Matrix<Integer> commPaths = Matrix.ShortestDistancesAndPaths(commGraph);
            for (int i = 0; i < network.numberOfNodes(); ++i) {
                if (i == indexOfSink) {
                    continue;
                }

                List<Integer> shortestCommPath = Matrix.ShortestPath(i, indexOfSink, commPaths);

                if (shortestCommPath.contains(indexOfSink)) {
                    List<Node> shortestNodePath = new ArrayList<>();
                    for (Integer nodeIndex : shortestCommPath) {
                        shortestNodePath.add(network.getNode(nodeIndex));
                    }
                    network.getNode(i).setPathToSink(shortestNodePath);
                } else {
                    hasFixes = true;
                    numberOfFixes++;
                    double distanceToClosestNode = Double.POSITIVE_INFINITY;
                    for (int j = 0; j < network.getNodes().size(); ++j) {
                        if (j == i) {
                            continue;
                        }
                        double distance_ij = distances.get(i, j);
                        if (distance_ij < distanceToClosestNode
                                && distance_ij > network.getNode(i).getCommunicationRange()) {
                            distanceToClosestNode = distance_ij;
                        }
                    }
                    network.getNode(i).setCommunicationRange(distanceToClosestNode);
//                    break;
                }
            }
        } while (hasFixes);

        return numberOfFixes;
    }

    public static void main(String[] args) {

        double maxPosition = 2;
        Position initialPosition = new Position(0, 0);
        Position endingPosition = new Position(maxPosition, maxPosition);
        Network network = new Network(new Grid(initialPosition, endingPosition, 100),
                new HopCountModel());

        Position sinkPosition = new Position(maxPosition, maxPosition);
        double xStep = 2.0, yStep = 2.0;
        int nodes = 0;
        Random random = new Random(0);
        for (double x = initialPosition.get(Position.X); x <= endingPosition.get(Position.X);
                x += xStep) {
            for (double y = initialPosition.get(Position.Y); y <= endingPosition.get(Position.Y);
                    y += yStep) {
                Node node;
                Position position = new Position(x, y);
                if (position.equals(sinkPosition)) {
                    node = new Sink("sink", position);
                    node.setCommunicationRange(Double.POSITIVE_INFINITY);
                    node.setSensorRange(0.0);
                } else {
                    node = new Node("" + nodes, position);
                    node.setSensorRange(1.0);
                    node.setCommunicationRange(random.nextDouble() * maxPosition);
                }
                network.addNode(node);
                nodes++;
            }
        }
        System.out.println(network.distances(network.getConnectivity()));
        System.out.println("*******************");
        NetworkConstraintHandler constraint = new ConnectivityByCommRange();
        constraint.fixConstraintViolations(network);
        System.out.println(network.distances(network.getConnectivity()));
    }
}
