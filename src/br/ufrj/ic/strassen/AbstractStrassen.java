package br.ufrj.ic.strassen;

import br.ufrj.ic.matrix.Matrix;

import java.util.concurrent.ExecutionException;

public abstract class AbstractStrassen {
    protected static int BASE_CASE = 1;
    protected AbstractStrassen() {}

    public static Matrix execute(Matrix A, Matrix B) {
        return null;
    }

    public static Matrix execute(Matrix A, Matrix B, Integer numThreads) throws ExecutionException, InterruptedException {
        return null;
    }
}
