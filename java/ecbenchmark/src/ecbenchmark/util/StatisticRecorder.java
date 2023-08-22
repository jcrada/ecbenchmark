/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jcrada
 */
public class StatisticRecorder {

    private String parameter = "";
    private List<Double> minimum = new ArrayList<>();
    private List<Double> mean = new ArrayList<>();
    private List<Double> maximum = new ArrayList<>();
    private int currentIteration = 0;
    private List<Double> currentMean = new ArrayList<>();

    public StatisticRecorder() {
    }

    public StatisticRecorder(String parameter) {
        this.parameter = parameter;
    }

    public void record(double value) {
        if (minimum.size() <= currentIteration) {
            minimum.add(value);
            maximum.add(value);
        } else {
            if (value < minimum.get(currentIteration)) {
                minimum.set(currentIteration, value);
            }
            if (value > maximum.get(currentIteration)) {
                maximum.set(currentIteration, value);
            }
        }
        currentMean.add(value);
    }

    public void next() {
        currentIteration++;
        mean.add(CommonMath.Mean(currentMean));
        currentMean = new ArrayList<>();
    }

    public String rString() {
        String result = "min" + parameter + "=" + r.util.Datatype.C(minimum) + ";\n";
        result += "mean" + parameter + "=" + r.util.Datatype.C(mean) + ";\n";
        result += "max" + parameter + "=" + r.util.Datatype.C(maximum) + ";\n";
        return result;

    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
