/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.movingpeaks;

import ecbenchmark.util.CommonMath;

/**
 *
 * @author jcrada
 */
public class PeakFunctionSphere implements PeakFunction {

    @Override
    public double f(double[] x, double[][] peaks, int peakIndex) {
        double dummy;

        dummy = (x[0] - peaks[peakIndex][0]) * (x[0] - peaks[peakIndex][0]);
        for (int j = 1; j < x.length; j++) {
            dummy += CommonMath.Pow2(x[j] - peaks[peakIndex][j]);
        }

        return peaks[peakIndex][x.length + 1] - dummy;

    }
}
