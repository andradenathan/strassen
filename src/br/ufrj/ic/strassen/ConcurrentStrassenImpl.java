package br.ufrj.ic.strassen;

import br.ufrj.ic.matrix.Matrix;
import br.ufrj.ic.matrix.MatrixImpl;
import br.ufrj.ic.utils.Utils;

import java.util.concurrent.*;

public class ConcurrentStrassenImpl extends AbstractStrassen {
    protected ConcurrentStrassenImpl() {}

    public static Matrix execute(Matrix A, Matrix B, Integer numThreads) {
        int originalSize = A.getRowCount();
        int size = Utils.nextPowerOfTwo(originalSize);

        Matrix paddedA = MatrixImpl.padMatrix(A, size);
        Matrix paddedB = MatrixImpl.padMatrix(B, size);

        ForkJoinPool forkJoinPool = new ForkJoinPool(numThreads);

        Matrix result = forkJoinPool.invoke(new StrassenTaskAsync(paddedA, paddedB));

        return MatrixImpl.unpadMatrix(result, originalSize, originalSize);
    }
}
