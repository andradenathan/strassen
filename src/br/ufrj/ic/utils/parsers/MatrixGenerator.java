package br.ufrj.ic.utils.parsers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MatrixGenerator {
    public void generateMatrix(Integer dimension, String inputFileName) {
        try {
            FileWriter fileWriter = new FileWriter(inputFileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println(dimension);

            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    printWriter.print((int) (Math.random() * 100) + " ");
                }
                printWriter.println();
            }

            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
