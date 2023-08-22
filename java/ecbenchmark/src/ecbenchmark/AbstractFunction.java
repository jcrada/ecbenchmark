/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark;

import ecbenchmark.util.CommonMath;
import java.util.ArrayList;
import java.util.List;
import r.util.Datatype;

/**
 *
 * @author jcrada
 */
public abstract class AbstractFunction implements Function {

    protected String name = "", description = "";
    protected int dimensions = 1;
    protected double minimum = Double.NEGATIVE_INFINITY, maximum = Double.POSITIVE_INFINITY;
    protected boolean minimization = true;
    
    public AbstractFunction() {
    }

    public AbstractFunction(String name, String description, int dimensions) {
        this.name = name;
        this.description = description;
        this.dimensions = dimensions;
    }

    public AbstractFunction(String name, String description, int dimensions,
            double minimum, double maximum) {
        this(name, description, dimensions);
        this.minimum = minimum;
        this.maximum = maximum;
        this.minimization = true;
    }

    public AbstractFunction(String name, String description, int dimensions,
            double minimum, double maximum, boolean minimization) {
        this(name, description, dimensions, minimum, maximum);
        this.minimization = minimization;
    }

    

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDimensions(int dimensions) {
        this.dimensions = dimensions;
    }

    public int getDimensions() {
        return this.dimensions;
    }

    public void setMinimum(double min) {
        this.minimum = min;
    }

    public double getMinimum() {
        return this.minimum;
    }

    public void setMaximum(double max) {
        this.maximum = max;
    }

    public double getMaximum() {
        return this.maximum;
    }

    public void setDomain(double min, double max) {
        setMinimum(min);
        setMaximum(max);
    }

    public void setMinimization(boolean min) {
        this.minimization = min;
    }

    public boolean isMinimization() {
        return this.minimization;
    }

    public void setMaximization(boolean max) {
        this.minimization = !max;
    }

    public boolean isMaximization() {
        return !this.minimization;
    }


    public double worstFitness() {
        return isMinimization() ? Double.POSITIVE_INFINITY
                : Double.NEGATIVE_INFINITY;
    }

    boolean isBetter(double fitness_a, double fitness_b) {
        return isMinimization()
                ? fitness_a < fitness_b
                : fitness_a > fitness_b;
    }

    boolean isWorse(double fitness_a, double fitness_b) {
        return isMinimization()
                ? fitness_a > fitness_b
                : fitness_a < fitness_b;
    }

    @Override
    public String toString() {
        return getName() + "[dimensions= " + getDimensions() + "; "
                + "(" + getMinimum() + ", " + getMaximum() + "); "
                + "minimization=" + isMinimization() + "]";
    }

    public String rString() {
        String r = "";
        r += "cols = colorRampPalette(c('black','white'));\n";
        List<Double> x_y = new ArrayList<Double>();
        int samples = 100;
        for (int i = 1; i <= samples; ++i) {
            x_y.add(CommonMath.Scale(1.0, samples, i, getMinimum(), getMaximum()));
        }
        r += getName() + "_x = " + getName() + "_y = " + Datatype.C(x_y) + ";\n";

        List<Double> z = new ArrayList<Double>();
        for (int i = 0; i < x_y.size(); ++i) {
            for (int j = 0; j < x_y.size(); ++j) {
                double[] input = new double[2];
                input[0] = x_y.get(i);
                input[1] = x_y.get(j);
                z.add(f(input));
            }
        }

        r += getName() + "_z = matrix(" + Datatype.C(z) + ", " + x_y.size() + ", " + x_y.size() + ", "
                + "byrow=T);\n\n";
        r += "image(" + getName() + "_x, " + getName() + "_y, " + getName() + "_z,"
                + "col=cols(255),axes=F,xlab=NA,ylab=NA,asp=1,main='" + getName() + "');\n";

        return r;
    }
}
