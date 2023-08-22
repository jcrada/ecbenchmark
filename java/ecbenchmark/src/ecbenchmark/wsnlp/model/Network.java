/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp.model;

import ecbenchmark.util.Matrix;
import ecbenchmark.wsnlp.constraint.Connectivity;
import ecbenchmark.wsnlp.constraint.HopCountModel;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import r.util.Functions;

/**
 *
 * @author jcrada
 */
public class Network {

    private List<Node> nodes = new ArrayList<>();
    private transient Grid grid;
    private Connectivity connectivity;

    public Network() {
    }

    public Network(Grid grid) {
        this.grid = grid;
    }

    public Network(Grid grid, Connectivity connectivity) {
        this(grid);
        this.connectivity = connectivity;
    }

    public Node getNode(int index) {
        return nodes.get(index);
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public int numberOfNodes() {
        return nodes.size();
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Connectivity getConnectivity() {
        return connectivity;
    }

    public void setConnectivity(Connectivity connectivity) {
        this.connectivity = connectivity;
    }
    
    public Matrix<Double> matrix(String characteristic){
        
        Method nodeMethod = null;
        try{
            nodeMethod =  Node.class.getMethod("get" + characteristic);
        } catch (NoSuchMethodException | SecurityException ex) {
            throw new RuntimeException(ex);
        }
        
        int cols = (int)Math.round(Math.sqrt(numberOfNodes()));
        Matrix<Double> result = new Matrix<>(cols);
        int row = 0, col = 0;
        for (Node node : getNodes()){
            if (col >= cols){
                row++;
                col = 0;
            }
            Double value = Double.POSITIVE_INFINITY;
            try{
                value = (Double) nodeMethod.invoke(node);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
            result.set(row, col, value);
            col++;
        }
        return result;
    }

    public Matrix<Float> distances() {
        int numberOfNodes = numberOfNodes();
        Matrix<Float> distances = new Matrix<>(numberOfNodes);
        for (int i = 0; i < numberOfNodes; ++i) {
            for (int j = i; j < numberOfNodes; ++j) {
                double distance = getNode(i).getPosition().euclideanDistanceTo(
                        getNode(j).getPosition());
                distances.matrix[i][j] = distance;
                distances.matrix[j][i] = distance;
            }
        }
        return distances;
    }

    public Matrix<Double> distances(Connectivity connectivity) {
        int numberOfNodes = numberOfNodes();
        Matrix<Double> distances = new Matrix<>(numberOfNodes);
        for (int i = 0; i < numberOfNodes; ++i) {
            for (int j = i; j < numberOfNodes; ++j) {
                distances.matrix[i][j] = connectivity.connectionDistance(getNode(i), getNode(j));
                distances.matrix[j][i] = connectivity.connectionDistance(getNode(j), getNode(i));
            }
        }
        return distances;
    }

    public static String rDefinitions() {
        String result = "";
        result += "def.grid.showSensorRange = F;\n";
        result += "def.grid.showCommRange = T;\n";
        return result;
    }

    public String rString() {
        String result = "require('grid');\n";
        double minX = getGrid().getMinPosition().get(Position.X);
        double maxX = getGrid().getMaxPosition().get(Position.X);
        double minY = getGrid().getMinPosition().get(Position.Y);
        double maxY = getGrid().getMaxPosition().get(Position.Y);

        result += "dev.new();\n";
        result += "network.viewport = plotViewport(c(3,3,3,3));\n";
        result += "pushViewport(network.viewport);\n";
        result += "grid.xaxis(); grid.yaxis();\n";

        List<Double> xNodes = new ArrayList<>();
        List<Double> yNodes = new ArrayList<>();
        List<Double> sensorRange = new ArrayList<>();
        List<Double> commRange = new ArrayList<>();
        Position sink = null;
        for (Node node : getNodes()) {
            if (node instanceof Sink) {
                sink = ((Sink) node).getPosition();
                continue;
            }
            xNodes.add(node.getPosition().get(Position.X));
            yNodes.add(node.getPosition().get(Position.Y));
            sensorRange.add(node.getSensorRange());
            commRange.add(node.getCommunicationRange());
        }
        result += "grid.points(" + Functions.Scale(xNodes, 0.0, 1.0, minX, maxX) + ",\n"
                + Functions.Scale(yNodes, 0.0, 1.0, minY, maxY) + ", pch=20);\n";
        result += "grid.points(" + Functions.Scale(sink.get(Position.X), 0.0, 1.0, minX, maxX) + ",\n"
                + Functions.Scale(sink.get(Position.Y), 0.0, 1.0, minY, maxY) + ", pch=22);\n";

        result += "if (def.grid.showSensorRange){\n";
        result += "grid.circle(" + Functions.Scale(xNodes, 0.0, 1.0, minX, maxX) + ",\n"
                + Functions.Scale(yNodes, 0.0, 1.0, minY, maxY) + ", \n"
                + Functions.Scale(sensorRange, 0.0, 1.0, minX, maxX) + ");\n";
        result += "}\n";

        result += "if (def.grid.showCommRange){\n";
        result += "grid.circle(" + Functions.Scale(xNodes, 0.0, 1.0, minX, maxX) + ",\n"
                + Functions.Scale(yNodes, 0.0, 1.0, minY, maxY) + ",\n "
                + Functions.Scale(commRange, 0.0, 1.0, minX, maxX) + ");\n";
        result += "}\n";
        return result;
    }
    
    @Override
    public String toString(){
        return common.Util.ToString(this);
    }

    public static void main(String[] args) {
        double maxPosition = 10;
        Position initialPosition = new Position(0, 0);
        Position endingPosition = new Position(maxPosition, maxPosition);
        Network network = new Network(new Grid(initialPosition, endingPosition, (int) maxPosition),
                new HopCountModel());

        Position sinkPosition = new Position(maxPosition / 2, maxPosition / 2);
        double xStep = 1.0, yStep = 1.0;
        int nodes = 0;
        for (double x = initialPosition.get(Position.X); x < endingPosition.get(Position.X);
                x += xStep) {
            for (double y = initialPosition.get(Position.Y); y < endingPosition.get(Position.Y);
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
                    node.setCommunicationRange(1.0);
                }
                network.addNode(node);
                nodes++;
            }
        }
        
        System.out.println(network.matrix("CommunicationRange"));
        
    }
}
