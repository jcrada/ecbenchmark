/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.wsnlp.model;

import ecbenchmark.util.CommonMath;
import org.apache.log4j.Logger;

/**
 *
 * @author jcrada
 */
public class Grid {
    private static final Logger log = Logger.getLogger(Grid.class);

    private int xSamples, ySamples;
    private Position minPosition, maxPosition;
    private boolean[][] coveredGrid;
    private int cellsCovered;

    public Grid(Position minPosition, Position maxPosition) {
        this.minPosition = minPosition;
        this.maxPosition = maxPosition;
    }

    public Grid(Position minPosition, Position maxPosition, int samples) {
        this(minPosition, maxPosition);
        this.xSamples = samples;
        this.ySamples = samples;
    }

    public Grid(Position minPosition, Position maxPosition, int xSamples, int ySamples) {
        this(minPosition, maxPosition);
        this.xSamples = xSamples;
        this.ySamples = ySamples;
    }

    public void clear() {
        this.coveredGrid = new boolean[xSamples][ySamples];
        this.cellsCovered = 0;
    }

    public Position getMinPosition() {
        return minPosition;
    }

    public void setMinPosition(Position minPosition) {
        this.minPosition = minPosition;
    }

    public Position getMaxPosition() {
        return maxPosition;
    }

    public void setMaxPosition(Position maxPosition) {
        this.maxPosition = maxPosition;
    }

    public void applyCoverage(Node node) {
        int nodeX = (int) Math.round(CommonMath.Scale(minPosition.get(Position.X), maxPosition.get(Position.X),
                node.getPosition().get(Position.X), 0, xSamples));
        int nodeY = (int) Math.round(CommonMath.Scale(minPosition.get(Position.Y), maxPosition.get(Position.Y),
                node.getPosition().get(Position.Y), 0, ySamples));

        int sensorX = (int) Math.round(CommonMath.Scale(minPosition.get(Position.X), maxPosition.get(Position.X),
                node.getSensorRange(), 0, xSamples));
        int sensorY = (int) Math.round(CommonMath.Scale(minPosition.get(Position.Y), maxPosition.get(Position.Y),
                node.getSensorRange(), 0, ySamples));

        for (int x = nodeX - sensorX; x < nodeX + sensorX; ++x) {
            if (x < 0 || x >= xSamples) {
                continue;
            }
            for (int y = nodeY - sensorY; y < nodeY + sensorY; ++y) {
                if (y < 0 || y >= ySamples) {
                    continue;
                }
                if (!coveredGrid[x][y]) {
                    cellsCovered++;
                }
                coveredGrid[x][y] = true;
            }
        }
    }

    public double coveredArea() {
        double base = (maxPosition.get(Position.X) - minPosition.get(Position.X)) / xSamples;
        double height = (maxPosition.get(Position.Y) - minPosition.get(Position.Y)) / ySamples;
        return cellsCovered * base * height;
    }
}
