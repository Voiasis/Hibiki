package net.vezio.tools.math;

public class Algebra {

    private Algebra() {
    }

    public static Rational[] solve(int unknowns, char[] unknownc, String[] lines) {
        Rational[][] matrix = new Rational[unknowns][unknowns + 1];
        for (int i = 0; i < unknowns; i++) {
            try {
                String[] equations = lines[i].split("=");
                Evaluate e = new Evaluate(equations[0], unknownc);
                Coefficient coefficient1 = e.evaluateExpression();
                e = new Evaluate(equations[1], unknownc);
                Coefficient coefficient2 = e.evaluateExpression();
                for (int j = 0; j < unknowns; j++) {
                    matrix[i][j] = coefficient1.unknown[j].subtract(coefficient2.unknown[j]);
                }
                matrix[i][unknowns] = coefficient2.number.subtract(coefficient1.number);
            } catch (RuntimeException e) {
                throw new ParserException(e, i);
            }
        }
        Matrix m = new Matrix(matrix, unknowns);
        return m.solve();
    }

}