package br.ufrj.ic.strassen;

import br.ufrj.ic.matrix.Matrix;
import br.ufrj.ic.matrix.MatrixImpl;
import br.ufrj.ic.utils.Utils;

import java.util.ArrayList;
import java.util.concurrent.*;

public class ConcurrentStrassenImpl extends AbstractStrassen {
    protected ConcurrentStrassenImpl() {}

    private static final int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static final ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    public static Matrix execute(Matrix A, Matrix B) {
        int originalSize = A.getRowCount();
        int size = Utils.nextPowerOfTwo(originalSize);

        Matrix paddedA = MatrixImpl.padMatrix(A, size);
        Matrix paddedB = MatrixImpl.padMatrix(B, size);

        Matrix result;
        try {
            result = strassen(paddedA, paddedB);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return MatrixImpl.unpadMatrix(result, originalSize, originalSize);
    }

    public static Matrix strassen(Matrix A, Matrix B) throws InterruptedException, ExecutionException {
        int size = A.getRowCount();

        if (size == BASE_CASE) return A.multiply(B);

        int newSize = size / 2;

        Matrix[] subMatricesA = new Matrix[4];
        Matrix[] subMatricesB = new Matrix[4];

        subMatricesA[0] = A.getSubMatrix(0, 0, newSize); // A11
        subMatricesA[1] = A.getSubMatrix(0, newSize, newSize); // A12
        subMatricesA[2] = A.getSubMatrix(newSize, 0, newSize); // A21
        subMatricesA[3] = A.getSubMatrix(newSize, newSize, newSize); // A22

        subMatricesB[0] = B.getSubMatrix(0, 0, newSize); // B11
        subMatricesB[1] = B.getSubMatrix(0, newSize, newSize); // B12
        subMatricesB[2] = B.getSubMatrix(newSize, 0, newSize); // B21
        subMatricesB[3] = B.getSubMatrix(newSize, newSize, newSize); // B22

        Future<Matrix>[] partialComputedSubMatricesResult = computeSubMatricesProducts(subMatricesA, subMatricesB);

        Matrix C = new MatrixImpl();

        Matrix[] subMatricesResult = combinePartialSubMatricesResult(partialComputedSubMatricesResult);

        return mergeResults(C, subMatricesResult, size, newSize);
    }

    private static Matrix mergeResults(Matrix C, Matrix[] subMatricesResult, int size, int newSize) {
        for (int i = 0; i < size; i++) {
            C.addRow(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                C.addElement(i, 0);
            }
        }

        C.setSubMatrix(0, 0, subMatricesResult[0]);
        C.setSubMatrix(0, newSize, subMatricesResult[1]);
        C.setSubMatrix(newSize, 0, subMatricesResult[2]);
        C.setSubMatrix(newSize, newSize, subMatricesResult[3]);

        return C;
    }

    private static Matrix[] combinePartialSubMatricesResult(Future<Matrix>[] partialComputedSubMatricesResult) throws InterruptedException, ExecutionException {
        Matrix[] result = new Matrix[4];

        result[0] = MatrixImpl.add(
                MatrixImpl.subtract(
                        MatrixImpl.add(partialComputedSubMatricesResult[0].get(), partialComputedSubMatricesResult[3].get()),
                        partialComputedSubMatricesResult[4].get()),
                partialComputedSubMatricesResult[6].get()
        );

        result[1] = MatrixImpl.add(partialComputedSubMatricesResult[2].get(), partialComputedSubMatricesResult[4].get());
        result[2] = MatrixImpl.add(partialComputedSubMatricesResult[1].get(), partialComputedSubMatricesResult[3].get());
        result[3] = MatrixImpl.add(
                MatrixImpl.subtract(
                        MatrixImpl.add(partialComputedSubMatricesResult[0].get(), partialComputedSubMatricesResult[2].get()),
                        partialComputedSubMatricesResult[1].get()),
                partialComputedSubMatricesResult[5].get()
        );

        return result;
    }

    private static Future<Matrix>[] computeSubMatricesProducts(Matrix[] subMatricesA, Matrix[] subMatricesB) {
        Future<Matrix>[] subMatricesResult = new Future[7];

        subMatricesResult[0] = submitStrassenTask(
                MatrixImpl.add(subMatricesA[0], subMatricesA[3]),
                MatrixImpl.add(subMatricesB[0], subMatricesB[3])
        );

        subMatricesResult[1] = submitStrassenTask(
                MatrixImpl.add(subMatricesA[2], subMatricesA[3]),
                subMatricesB[0]
        );

        subMatricesResult[2] = submitStrassenTask(
                subMatricesA[0],
                MatrixImpl.subtract(subMatricesB[1], subMatricesB[3])
        );

        subMatricesResult[3] = submitStrassenTask(
                subMatricesA[3],
                MatrixImpl.subtract(subMatricesB[2], subMatricesB[0])
        );

        subMatricesResult[4] = submitStrassenTask(
                MatrixImpl.add(subMatricesA[0], subMatricesA[1]),
                subMatricesB[3]
        );

        subMatricesResult[5] = submitStrassenTask(
                MatrixImpl.subtract(subMatricesA[2], subMatricesA[0]),
                MatrixImpl.add(subMatricesB[0], subMatricesB[1])
        );

        subMatricesResult[6] = submitStrassenTask(
                MatrixImpl.subtract(subMatricesA[1], subMatricesA[3]),
                MatrixImpl.add(subMatricesB[2], subMatricesB[3])
        );

        return subMatricesResult;
    }

    private static Future<Matrix> submitStrassenTask(Matrix A, Matrix B) {
        return executor.submit(() -> strassen(A, B));
    }
}
