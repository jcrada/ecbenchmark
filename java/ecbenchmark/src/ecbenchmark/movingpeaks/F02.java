/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.movingpeaks;

/**
 *
 * @author jcrada
 */
public class F02 extends MPFunction {

    public F02() {
        Scenario s = new Scenario(Scenario.DefaultScenario.TWO);
        MovingPeaks f = new MovingPeaks();
        f.setScenario(s);
        setFunction(f);

        setName("F02");
        setDescription("Scenario2");
        setDimensions(2);
        setMinimum(s.getMinCoordinate());
        setMaximum(s.getMaxCoordinate());
        setMinimization(false);

    }
}
