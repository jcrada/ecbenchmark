/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp.model;

import java.util.Arrays;

/**
 *
 * @author jcrada
 */
public class Position {

    public static final int X = 0;
    public static final int Y = 1;
    public static final int Z = 2;
    double[] coordinates;

    public Position(double x, double y) {
        coordinates = new double[2];
        coordinates[X] = x;
        coordinates[Y] = y;
    }

    public Position(double x, double y, double z) {
        coordinates = new double[3];
        coordinates[X] = x;
        coordinates[Y] = y;
        coordinates[Z] = z;
    }

    public double[] coordinates() {
        return coordinates;
    }

    public double get(int axis) {
        return coordinates[axis];
    }

    public void set(int axis, double value) {
        coordinates[axis] = value;
    }

    public int numberOfDimensions() {
        return coordinates.length;
    }

    public boolean is2d() {
        return coordinates.length == 2;
    }

    public boolean is3d() {
        return coordinates.length == 3;
    }

    public double euclideanDistanceTo(Position other) {
        double result = 0;
        for (int i = 0; i < numberOfDimensions(); ++i) {
            result += (this.coordinates[i] - other.coordinates[i])
                    * (this.coordinates[i] - other.coordinates[i]);
        }
        return Math.sqrt(result);
    }

    @Override
    public String toString() {
        String result = "(" + get(X) + ";" + get(Y)
                + (is3d() ? "; " + get(Z) : "") + ")";
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Position)) {
            return false;
        }
        Position other = (Position) obj;
        if (this.numberOfDimensions() != other.numberOfDimensions()) {
            return false;
        }
        boolean result = true;
        for (int i = 0; i < this.numberOfDimensions(); ++i) {
            result &= Double.compare(this.coordinates[i], other.coordinates[i]) == 0;
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Arrays.hashCode(this.coordinates);
        return hash;
    }
}
