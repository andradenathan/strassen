package br.ufrj.ic.utils.parsers;

public class Generate {
    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("Usage: <dimension> <input_filename>");
            System.exit(1);
        }

        Integer dimension = Integer.parseInt(args[0]);
        String inputFileName = args[1];

        InputParser inputParser = new InputParser(dimension, inputFileName);
        inputParser.generateInput();
    }
}
