package net.vezio.tools.math;

import java.io.Serializable;

public class Matrix implements Serializable {

    private Rational[][] matrix;
    private int n;

    public Matrix(Rational[][] rational, int n) {
        this.matrix = rational;
        this.n = n;
    }

    public Rational[] solve() {
        for (int k = 0; k < n; k++) {
            if (matrix[k][k].equals(0)) {
                changeRow(n, k, matrix);
            }
            for (int i = 0; i < n; i++) {
                Rational temp = matrix[i][k];
                for (int j = 0; j < n + 1; j++) {
                    if (i < k)
                        break;
                    if (temp.equals(0))
                        continue;
                    if (!temp.equals(1)) {
                        matrix[i][j] = matrix[i][j].divide(temp);
                    }
                    if (i > k)
                        matrix[i][j] = matrix[i][j].subtract(matrix[k][j]);
                }
            }
        }
        Rational[] result = new Rational[n];
        for (int i = n - 1; i >= 0; i--) {
            Rational temp = matrix[i][n];
            for (int j = n - 1; j >= 0; j--) {
                if (i < j && !matrix[i][j].equals(0)) {
                    temp = temp.subtract(result[j].multiply(matrix[i][j]));
                }
            }
            temp = temp.divide(matrix[i][i]);
            result[i] = temp;
        }
        return result;
    }

    private void changeRow(int n, int k, Rational[][] matrix) {
        Rational[] temp = new Rational[n + 1];
        for (int i = k; i < n; i++) {
            if (i + 1 == n && matrix[k][k].equals(0)) {
                if (matrix[k][n].equals(0)) {
                    throw new InfiniteSolutionException();
                } else {
                    throw new NoSolutionException();
                }
            }
            for (int j = 0; j < n + 1; j++) {
                temp[j] = matrix[k][j];
                matrix[k][j] = matrix[i + 1][j];
                matrix[i + 1][j] = temp[j];
            }
            if (!matrix[k][k].equals(0))
                return;
        }
    }

}