package br.ufrj.ic.utils.parsers;

public class Generate {
    public static void main(String[] args) {
        if(args.length != 3) {
            System.out.println("Usage: <dimension> <input_filename> <threads>");
            System.exit(1);
        }

        Integer dimension = Integer.parseInt(args[0]);
        String inputFileName = args[1];
        Integer threads = Integer.parseInt(args[2]);

        InputParser inputParser = new InputParser(dimension, inputFileName, threads);
        inputParser.generateInput();
    }
}
