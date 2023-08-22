/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp.objective;

import ecbenchmark.util.stat.StatOperator;
import ecbenchmark.wsnlp.NetworkObjective;
import ecbenchmark.wsnlp.model.Network;
import ecbenchmark.wsnlp.model.Node;
import ecbenchmark.wsnlp.model.Sink;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jcrada
 */
public class GenericStatistic implements NetworkObjective {
    
    private String variable;
    private StatOperator statistic;
    
    public GenericStatistic(String variable, StatOperator statistic) {
        this.variable = variable;
        this.statistic = statistic;
    }
    
    @Override
    public double f(Network network) {
        Method nodeMethod = null;
        try {
            nodeMethod = Node.class.getMethod("get" + variable);
        } catch (NoSuchMethodException | SecurityException ex) {
            throw new RuntimeException(ex);
        }
        
        List<Double> variables = new ArrayList<>();
        for (Node node : network.getNodes()) {
            if (node instanceof Sink) continue;
            try {
                variables.add((Double) nodeMethod.invoke(node));
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
        }
        
        return statistic.compute(variables);
    }
}
