package br.ufrj.ic.strassen;

import br.ufrj.ic.matrix.Matrix;

public class ConcurrentStrassenImpl {
    protected ConcurrentStrassenImpl() {}

    public static Matrix execute(Matrix A, Matrix B) {
        int N = A.getRowCount();
        return A;
    }
}
