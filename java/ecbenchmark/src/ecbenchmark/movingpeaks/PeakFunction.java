/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.movingpeaks;

/**
 *
 * @author jcrada
 */
public interface PeakFunction {
    
    public double f(final double[] x, final double peaks[][], int peak);
}
