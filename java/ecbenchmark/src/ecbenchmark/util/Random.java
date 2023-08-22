/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.util;

/**
 *
 * @author jcrada
 */
public interface Random {

    void setSeed(long seed);

    long getSeed();

    double nextDouble();

    boolean nextBool();

    int nextInt(int max);

    int nextInt();

    long nextLong();
}
