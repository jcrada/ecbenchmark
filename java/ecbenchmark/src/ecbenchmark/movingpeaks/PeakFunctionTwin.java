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
public class PeakFunctionTwin implements PeakFunction {

    @Override
    public double f(double[] x, double[][] peaks, int peakIndex) {
        int j;
        double maximum = Double.NEGATIVE_INFINITY, dummy;
        /* difference to first peak */
        /*static*/
        double[] twin_peak = {1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0};

        dummy = CommonMath.Pow2(x[0] - peaks[peakIndex][0]);
        for (j = 1; j < x.length; j++) {
            dummy += CommonMath.Pow2(x[j] - peaks[peakIndex][j]);
        }
        dummy =
                peaks[peakIndex][x.length
                + 1]
                - (peaks[peakIndex][x.length] * dummy);
        maximum = dummy;
        //System.out.println("j: "+j);
        dummy = CommonMath.Pow2(x[j] - (peaks[peakIndex][0] + twin_peak[0]));
        for (j = 1; j < x.length; j++) {
            dummy += CommonMath.Pow2(x[j] - (peaks[peakIndex][j] + twin_peak[0]));
        }
        dummy = peaks[peakIndex][x.length + 1] + twin_peak[x.length + 1]
                - ((peaks[peakIndex][x.length] + twin_peak[x.length]) * dummy);
        
        if (dummy > maximum) {
            maximum = dummy;
        }

        return maximum;

    }
}
