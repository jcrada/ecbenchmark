/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.movingpeaks;

import ecbenchmark.AbstractFunction;
import ecbenchmark.util.CommonMath;
import java.util.ArrayList;
import java.util.List;
import r.util.Datatype;

/**
 *
 * @author jcrada
 */
public class MPFunction extends AbstractFunction {

    private MovingPeaks function;
    static int index = 1;

    public MPFunction() {
    }

    public MPFunction(String name, String description, MovingPeaks function) {
        this.function = function;
        setName(name);
        setDescription(description);
        setDimensions(function.getScenario().getNumberOfDimensions());
        setMinimum(function.getScenario().getMinCoordinate());
        setMaximum(function.getScenario().getMaxCoordinate());
        setMinimization(false);
    }

    @Override
    public double f(double[] x) {
        return function.f(x);
    }

    @Override
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
                z.add(function.f(input, false));
            }
        }

        r += getName() + "_z = matrix(" + Datatype.C(z) + ", " + x_y.size() + ", " + x_y.size() + ", "
                + "byrow=T);\n\n";

        r += "png('/tmp/" + getName() + "-" + (++index < 10 ? "0" + index : "" + index) + ".png'); par(mar=c(0,0,0,0));\n";
        r += "image(" + getName() + "_x, " + getName() + "_y, " + getName() + "_z,"
                + "col=cols(255),axes=F,xlab=NA,ylab=NA,asp=1,main='" + getName() + "');\n";
        r += "dev.off();\n";

        return r;
    }

    public MovingPeaks getFunction() {
        return function;
    }

    public void setFunction(MovingPeaks function) {
        this.function = function;
    }
}
