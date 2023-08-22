/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark;

/**
 *
 * @author jcrada
 */
public class CustomFunction extends AbstractFunction {

    private Function function;

    public CustomFunction() {
        super();
    }

    public CustomFunction(String name,
            String description, int dimensions, double min_domain,
            double max_domain, boolean minimization) {
        super(name, description, dimensions, min_domain, max_domain, minimization);
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    @Override
    public double f(double[] x) {
        return function.f(x);
    }
    
    
    
}
