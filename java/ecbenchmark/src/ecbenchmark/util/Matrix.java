/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecbenchmark.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jcrada
 */
public class Matrix<T extends Number> implements Cloneable {

    private static final DecimalFormat DF = new DecimalFormat("0.000");
    public static final int DISCONNECTED = -1;
    public Object[][] matrix;

    public Matrix() {
    }

    public Matrix(int numberOfElements) {
        matrix = new Object[numberOfElements][numberOfElements];
    }

    public Matrix(int rows, int cols) {
        matrix = (T[][]) new Number[rows][cols];
    }

    public void set(int row, int col, T value) {
        matrix[row][col] = value;
    }

    public void set(int row, int col, T value, boolean symmetric) {
        matrix[row][col] = value;
        if (symmetric) {
            matrix[col][row] = value;
        }
    }

    public T get(int row, int col) {
        return (T) matrix[row][col];
    }

    public int numberOfRows() {
        return matrix.length;
    }

    public int numberOfColumns() {
        return matrix[0].length;
    }

    @Override
    public String toString() {
        String result = "";
        for (Object[] row : matrix) {
            result += "[\t";
            for (Object value : row) {
                if (value == null) {
                    result += value + "\t";
                } else {
                    result += DF.format(value) + "\t";
                }
            }
            result += "]\n";
        }
        return result;
    }

    @Override
    public Matrix<T> clone() throws CloneNotSupportedException {
        Matrix<T> result = (Matrix<T>) super.clone();
        result.matrix = this.matrix.clone();
        return result;
    }

    public static void ShortestDistances(Matrix<? extends Number> distances) {
        int n = distances.numberOfRows();
        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    distances.matrix[i][j] = Math.min(
                            distances.get(i, j).doubleValue(),
                            distances.get(i, k).doubleValue()
                            + distances.get(k, j).doubleValue());
                }
            }
        }
    }

    public static Matrix<Integer> ShortestDistancesAndPaths(Matrix<? extends Number> distances) {
        int n = distances.numberOfRows();

        Matrix<Integer> paths = new Matrix<>(n);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == j || Double.isInfinite(distances.get(i, j).doubleValue())) {
                    paths.matrix[i][j] = DISCONNECTED;
                } else {
                    paths.matrix[i][j] = j;
                }
            }
        }

        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (distances.get(i, j).doubleValue() > distances.get(i, k).doubleValue() + distances.get(k, j).doubleValue()) {
                        distances.matrix[i][j] = distances.get(i, k).doubleValue() + distances.get(k, j).doubleValue();
                        paths.matrix[i][j] = paths.matrix[i][k];
                    }
                }
            }
        }
        return paths;
    }

    public static List<Integer> ShortestPath(Integer source, Integer target, Matrix<Integer> path) {
        List<Integer> result = new ArrayList<>();
        int maxLoops = path.numberOfRows();
        Integer i = source;

        while (!i.equals(target) && maxLoops-- >= 0) {
            i = path.get(i, target);
            if (i == DISCONNECTED) {
                break;
            }
            result.add(i);
        }

        if (maxLoops < 0) {
            throw new RuntimeException("Path contains loops");
        }

        return result;
    }

    //Shortest path with recovery
    public static List<Integer> ShortestPath(Integer source, Integer target,
            Matrix<Integer> path, Matrix<Float> distances) {
        List<Integer> result = new ArrayList<>();
        int maxLoops = path.numberOfRows();
        Integer current = source;
        Integer previous = null;
        while (!current.equals(target) && maxLoops-- >= 0) {
            previous = current;
            current = path.get(current, target);
            if (current == DISCONNECTED) {
                //Find nearest node which has not been previously added to the path
                //and the one that is nearer the sink than the node itself

                float minDistanceToNode = Float.POSITIVE_INFINITY;
                float previousDistanceToSink = distances.get(previous, target);
                for (int j = 0; j < distances.numberOfColumns(); ++j) {
                    if (previous.equals(j)) {
                        continue;
                    }
                    float distanceToNode = distances.get(previous, j);
                    float distanceToSink = distances.get(j, target);


                    if (distanceToNode < minDistanceToNode
                            && distanceToSink < previousDistanceToSink
                            && !(result.contains(j) || source.equals(j))) {
                        current = j;
                        minDistanceToNode = distanceToNode;

                    }
                }
                if (current == DISCONNECTED) {//if i is still disconnected
                    throw new RuntimeException("Impossible to reach from " + source + " to " + target);
                }
            }
            result.add(current);
        }

        if (maxLoops < 0) {
            throw new RuntimeException("Path contains loops");
        }

        return result;
    }

    public static void main(String[] args) {
        Matrix eg = new Matrix<>(5);
        for (int i = 0; i < eg.numberOfRows(); ++i) {
            for (int j = 0; j < eg.numberOfColumns(); ++j) {
                eg.matrix[i][j] = new Double(Double.POSITIVE_INFINITY);
            }
        }
        eg.set(0, 1, 1.0, true);
        eg.set(0, 2, 3.0, true);

        eg.set(1, 2, 4.0, true);
        eg.set(1, 3, 2.0, true);

        eg.set(2, 3, 3.0, true);
//        eg.set(2, 4, 2.0, true);

//        eg.set(3, 4, 5.0, true);

        System.out.println(eg.toString());
        Matrix<Integer> paths = Matrix.ShortestDistancesAndPaths(eg);
        System.out.println(eg.toString());

        for (int i = 0; i < eg.numberOfRows(); ++i) {
            for (int j = 0; j < eg.numberOfColumns(); ++j) {
                List<Integer> path = Matrix.ShortestPath(i, j, paths);
                String out = "[" + (i + 1) + " -> " + (j + 1) + "] = ";
                for (Integer x : path) {
                    out += (x + 1) + " ";
                }
                System.out.println(out);
            }
        }

    }
}
