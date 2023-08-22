/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark;

import java.util.Comparator;

/**
 *
 * @author jcrada
 */
public enum Optimization implements Comparator<Comparable> {

    MINIMIZE, MAXIMIZE;

    @Override
    public int compare(Comparable a, Comparable b) {
        int result = a.compareTo(b);
        //if a > b then result = 1;
        //if a < b then result = -1;
        //else result = 0;

        if (this == MINIMIZE) {
            return result * -1;
        }
        return result;
    }

    public static void main(String[] args) {
        Optimization o = MINIMIZE;
        System.out.println(o.compare(3.0, 4.0));
        
//        System.out.println(x.compareTo(y));
    }
}
