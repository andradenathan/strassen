package br.ufrj.ic;

import br.ufrj.ic.matrix.Matrix;
import br.ufrj.ic.strassen.ConcurrentStrassenImpl;
import br.ufrj.ic.strassen.SequentialStrassenImpl;
import br.ufrj.ic.utils.Metrics;
import br.ufrj.ic.utils.Utils;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length != 3) {
            System.out.println("Usage: <matrix1_input_filename> <matrix2_input_filename> <threads>");
            System.exit(1);
        }

        String firstMatrixInputFileName = args[0];
        String secondMatrixInputFileName = args[1];
        Integer threads = Integer.parseInt(args[2]);

        Matrix A = Utils.parseInput(firstMatrixInputFileName);
        Matrix B = Utils.parseInput(secondMatrixInputFileName);

        // Executar o algoritmo sequencial
        System.out.println("\nResultado do algoritmo sequencial:");
        long start = System.nanoTime();
        Matrix Sequential = SequentialStrassenImpl.execute(A, B);
        long end = System.nanoTime();

        Sequential.print();

        Metrics metrics = new Metrics(start, end, 's');
        metrics.print();

        // Executar o algoritmo concorrente
        System.out.println("\nResultado do algoritmo concorrente:");
        long startConcurrent = System.nanoTime();
        Matrix Concurrent = ConcurrentStrassenImpl.execute(A, B);
        long endConcurrent = System.nanoTime();

        Concurrent.print();

        Metrics concurrentMetrics = new Metrics(startConcurrent, endConcurrent, 's');
        concurrentMetrics.print();
    }
}