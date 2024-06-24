package br.ufrj.ic.strassen;

import br.ufrj.ic.matrix.Matrix;
import br.ufrj.ic.matrix.MatrixImpl;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class StrassenTaskAsync extends RecursiveTask<Matrix> {
    private static final int THRESHOLD = 128;
    private final Matrix A;
    private final Matrix B;

    StrassenTaskAsync(Matrix A, Matrix B) {
        this.A = A;
        this.B = B;
    }


    @Override
    protected Matrix compute() {
        int size = A.getRowCount();

        if (size <= THRESHOLD) return A.multiply(B);

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

        StrassenTaskAsync[] tasks = new StrassenTaskAsync[7];
        tasks[0] = new StrassenTaskAsync(MatrixImpl.add(subMatricesA[0], subMatricesA[3]), MatrixImpl.add(subMatricesB[0], subMatricesB[3]));
        tasks[1] = new StrassenTaskAsync(MatrixImpl.add(subMatricesA[2], subMatricesA[3]), subMatricesB[0]);
        tasks[2] = new StrassenTaskAsync(subMatricesA[0], MatrixImpl.subtract(subMatricesB[1], subMatricesB[3]));
        tasks[3] = new StrassenTaskAsync(subMatricesA[3], MatrixImpl.subtract(subMatricesB[2], subMatricesB[0]));
        tasks[4] = new StrassenTaskAsync(MatrixImpl.add(subMatricesA[0], subMatricesA[1]), subMatricesB[3]);
        tasks[5] = new StrassenTaskAsync(MatrixImpl.subtract(subMatricesA[2], subMatricesA[0]), MatrixImpl.add(subMatricesB[0], subMatricesB[1]));
        tasks[6] = new StrassenTaskAsync(MatrixImpl.subtract(subMatricesA[1], subMatricesA[3]), MatrixImpl.add(subMatricesB[2], subMatricesB[3]));

        invokeAll(tasks);

        try {
            Matrix[] results = new Matrix[7];
            for (int i = 0; i < 7; i++) {
                results[i] = tasks[i].get();
            }

            Matrix C11 = MatrixImpl.add(MatrixImpl.subtract(MatrixImpl.add(results[0], results[3]), results[4]), results[6]);
            Matrix C12 = MatrixImpl.add(results[2], results[4]);
            Matrix C21 = MatrixImpl.add(results[1], results[3]);
            Matrix C22 = MatrixImpl.add(MatrixImpl.subtract(MatrixImpl.add(results[0], results[2]), results[1]), results[5]);

            Matrix C = new MatrixImpl();
            for (int i = 0; i < size; i++) {
                C.addRow(new ArrayList<>());
                for (int j = 0; j < size; j++) {
                    C.addElement(i, 0);
                }
            }

            C.setSubMatrix(0, 0, C11);
            C.setSubMatrix(0, newSize, C12);
            C.setSubMatrix(newSize, 0, C21);
            C.setSubMatrix(newSize, newSize, C22);

            return C;
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
