/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.movingpeaks;

import ecbenchmark.Function;

/**
 *
 * @author jcrada
 */
public class Scenario {

    private int changeFrequency = 5000;
    private int numberOfDimensions = 5, numberOfPeaks = 5;
    private long seed = 1l;
    private double heightSeverity = 7.0, widthSeverity = 0.01;
    private double minCoordinate = 0.0, maxCoordinate = 100.0;
    private double minHeight = 30.0, maxHeight = 70.0, standardHeight = 50.0;
    private double minWidth = 0.0001, maxWidth = 0.2, standardWidth = 0.1;
    private double vlength = 10.0;
    private double directionMovement = 0.0;
    private PeakFunction peakFunction = new PeakFunctionCone();
    private Function basisFunction = null;

    public enum DefaultScenario {

        NONE, ONE, TWO, THREE;
    }

    public Scenario() {
        this(DefaultScenario.NONE);
    }

    public Scenario(DefaultScenario scenario) {
        switch (scenario) {
            case ONE:
                loadScenarioOne();
                break;
            case TWO:
                loadScenarioTwo();
                break;
            case THREE:
                loadScenarioThree();
                break;
            default:
                break;

        }
    }

    private void loadScenarioOne() {
        setSeed(1l);
        setNumberOfPeaks(5);
        setNumberOfDimensions(5);

        setMinHeight(30);
        setMaxHeight(70);
        setStandardHeight(50);
        setMinWidth(0.0001);
        setMaxWidth(0.2);
        setStandardWidth(0.1);

        setMinCoordinate(0.0);
        setMaxCoordinate(100.0);
        setVlength(1.0);

        setHeightSeverity(7.0);
        setWidthSeverity(0.01);
        setBasisFunction(null);
        setDirectionMovement(0.0);

        setChangeFrequency(5000);
        setPeakFunction(new PeakFunctionOne());

    }

    private void loadScenarioTwo() {
        setSeed(1l);
        setNumberOfPeaks(105);
        setNumberOfDimensions(5);

        setMinHeight(30);
        setMaxHeight(70);
        setStandardHeight(50);
        setMinWidth(1.0);
        setMaxWidth(12.0);
        setStandardWidth(0.0);

        setMinCoordinate(0.0);
        setMaxCoordinate(100.0);
        setVlength(1.5);

        setHeightSeverity(7.0);
        setWidthSeverity(1.0);
        setBasisFunction(null);
        setDirectionMovement(0.5);

        setChangeFrequency(3000);
        setPeakFunction(new PeakFunctionCone());
    }

    private void loadScenarioThree() {
        setSeed(1l);
        setNumberOfPeaks(50);
        setNumberOfDimensions(5);

        setMinHeight(30);
        setMaxHeight(70);
        setStandardHeight(0);
        setMinWidth(1.0);
        setMaxWidth(12.0);
        setStandardWidth(0.0);

        setMinCoordinate(0.0);
        setMaxCoordinate(100.0);
        setVlength(1.0);

        setHeightSeverity(1.0);
        setWidthSeverity(0.5);

        setBasisFunction(new Function() {
            @Override
            public double f(double[] x) {
                return 0.0;
            }
        });
        setDirectionMovement(0.5);

        setChangeFrequency(1000);
        setPeakFunction(new PeakFunctionCone());
    }

    public int getChangeFrequency() {
        return changeFrequency;
    }

    public void setChangeFrequency(int changeFrequency) {
        this.changeFrequency = changeFrequency;
    }

    public int getNumberOfDimensions() {
        return numberOfDimensions;
    }

    public void setNumberOfDimensions(int numberOfDimensions) {
        this.numberOfDimensions = numberOfDimensions;
    }

    public int getNumberOfPeaks() {
        return numberOfPeaks;
    }

    public void setNumberOfPeaks(int numberOfPeaks) {
        this.numberOfPeaks = numberOfPeaks;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public double getHeightSeverity() {
        return heightSeverity;
    }

    public void setHeightSeverity(double heightSeverity) {
        this.heightSeverity = heightSeverity;
    }

    public double getWidthSeverity() {
        return widthSeverity;
    }

    public void setWidthSeverity(double widthSeverity) {
        this.widthSeverity = widthSeverity;
    }

    public double getMinCoordinate() {
        return minCoordinate;
    }

    public void setMinCoordinate(double minCoordinate) {
        this.minCoordinate = minCoordinate;
    }

    public double getMaxCoordinate() {
        return maxCoordinate;
    }

    public void setMaxCoordinate(double maxCoordinate) {
        this.maxCoordinate = maxCoordinate;
    }

    public double getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(double minHeight) {
        this.minHeight = minHeight;
    }

    public double getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(double maxHeight) {
        this.maxHeight = maxHeight;
    }

    public double getStandardHeight() {
        return standardHeight;
    }

    public void setStandardHeight(double standardHeight) {
        this.standardHeight = standardHeight;
    }

    public double getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(double minWidth) {
        this.minWidth = minWidth;
    }

    public double getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(double maxWidth) {
        this.maxWidth = maxWidth;
    }

    public double getStandardWidth() {
        return standardWidth;
    }

    public void setStandardWidth(double standardWidth) {
        this.standardWidth = standardWidth;
    }

    public double getVlength() {
        return vlength;
    }

    public void setVlength(double vlength) {
        this.vlength = vlength;
    }

    public double getDirectionMovement() {
        return directionMovement;
    }

    public void setDirectionMovement(double directionMovement) {
        this.directionMovement = directionMovement;
    }

    public PeakFunction getPeakFunction() {
        return peakFunction;
    }

    public void setPeakFunction(PeakFunction peakFunction) {
        this.peakFunction = peakFunction;
    }

    public Function getBasisFunction() {
        return basisFunction;
    }

    public void setBasisFunction(Function basisFunction) {
        this.basisFunction = basisFunction;
    }
}