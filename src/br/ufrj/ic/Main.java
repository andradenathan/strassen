package br.ufrj.ic;

import br.ufrj.ic.matrix.Matrix;
import br.ufrj.ic.matrix.MatrixImpl;
import br.ufrj.ic.strassen.SequentialStrassenImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> matrixDataA = new ArrayList<>();

        matrixDataA.add(Arrays.asList(1, 2, 4, 2));
        matrixDataA.add(Arrays.asList(2, 4, 9, 9));
        matrixDataA.add(Arrays.asList(5, 3, 1, 5));
        matrixDataA.add(Arrays.asList(6, 1, 5, 2));

        Matrix A = new MatrixImpl(matrixDataA);

        List<List<Integer>> matrixDataB = new ArrayList<>();

        matrixDataB.add(Arrays.asList(1, 2, 6, 8));
        matrixDataB.add(Arrays.asList(3, 4, 1, 1));
        matrixDataB.add(Arrays.asList(1, 9, 4, 6));
        matrixDataB.add(Arrays.asList(3, 4, 1, 3));

        Matrix B = new MatrixImpl(matrixDataB);

        Matrix C = SequentialStrassenImpl.execute(A, B);

        C.print();
    }
}