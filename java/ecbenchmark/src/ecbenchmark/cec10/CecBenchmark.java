/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import ecbenchmark.Benchmark;

/**
 *
 * @author jcrada
 */
public class CecBenchmark extends Benchmark {

    public CecBenchmark(int dimensions, int mValue) {
        super("cec10", "K. Tang, Xiaodong Li, P. N. Suganthan, Z. Yang and T. Weise, "
                + "Benchmark Functions for the CEC'2010 Special Session and Competition on Large Scale Global Optimization,"
                + "Technical Report, Nature Inspired Computation and Applications Laboratory, USTC, China,"
                + "http://nical.ustc.edu.cn/cec10ss.php");
        add(new F01(dimensions));
        add(new F02(dimensions));
        add(new F03(dimensions));
        add(new F04(dimensions,mValue));
        add(new F05(dimensions,mValue));
        add(new F06(dimensions,mValue));
        add(new F07(dimensions,mValue));
        add(new F08(dimensions,mValue));
        add(new F09(dimensions,mValue));
        add(new F10(dimensions,mValue));
        add(new F11(dimensions,mValue));
        add(new F12(dimensions,mValue));
        add(new F13(dimensions,mValue));
        add(new F14(dimensions,mValue));
        add(new F15(dimensions,mValue));
        add(new F16(dimensions,mValue));
        add(new F17(dimensions,mValue));
        add(new F18(dimensions,mValue));
        add(new F19(dimensions,mValue));
        add(new F20(dimensions,mValue));
    }
}
