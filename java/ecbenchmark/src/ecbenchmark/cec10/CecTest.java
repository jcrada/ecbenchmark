package ecbenchmark.cec10;


import ecbenchmark.Function;
import ecbenchmark.cec10.CecBenchmark;
import ecbenchmark.cec10.CecMath;
import ecbenchmark.cec10.CecRandom;
import ecbenchmark.function.Ackley;
import ecbenchmark.function.Elliptic;
import ecbenchmark.function.Rastrigin;
import ecbenchmark.function.Rosenbrock;
import ecbenchmark.function.Schwefel;
import ecbenchmark.function.Sphere;
import ecbenchmark.operator.Permuted;
import ecbenchmark.cec10.CecRotated;
import java.util.Random;
import original.cec10.Defaults;
import original.cec10.Kernel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author jcrada
 */
public class CecTest {

    public static int DIMENSIONS = 1000;
    public static int M = 50;

    public static void test() {
        int dimensions = 3;
        double[] x = new double[dimensions];
        double[] shift = new double[dimensions];
        int[] permutation = new int[dimensions];
        Random random = new Random(1l);
        for (int i = 0; i < dimensions; ++i) {
//            x[i] = random.nextDouble();
            x[i] = i + 10;
            shift[i] = 0;
            permutation[i] = i;
        }

        CecRandom r1 = new CecRandom(1l);

        //double[][] rotation = CecMath.RotationMatrix(dimensions, r1);
        //double[] rotation1d = r2.createRotMatrix1D(dimensions);

        double[][] rotation = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[] rotation1d = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        double[] lookup = original.cec10.Kernel.createPowLookup(dimensions);
        double[] z = new double[dimensions];

        double t = Kernel.shiftedPermRotElliptic(x, shift, permutation, rotation1d, 0, dimensions, z, lookup);

        Function j = new CecRotated(rotation, new Elliptic());
        double m = j.f(x);

        System.out.println("t\tm");
        System.out.println(t + "\t" + m);

    }

    public static void test(original.cec10.Function theirs, ecbenchmark.Function mine) {
        int times  = 1;
        for (int r = 0; r < times; ++r) {
            int dimensions = DIMENSIONS;
            double[] x = new double[dimensions];
            Random random = new Random();
            for (int i = 0; i < dimensions; ++i) {
                x[i] = random.nextDouble();
            }

            double theirResult, myResult;

            theirResult = theirs.f(x);
            myResult = mine.f(x);
//            System.out.println("Theirs vs. Mine");
            System.out.println(theirResult + " \t " + myResult + " \t " + (theirResult - myResult));
        }

    }

    public static void testCore() {
        int dimensions = DIMENSIONS;
        Defaults.DEFAULT_DIM = DIMENSIONS;
        Defaults.DEFAULT_M = M;
        double[] x = new double[dimensions];
        Random random = new Random();
        for (int i = 0; i < dimensions; ++i) {
            x[i] = random.nextDouble();
        }
        double[] shift = new double[dimensions];

        double theirs, mine;

        //REGULAR
        System.out.println("Theirs vs. Mine");
        System.out.print("Sphere:\t");
        theirs = Kernel.shiftedSphere(x, shift, 0, dimensions);
        mine = new Sphere().f(x);
        System.out.println(theirs + " \t " + mine);

        System.out.print("Elliptic:\t");
        double[] lookup = Kernel.createPowLookup(dimensions);
        theirs = Kernel.shiftedElliptic(x, shift, 0, dimensions, lookup);
        mine = new Elliptic().f(x);
        System.out.println(theirs + " \t " + mine);

        System.out.print("Ackley:\t");
        theirs = Kernel.shiftedAckley(x, shift, 0, dimensions);
        mine = new Ackley().f(x);
        System.out.println(theirs + " \t " + mine);

        System.out.print("Rastrigin:\t");
        theirs = Kernel.shiftedRastrigin(x, shift, 0, dimensions);
        mine = new Rastrigin().f(x);
        System.out.println(theirs + " \t " + mine);

        System.out.print("Rosenbrock:\t");
        theirs = Kernel.shiftedRosenbrock(x, shift, 0, dimensions);
        mine = new Rosenbrock().f(x);
        System.out.println(theirs + " \t " + mine);

        System.out.print("Schwefel:\t");
        theirs = Kernel.shiftedSchwefel12(x, shift, 0, dimensions);
        mine = new Schwefel().f(x);
        System.out.println(theirs + " \t " + mine);



        //PERMUTATION



        System.out.println("PERMUTATION");
        int[] permutation = new int[dimensions];
        for (int i = 0; i < dimensions; ++i) {
            permutation[i] = random.nextInt(dimensions);
        }
        System.out.println("Theirs vs. Mine");
        System.out.print("Sphere:\t");
        theirs = Kernel.shiftedPermSphere(x, shift, permutation, 0, dimensions);
        mine = new Permuted(permutation, new Sphere()).f(x);
        System.out.println(theirs + " \t " + mine);

        System.out.print("Elliptic:\t");
        lookup = Kernel.createPowLookup(dimensions);
        theirs = Kernel.shiftedPermElliptic(x, shift, permutation, 0, dimensions, lookup);
        mine = new Permuted(permutation, new Elliptic()).f(x);
        System.out.println(theirs + " \t " + mine);

        System.out.print("Ackley:\t");
        theirs = Kernel.shiftedPermAckley(x, shift, permutation, 0, dimensions);
        mine = new Permuted(permutation, new Ackley()).f(x);
        System.out.println(theirs + " \t " + mine);

        System.out.print("Rastrigin:\t");
        theirs = Kernel.shiftedPermRastrigin(x, shift, permutation, 0, dimensions);
        mine = new Permuted(permutation, new Rastrigin()).f(x);
        System.out.println(theirs + " \t " + mine);

        System.out.print("Rosenbrock:\t");
        theirs = Kernel.shiftedPermRosenbrock(x, shift, permutation, 0, dimensions);
        mine = new Permuted(permutation, new Rosenbrock()).f(x);
        System.out.println(theirs + " \t " + mine);

        //ROTATION

        System.out.println("ROTATION:");
        System.out.println("Theirs vs. Mine");

        original.cec10.Randomizer rTheir = new original.cec10.Randomizer(1l);
        Random rMine = new ecbenchmark.cec10.CecRandom(1l);

        double[][] rotation = CecMath.RotationMatrix(M, rMine);
        double[] rotation1d = rTheir.createRotMatrix1D(M);
        double[] z = new double[dimensions];

        System.out.print("Elliptic:\t");
        lookup = Kernel.createPowLookup(dimensions);
        theirs = Kernel.shiftedPermElliptic(x, shift, permutation, 0, dimensions, lookup);
        mine = new Permuted(permutation, new Elliptic()).f(x);
//        System.out.println(theirs + " \t " + mine);

        System.out.print("Ackley:\t");
        theirs = Kernel.shiftedPermRotAckley(x, shift, permutation, rotation1d, 0, dimensions, z);
        mine = new CecRotated(rotation, new Permuted(permutation, new Ackley())).f(x);
        System.out.println(theirs + " \t " + mine);

        System.out.print("Rastrigin:\t");
        theirs = Kernel.shiftedPermRotRastrigin(x, shift, permutation, rotation1d, 0, dimensions, z);
        mine = new CecRotated(rotation, new Permuted(permutation, new Rastrigin())).f(x);
        System.out.println(theirs + " \t " + mine);





    }

    public static void Compare() {
        int dimensions = DIMENSIONS;
        int m = M;
        Defaults.DEFAULT_DIM = DIMENSIONS;
        Defaults.DEFAULT_M = M;

        //TODO: F4

        original.cec10.Function[] theirs = {
            new original.cec10.F1(), //OK
            new original.cec10.F2(), //OK
            new original.cec10.F3(), //OK
            new original.cec10.F4(), //OK
            new original.cec10.F5(), //OK
            new original.cec10.F6(), //OK
            new original.cec10.F7(), //OK
            new original.cec10.F8(), //OK
            new original.cec10.F9(), //OK
            new original.cec10.F10(), //OK
            new original.cec10.F11(), //OK
            new original.cec10.F12(), //OK
            new original.cec10.F13(), //OK
            new original.cec10.F14(), //OK
            new original.cec10.F15(), //OK
            new original.cec10.F16(), //OK
            new original.cec10.F17(), //OK
            new original.cec10.F18(), //OK
            new original.cec10.F19(), //OK
            new original.cec10.F20(),};
        ecbenchmark.Function[] mine = {
            new ecbenchmark.cec10.F01(dimensions), //OK
            new ecbenchmark.cec10.F02(dimensions), //OK
            new ecbenchmark.cec10.F03(dimensions), //OK
            new ecbenchmark.cec10.F04(dimensions, m), //OK
            new ecbenchmark.cec10.F05(dimensions, m), //OK
            new ecbenchmark.cec10.F06(dimensions, m), //OK
            new ecbenchmark.cec10.F07(dimensions, m), //OK
            new ecbenchmark.cec10.F08(dimensions, m), //OK
            new ecbenchmark.cec10.F09(dimensions, m), //OK
            new ecbenchmark.cec10.F10(dimensions, m), //OK
            new ecbenchmark.cec10.F11(dimensions, m), //OK
            new ecbenchmark.cec10.F12(dimensions, m), //OK
            new ecbenchmark.cec10.F13(dimensions, m), //OK
            new ecbenchmark.cec10.F14(dimensions, m), //OK
            new ecbenchmark.cec10.F15(dimensions, m), //OK
            new ecbenchmark.cec10.F16(dimensions, m), //OK
            new ecbenchmark.cec10.F17(dimensions, m), //OK
            new ecbenchmark.cec10.F18(dimensions, m), //OK
            new ecbenchmark.cec10.F19(dimensions, m), //OK
            new ecbenchmark.cec10.F20(dimensions, m),};



        for (int i = 0; i < theirs.length; ++i) {
//            System.out.println("**************************************");
            System.out.println(theirs[i].getShortName());
            test(theirs[i], mine[i]);
//            System.out.println("**************************************");
        }
//        System.out.println((System.currentTimeMillis() - time) / 1000.0);

    }
    
    public static void main(String[] args) {
        Compare();
        System.exit(0);
        int dimensions = 1000, mValue = 50;
        CecBenchmark b = new CecBenchmark(dimensions, mValue);
        
        
        
        CecRandom random = new CecRandom(99l);
        
        double[] x = new double[dimensions];
        for (int i = 0 ; i < dimensions; ++i){
            x[i] = random.nextDouble();
        }
        
        for (int i = 0 ; i < b.numberOfFunctions(); ++i){
            System.out.println("F" + (i + 1) + "(x) = " + b.getFunction(i).f(x));
        }
                
    }
}
