package br.ufrj.ic;

import br.ufrj.ic.matrix.Matrix;
import br.ufrj.ic.matrix.MatrixImpl;
import br.ufrj.ic.strassen.SequentialStrassenImpl;
import br.ufrj.ic.utils.Metrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // TODO: receber os argumentos do programa.

        if(args.length != 3) {
            System.out.println("Usage: <input_filename> <output_filename> <threads>");
            System.exit(1);
        }

        String inputFileName = args[0];
        String outputFileName = args[1];
        Integer threads = Integer.parseInt(args[2]);


        long start = System.nanoTime();
        List<List<Integer>> matrixDataA = new ArrayList<>();

        matrixDataA.add(Arrays.asList(1, 2, 8));
        matrixDataA.add(Arrays.asList(2, 4, 5));
        matrixDataA.add(Arrays.asList(5, 3, 1));

        Matrix A = new MatrixImpl(matrixDataA);

        List<List<Integer>> matrixDataB = new ArrayList<>();

        matrixDataB.add(Arrays.asList(1, 2, 3));
        matrixDataB.add(Arrays.asList(3, 4, 7));
        matrixDataB.add(Arrays.asList(1, 9, 4));

        Matrix B = new MatrixImpl(matrixDataB);

        Matrix C = SequentialStrassenImpl.execute(A, B);
        long end = System.nanoTime();

        C.print();

        Metrics metrics = new Metrics(start, end, 's');
        metrics.print();
    }
}