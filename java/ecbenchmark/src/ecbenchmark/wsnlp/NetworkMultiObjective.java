/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp;

import ecbenchmark.MultiObjectiveFunction;
import ecbenchmark.Optimization;
import ecbenchmark.util.Pair;
import ecbenchmark.wsnlp.model.Network;
import ecbenchmark.wsnlp.model.encoding.Encoding;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author jcrada
 */
public class NetworkMultiObjective implements MultiObjectiveFunction {

    private static final Logger log = Logger.getLogger(NetworkMultiObjective.class);
    private Network network;
    private Encoding encoding;
    private List<Pair<NetworkObjective, Optimization>> objectives = new ArrayList<>();
    private List<NetworkObjective> indicators = new ArrayList<>();
    private List<NetworkConstraintHandler> constraints = new ArrayList<>();

    public NetworkMultiObjective() {
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public void addObjective(NetworkObjective objective, Optimization optimization) {
        objectives.add(new Pair<>(objective, optimization));
    }

    public NetworkObjective getObjective(int index) {
        return objectives.get(index).first;
    }

    public Optimization getOptimization(int index) {
        return objectives.get(index).second;
    }

    public List<Pair<NetworkObjective, Optimization>> getObjectives() {
        return this.objectives;
    }

    public List<NetworkObjective> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<NetworkObjective> indicators) {
        this.indicators = indicators;
    }

    public void addIndicator(NetworkObjective indicator) {
        this.indicators.add(indicator);
    }

    public List<NetworkConstraintHandler> getConstraints() {
        return this.constraints;
    }

    public void addConstraint(NetworkConstraintHandler constraint) {
        this.constraints.add(constraint);
    }

    public Encoding getEncoding() {
        return encoding;
    }

    public void setEncoding(Encoding encoding) {
        this.encoding = encoding;
    }

    @Override
    public double[] f(final double[] x) {
        double[] result = new double[objectives.size()];
        int index = 0;
        getEncoding().decode(x, network);
        for (Pair<NetworkObjective, Optimization> o : objectives) {
            result[index++] = o.first.f(network);
        }
        return result;
    }

    public double[] performance(final double[] x) {
        double[] result = new double[indicators.size()];
        int index = 0;
        getEncoding().decode(x, network);
        for (NetworkObjective performance : indicators) {
            result[index++] = performance.f(network);
        }
        return result;
    }

    public double[] fixConstraints(final double[] x) {
        getEncoding().decode(x, getNetwork());
        int fixes = 0;
        for (NetworkConstraintHandler constraint : getConstraints()) {
            fixes += constraint.fixConstraintViolations(getNetwork());
        }
//        log.info("fixes: " + fixes);

        return fixes == 0 ? x : getEncoding().encode(getNetwork());
    }
}
