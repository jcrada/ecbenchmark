/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import ecbenchmark.AbstractFunction;
import java.util.Random;

/**
 *
 * @author jcrada
 */
public abstract class CecFunction extends AbstractFunction {

    protected Random randomizer = null;
    protected int mValue = 50;

    public CecFunction(String name, String description, int dimensions,
            double minimum, double maximum, boolean minimization, Random randomizer) {
        super(name, description, dimensions, minimum, maximum, minimization);
        this.randomizer = randomizer;
    }

    public CecFunction(String name, String description, int dimensions,
            double minimum, double maximum, boolean minimization, Random randomizer,
            int mValue) {
        this(name, description, dimensions, minimum, maximum, minimization, randomizer);
        this.mValue = mValue;
    }

    public void setRandomizer(Random random) {
        this.randomizer = random;
    }

    public Random getRandomizer() {
        return this.randomizer;
    }

    public void setMValue(int m) {
        this.mValue = m;
    }

    public int getMValue() {
        return this.mValue;
    }
}
