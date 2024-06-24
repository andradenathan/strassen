package br.ufrj.ic;

import br.ufrj.ic.matrix.Matrix;
import br.ufrj.ic.strassen.ConcurrentStrassenImpl;
import br.ufrj.ic.strassen.SequentialStrassenImpl;
import br.ufrj.ic.utils.Metrics;
import br.ufrj.ic.utils.Utils;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length < 3 || args.length > 4) {
            System.out.println("Usage: <matrix1_input_filename> <matrix2_input_filename> <threads> --compare");
            System.exit(1);
        }

        String firstMatrixInputFileName = args[0];
        String secondMatrixInputFileName = args[1];
        Integer threads = Integer.parseInt(args[2]);

        Matrix A = Utils.parseInput(firstMatrixInputFileName);
        Matrix B = Utils.parseInput(secondMatrixInputFileName);

        calculateConcurrent(A, B, threads);

        if(args.length == 4 && args[3].equals("--compare")) {
            calculateSequential(A, B);
        }
    }

    public static void calculateConcurrent(Matrix A, Matrix B, Integer threads) {
        System.out.println("Calculating matrix multiplication using Strassen's algorithm concurrent...");
        long startConcurrent = System.nanoTime();
        Matrix concurrentStrassen = ConcurrentStrassenImpl.execute(A, B, threads);
        long endConcurrent = System.nanoTime();

        concurrentStrassen.printInFile("output_concurrent.txt");

        Metrics concurrentDuration = new Metrics(startConcurrent, endConcurrent, 's');
        concurrentDuration.print();
    }

    public static void calculateSequential(Matrix A, Matrix B) throws IOException {
        System.out.println("Calculating matrix multiplication using Strassen's algorithm sequential...");

        long startSequential = System.nanoTime();
        Matrix sequentialStrassen = SequentialStrassenImpl.execute(A, B);
        long endSequential = System.nanoTime();

        Metrics sequentialDuration = new Metrics(startSequential, endSequential, 's');
        sequentialDuration.print();

        sequentialStrassen.printInFile("output_sequential.txt");

        System.out.println("Are the outputs identical? "
                + Utils.areFilesIdentical("output_sequential.txt", "output_concurrent.txt"));
    }
}