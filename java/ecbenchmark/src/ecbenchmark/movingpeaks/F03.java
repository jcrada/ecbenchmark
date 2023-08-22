/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.movingpeaks;

/**
 *
 * @author jcrada
 */
public class F03 extends MPFunction{
    
    public F03() {
        Scenario s = new Scenario(Scenario.DefaultScenario.THREE);
        MovingPeaks f = new MovingPeaks();
        f.setScenario(s);
        setFunction(f);

        setName("F03");
        setDescription("Scenario3");
        setDimensions(2);
        setMinimum(s.getMinCoordinate());
        setMaximum(s.getMaxCoordinate());
        setMinimization(false);

    }
}
