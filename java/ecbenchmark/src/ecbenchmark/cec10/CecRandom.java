/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.cec10;

import java.util.Random;

/**
 *
 * @author jcrada
 */
public class CecRandom extends Random {

    /** use serialVersionUID y */
    static final long serialVersionUID = 1;
    /** M */
    public static final long M = 0x5DEECE66DL;
    /** a */
    public static final long A = 0xBL;
    /** mask */
    public static final long MASK = (1L << 48) - 1;
    /** The randomization seed */
    private long m_seed;
    /** the internal variable holding the next gaussian number */
    private double m_nextNextGaussian;
    /**
     * the internal variable indicating whether there is a next gaussian
     * number stored
     */
    private boolean m_haveNextNextGaussian = false;

    /**
     * Creates a new random number generator.
     */
    public CecRandom() {
        this(0);
    }

    /**
     * Creates a new random number generator using a single {@code long}
     * seed.
     * 
     * @param seed
     *          the initial seed
     */
    public CecRandom(final long seed) {
        super(seed);
        this.m_seed = seed;
    }

    /**
     * Sets the seed of this random number generator using a single {@code
     * long} seed.
     * 
     * @param seed
     *          the new seed
     */
    @Override
    public final void setSeed(long seed) {
        this.m_seed = seed;
        super.setSeed(seed);
    }

    /**
     * Generates the next pseudorandom number.
     * 
     * @param bits
     *          random bits
     * @return the random bits
     */
    @Override
    public final int next(final int bits) {
        final long s;

        this.m_seed = s = (((this.m_seed * M) + A) & MASK);
        return (int) (s >>> (48 - bits));
    }

    /**
     * Returns a pseudorandom, uniformly distributed {@code int} value
     * between 0 (inclusive) and the specified value (exclusive), drawn from
     * this random number generator's sequence.
     * 
     * @param n
     *          the bound on the random number to be returned. Must be
     *          positive.
     * @return the next pseudorandom, uniformly distributed {@code int} value
     *         between {@code 0} (inclusive) and {@code n} (exclusive) from
     *         this random number generator's sequence
     */
    @Override
    public final int nextInt(final int n) {
        int bits, val;

        if ((n & (-n)) == n) {// i.e., n is a power of 2
            return (int) ((n * (long) next(31)) >> 31);
        }

        do {
            bits = next(31);
            val = bits % n;
        } while (bits - val + (n - 1) < 0);

        return val;
    }

    /**
     * Returns the next pseudorandom, uniformly distributed {@code double}
     * value between {@code 0.0} and {@code 1.0} from this random number
     * generator's sequence.
     * 
     * @return the next pseudorandom, uniformly distributed {@code double}
     *         value between {@code 0.0} and {@code 1.0} from this random
     *         number generator's sequence
     * @see Math#random
     */
    @Override
    public final double nextDouble() {
        return ((((long) (next(26)) << 27) + next(27)) / (double) (1L << 53));
    }

    /**
     * Returns the next pseudorandom, Gaussian ("normally") distributed
     * {@code double} value with mean {@code 0.0} and standard deviation
     * {@code 1.0} from this random number generator's sequence.
     * 
     * @return the next pseudorandom, Gaussian ("normally") distributed
     *         {@code double} value with mean {@code 0.0} and standard
     *         deviation {@code 1.0} from this random number generator's
     *         sequence
     */
    // @Override
    @Override
    public final double nextGaussian() {
        double multiplier, v1, v2, s;

        if (this.m_haveNextNextGaussian) {
            this.m_haveNextNextGaussian = false;
            return this.m_nextNextGaussian;
        }

        do {
            v1 = ((2d * this.nextDouble()) - 1d);
            v2 = ((2d * this.nextDouble()) - 1d);
            s = ((v1 * v1) + (v2 * v2));
        } while ((s >= 1d) || (s == 0d));
        multiplier = StrictMath.sqrt(-2.d * StrictMath.log(s) / s);

        this.m_nextNextGaussian = (v2 * multiplier);
        this.m_haveNextNextGaussian = true;

        return (v1 * multiplier);
    }

    /**
     * The test function used to check whether the routines here have been
     * implemented correctly.
     * 
     * @param params
     *          the parameters
     */
    public static void main(final String[] params) {
        // double[][] m;
        double[] o;
        int[] p;
        int /* i, */ j;
        final int D = 10;
        final CecRandom r;
        final int seed = 1;

        r = new CecRandom();
        r.setSeed(seed);

        r.setSeed(1);
        o = CecMath.ShiftVector(D, -100d, 100d, r);
        for (j = 0; j < D; j++) {
            if (j > 0) {
                System.out.print(' ');
            }
            System.out.print(o[j]);
        }

        System.out.println();
        System.out.println();
        System.out.println();

        r.setSeed(2);
        o = CecMath.ShiftVector(D, -100d, 100d, r);
        for (j = 0; j < D; j++) {
            if (j > 0) {
                System.out.print(' ');
            }
            System.out.print(o[j]);
        }

        System.out.println();
        System.out.println();
        System.out.println();

        r.setSeed(1);
        p = CecMath.PermutationVector(D, r);

        for (j = 0; j < D; j++) {
            if (j > 0) {
                System.out.print(' ');
            }
            System.out.print(p[j] + 1);
        }

        System.out.println();
        System.out.println();
        System.out.println();

        r.setSeed(2);
        p = CecMath.PermutationVector(D, r);
        for (j = 0; j < D; j++) {
            if (j > 0) {
                System.out.print(' ');
            }
            System.out.print(p[j] + 1);
        }
        //
        // System.out.println();
        // System.out.println();
        //
        // m = r.createRotMatrix(D);
        // for (i = 0; i < D; i++) {
        // for (j = 0; j < D; j++) {
        // if (j > 0) {
        // System.out.print(' ');
        // }
        // System.out.print(m[i][j]);
        // }
        // System.out.println();
        // }

    }
}
