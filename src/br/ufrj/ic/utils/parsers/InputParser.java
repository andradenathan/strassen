package br.ufrj.ic.utils.parsers;

public class InputParser {
    private final Integer dimension;
    private final Integer threads;
    private final String inputFileName;

    protected String DEFAULT_INPUT_FILE_NAME = "matrix.txt";
    protected Integer DEFAULT_THREADS_VALUE = 4;

    public InputParser() {
        this.dimension = (int) Math.pow(10, 4);
        this.inputFileName = DEFAULT_INPUT_FILE_NAME;
        this.threads = DEFAULT_THREADS_VALUE;
    }

    public InputParser(Integer dimension, String inputFileName) {
        this(dimension, inputFileName, null);
    }

    public InputParser(Integer dimension, String inputFileName, Integer threads) {
        this.dimension = dimension;
        this.inputFileName = inputFileName;
        this.threads = threads;
    }

    public void generateInput() {
        MatrixGenerator matrixGenerator = new MatrixGenerator();
        matrixGenerator.generateMatrix(dimension, threads, inputFileName);
    }

    public Integer getDimension() {
        return dimension;
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public Integer getThreads() {
        return threads;
    }
}
