/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.movingpeaks;

import ecbenchmark.AbstractFunction;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import original.movingpeaks.movpeaks;

/**
 *
 * @author jcrada
 */
public class MovingPeaksTest {

    public static void main(String[] args) throws Exception {
        print();
        System.exit(0);

        int changes = 1000;
        DecimalFormat df = new DecimalFormat("#.###");
        Random random = new Random(1l);


        Scenario[] scenarios = {
            //            new Scenario(Scenario.DefaultScenario.ONE),
            new Scenario(Scenario.DefaultScenario.TWO), //            new Scenario(Scenario.DefaultScenario.THREE),
        };


        for (Scenario s : scenarios) {
            s.setPeakFunction(new PeakFunctionSphere());
            double[] x = new double[s.getNumberOfDimensions()];

            for (int i = 0; i < x.length; ++i) {
                x[i] = random.nextDouble();
            }

            MovingPeaks myMP = new MovingPeaks();
            myMP.setScenario(s);

            movpeaks theirMP = new movpeaks(s);
            theirMP.init_peaks();

            for (int i = 0; i < changes * s.getChangeFrequency(); ++i) {

                double mine = myMP.f(x);
                double theirs = theirMP.eval_movpeaks(x);

                if (mine - theirs != 0.0) {
                    System.out.println("ALERT");
                }

//                System.out.println(df.format(mine - theirs) + "\t"
//                        + df.format(mine) + "\t" + df.format(theirs));
            }


        }


    }

    public static void print() throws Exception {
//        AbstractFunction[] functions = {new F01(), new F02(), new F03()};
        Scenario[] scenarios = {
            //            new Scenario(Scenario.DefaultScenario.ONE),
            new Scenario(Scenario.DefaultScenario.TWO), //            new Scenario(Scenario.DefaultScenario.THREE),
        };
        MovingPeaks[] functions = new MovingPeaks[scenarios.length];
        {
            int i = 0;
            for (Scenario s : scenarios) {
                functions[i] = new MovingPeaks();
                functions[i].setScenario(s);
                ++i;
            }
        }

        int changes = 10;

        int sIndex = 0;
        for (MovingPeaks f : functions) {
            Scenario s = scenarios[sIndex++];
            MPFunction mpf = new MPFunction("F0" + 2, "Scenario", f);
            File file = new File("/tmp/" + mpf.getName() + ".R");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            System.out.println(file.getName() + " was created");
            BufferedWriter w = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < changes; ++i) {

                System.out.println("Change " + (i + 1));

                w.write(mpf.rString() + "\n\n");
                w.flush();
                f.changePeaks();
            }
            w.close();



        }


    }
}
