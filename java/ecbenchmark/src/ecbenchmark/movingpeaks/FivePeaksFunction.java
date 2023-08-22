/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.movingpeaks;

/**
 *
 * @author jcrada
 */
public class FivePeaksFunction implements PeakFunction {

    @Override
    public double f(double[] x, double[][] peaks, int peakIndex) {
        int i, j;
        double maximum = -100000.0, dummy;
        double[][] basis_peak = {{8.0, 64.0, 67.0, 55.0, 4.0, 0.1, 50.0}, {
                50.0, 13.0, 76.0, 15.0, 7.0, 0.1, 50.0}, {
                9.0, 19.0, 27.0, 67.0, 24.0, 0.1, 50.0}, {
                66.0, 87.0, 65.0, 19.0, 43.0, 0.1, 50.0}, {
                76.0, 32.0, 43.0, 54.0, 65.0, 0.1, 50.0},};
        for (i = 0; i < 5; i++) {
            dummy = (x[0] - basis_peak[i][0]) * (x[0] - basis_peak[i][0]);
            for (j = 1; j < x.length; j++) {
                dummy += (x[j] - basis_peak[i][j]) * (x[j] - basis_peak[i][j]);
            }
            dummy = basis_peak[i][x.length + 1]
                    - (basis_peak[i][x.length] * dummy);
            if (dummy > maximum) {
                maximum = dummy;
            }
        }
        return maximum;

    }
}
