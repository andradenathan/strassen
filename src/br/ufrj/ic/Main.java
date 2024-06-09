package br.ufrj.ic;

import br.ufrj.ic.matrix.Matrix;
import br.ufrj.ic.matrix.MatrixImpl;
import br.ufrj.ic.strassen.AbstractStrassen;
import br.ufrj.ic.strassen.SequentialStrassenImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> matrixDataA = new ArrayList<>();

        matrixDataA.add(Arrays.asList(1, 2));
        matrixDataA.add(Arrays.asList(3, 4));

        Matrix A = new MatrixImpl(matrixDataA);

        List<List<Integer>> matrixDataB = new ArrayList<>();

        matrixDataB.add(Arrays.asList(1, 2));
        matrixDataB.add(Arrays.asList(3, 4));

        Matrix B = new MatrixImpl(matrixDataB);

        Matrix C = SequentialStrassenImpl.execute(A, B);

        System.out.println("Resultado da multiplicação:");
        for (int i = 0; i < C.getRowCount(); i++) {
            for (int j = 0; j < C.getColumnCount(i); j++) {
                System.out.print(C.get(i, j) + " ");
            }
            System.out.println();
        }
    }
}