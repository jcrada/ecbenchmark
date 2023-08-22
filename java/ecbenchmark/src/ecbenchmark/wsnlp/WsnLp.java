/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp;

import ecbenchmark.wsnlp.model.encoding.PositionEncoding;
import ecbenchmark.wsnlp.constraint.DiskUnitModel;
import ecbenchmark.wsnlp.model.Grid;
import ecbenchmark.wsnlp.model.Sink;
import ecbenchmark.wsnlp.model.Node;
import ecbenchmark.wsnlp.model.Network;
import ecbenchmark.wsnlp.model.Position;
import ecbenchmark.wsnlp.objective.SingleSinkHopCounter;
import ecbenchmark.wsnlp.objective.GridCoverage;
import ecbenchmark.wsnlp.objective.HopCounterWithDescStat;
import ecbenchmark.Optimization;
import ecbenchmark.util.stat.Mean;
import ecbenchmark.util.stat.Plus;
import ecbenchmark.util.stat.StandardDeviation;
import org.apache.log4j.Logger;
import r.util.Functions;

/**
 *
 * @author jcrada
 */
public class WsnLp {

    public static Logger log = Logger.getLogger(WsnLp.class);

    public static void main(String[] args) {
        Network network = new Network(
                new Grid(new Position(0, 0), new Position(100, 100), 1000),
                new DiskUnitModel());
        network.addNode(new Node("" + 0, new Position(10, 10)));
        network.addNode(new Node("" + 1, new Position(90, 10)));
//        network.addNode(new Node(2, new Position(10,90)));
        network.addNode(new Node("" + 2, new Position(20, 80)));
        network.addNode(new Node("" + 3, new Position(90, 90)));

        network.addNode(new Node("" + 4, new Position(30, 30)));
        network.addNode(new Node("" + 5, new Position(70, 30)));
        network.addNode(new Node("" + 6, new Position(30, 70)));
        network.addNode(new Node("" + 7, new Position(70, 70)));
        for (int i = 0; i < network.numberOfNodes(); ++i) {
            network.getNode(i).setSensorRange(4.0);
            network.getNode(i).setCommunicationRange(20.0);
        }

        Node sink = new Sink("" + 8, new Position(50, 50));
        sink.setCommunicationRange(Double.POSITIVE_INFINITY);
        sink.setSensorRange(0);
        network.addNode(sink);

        System.out.println(Functions.DefineScale());
        System.out.println(network.rDefinitions());
        System.out.println(network.rString());
        System.exit(9);

        NetworkMultiObjective problem = new NetworkMultiObjective();
        problem.setNetwork(network);
        problem.addObjective(new HopCounterWithDescStat(
                new SingleSinkHopCounter(),
                new Plus(new Mean(), new StandardDeviation())), Optimization.MINIMIZE);
        problem.addObjective(new GridCoverage(), Optimization.MINIMIZE);

        problem.setEncoding(new PositionEncoding());
        double[] encoding = new PositionEncoding().encode(network);

        for (int i = 0; i < 1000; ++i) {
            double[] objectives = problem.f(encoding);
            String out = "[ ";
            for (double o : objectives) {
                out += o + " ";
            }
            out += "]";
        }
        for (Node node : network.getNodes()) {
            log.info(node);
        }

//        log.info(out);



    }
}
