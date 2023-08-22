/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.movingpeaks;

import ecbenchmark.Function;
import java.util.Random;

/**
 *
 * @author jcrada
 */
public class MovingPeaks implements Function {

    private Scenario scenario;
    private Random randomUniform;
    private Random randomGaussian;
    private double[] shift;
    private double[][] peaks;
    private double[][] previousPeaks;
    private int numberOfEvaluations = 0;
    private boolean recentChange = true;

    public MovingPeaks() {
    }

    protected void loadScenario() {
        randomGaussian = new Random(scenario.getSeed() + 1);
        randomUniform = new Random(scenario.getSeed() + 1);

        shift = new double[scenario.getNumberOfDimensions()];
        peaks = new double[scenario.getNumberOfPeaks()][];
        previousPeaks = new double[scenario.getNumberOfPeaks()][];
        for (int i = 0; i < scenario.getNumberOfPeaks(); i++) {
            peaks[i] = new double[scenario.getNumberOfDimensions() + 2];
            previousPeaks[i] = new double[scenario.getNumberOfDimensions()];
        }

        for (int i = 0; i < scenario.getNumberOfPeaks(); i++) {
            for (int j = 0; j < scenario.getNumberOfDimensions(); j++) {
                peaks[i][j] = 100.0 * randomUniform.nextDouble();
                previousPeaks[i][j] = randomUniform.nextDouble() - 0.5;
            }
        }


        if (scenario.getStandardHeight() <= 0.0) {
            for (int i = 0; i < scenario.getNumberOfPeaks(); i++) {
                peaks[i][scenario.getNumberOfDimensions() + 1] =
                        (scenario.getMaxHeight() - scenario.getMinHeight()) * randomUniform.nextDouble()
                        + scenario.getMinHeight();
            }
        } else {
            for (int i = 0; i < scenario.getNumberOfPeaks(); i++) {
                peaks[i][scenario.getNumberOfDimensions() + 1] = scenario.getStandardHeight();
            }
        }

        if (scenario.getStandardWidth() <= 0.0) {
            for (int i = 0; i < scenario.getNumberOfPeaks(); i++) {
                peaks[i][scenario.getNumberOfDimensions()] =
                        (scenario.getMaxWidth() - scenario.getMinWidth())
                        * randomUniform.nextDouble() + scenario.getMinWidth();
            }
        } else {
            for (int i = 0; i < scenario.getNumberOfPeaks(); i++) {
                peaks[i][scenario.getNumberOfDimensions()] = scenario.getStandardWidth();
            }
        }
    }

    public double f(double[] x, boolean countEvaluation) {
        if (countEvaluation) {
            if ((scenario.getChangeFrequency() > 0)
                    && (numberOfEvaluations % scenario.getChangeFrequency() == 0)) {
                changePeaks();
            }
            ++numberOfEvaluations;
        }
        double maximum = Double.NEGATIVE_INFINITY, dummy = 0;

        for (int i = 0; i < scenario.getNumberOfPeaks(); i++) {
            dummy = scenario.getPeakFunction().f(x, this.peaks, i);
            if (dummy > maximum) {
                maximum = dummy;
            }
        }

        if (scenario.getBasisFunction() != null) {
            dummy = scenario.getBasisFunction().f(x);
            /* If value of basis function is higher return it */
            if (maximum < dummy) {
                maximum = dummy;
            }
        }
        return (maximum);
    }

    public void changePeaks() {
        double sum = 0, sum2 = 0, offset = 0;
        for (int i = 0; i < scenario.getNumberOfPeaks(); i++) {
            /* shift peak locations */
            sum = 0.0;
            for (int j = 0; j < scenario.getNumberOfDimensions(); j++) {
                shift[j] = randomUniform.nextDouble() - 0.5;
                sum += shift[j] * shift[j];
            }
            if (sum > 0.0) {
                sum = scenario.getVlength() / Math.sqrt(sum);
            } else {/* only in case of rounding errors */
                sum = 0.0;
            }

            sum2 = 0.0;
            for (int j = 0; j < scenario.getNumberOfDimensions(); j++) {
                shift[j] =
                        sum * (1.0 - scenario.getDirectionMovement()) * shift[j]
                        + scenario.getDirectionMovement() * previousPeaks[i][j];
                sum2 += shift[j] * shift[j];
            }
            if (sum2 > 0.0) {
                sum2 = scenario.getVlength() / Math.sqrt(sum2);
            } else /* only in case of rounding errors */ {
                sum2 = 0.0;
            }

            for (int j = 0; j < scenario.getNumberOfDimensions(); j++) {
                shift[j] *= sum2;
                previousPeaks[i][j] = shift[j];
                if ((peaks[i][j] + previousPeaks[i][j]) < scenario.getMinCoordinate()) {
                    peaks[i][j] = 2.0 * scenario.getMinCoordinate() - peaks[i][j]
                            - previousPeaks[i][j];
                    previousPeaks[i][j] *= -1.0;
                } else if ((peaks[i][j] + previousPeaks[i][j]) > scenario.getMaxCoordinate()) {
                    peaks[i][j] =
                            2.0 * scenario.getMaxCoordinate()
                            - peaks[i][j]
                            - previousPeaks[i][j];
                    previousPeaks[i][j] *= -1.0;
                } else {
                    peaks[i][j] += previousPeaks[i][j];
                }
            }
            /* change peak width */
            int j = scenario.getNumberOfDimensions();
            offset = randomGaussian.nextGaussian() * scenario.getWidthSeverity();
            if ((peaks[i][j] + offset) < scenario.getMinWidth()) {
                peaks[i][j] = 2.0 * scenario.getMinWidth() - peaks[i][j] - offset;
            } else if ((peaks[i][j] + offset) > scenario.getMaxWidth()) {
                peaks[i][j] = 2.0 * scenario.getMaxWidth() - peaks[i][j] - offset;
            } else {
                peaks[i][j] += offset;
            }
            /* change peak height */
            j++;
            offset = scenario.getHeightSeverity() * randomGaussian.nextGaussian();
            if ((peaks[i][j] + offset) < scenario.getMinHeight()) {
                peaks[i][j] = 2.0 * scenario.getMinHeight() - peaks[i][j] - offset;
            } else if ((peaks[i][j] + offset) > scenario.getMaxHeight()) {
                peaks[i][j] = 2.0 * scenario.getMaxHeight() - peaks[i][j] - offset;
            } else {
                peaks[i][j] += offset;
            }
        }
        
        setRecentChange(true);
    }

    @Override
    public double f(double[] x) {
        return f(x, true);
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
        loadScenario();
    }

    public Random getRandomUniform() {
        return randomUniform;
    }

    public void setRandomUniform(Random randomUniform) {
        this.randomUniform = randomUniform;
    }

    public Random getRandomGaussian() {
        return randomGaussian;
    }

    public void setRandomGaussian(Random randomGaussian) {
        this.randomGaussian = randomGaussian;
    }

    public int getNumberOfEvaluations() {
        return numberOfEvaluations;
    }

    public void setNumberOfEvaluations(int numberOfEvaluations) {
        this.numberOfEvaluations = numberOfEvaluations;
    }

    public boolean isRecentChange() {
        return recentChange;
    }

    public void setRecentChange(boolean recentChange) {
        this.recentChange = recentChange;
    }
}
