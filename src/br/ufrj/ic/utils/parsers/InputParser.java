package br.ufrj.ic.utils.parsers;

public class InputParser {
    private final Integer dimension;
    private final String inputFileName;

    public InputParser(Integer dimension, String inputFileName) {
        this.dimension = dimension;
        this.inputFileName = inputFileName;
    }

    public void generateInput() {
        MatrixGenerator matrixGenerator = new MatrixGenerator();
        matrixGenerator.generateMatrix(dimension, inputFileName);
    }
}
