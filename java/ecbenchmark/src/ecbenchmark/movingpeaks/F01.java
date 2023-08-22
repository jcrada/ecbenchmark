/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.movingpeaks;

/**
 *
 * @author jcrada
 */
public class F01 extends MPFunction {

    public F01() {
        Scenario s = new Scenario(Scenario.DefaultScenario.ONE);
        MovingPeaks f = new MovingPeaks();
        f.setScenario(s);
        setFunction(f);

        setName("F01");
        setDescription("Scenario1");
        setDimensions(2);
        setMinimum(s.getMinCoordinate());
        setMaximum(s.getMaxCoordinate());
        setMinimization(false);

    }
}
