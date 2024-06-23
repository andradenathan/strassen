package br.ufrj.ic.utils.parsers;

public class InputParser {
    private Integer dimension;
    private Integer threads;
    private String inputFileName;

    public InputParser(Integer dimension, String inputFileName, Integer threads) {
        this.dimension = dimension;
        this.inputFileName = inputFileName;
        this.threads = threads;
    }

    public void generateInput() {
        MatrixGenerator matrixGenerator = new MatrixGenerator();
        matrixGenerator.generateMatrix(dimension, threads, inputFileName);
    }
}
