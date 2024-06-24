package br.ufrj.ic.utils;

import br.ufrj.ic.matrix.Matrix;
import br.ufrj.ic.matrix.MatrixImpl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    protected Utils() {}

    public static Matrix parseInput(String inputFileName) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader((new FileReader(inputFileName)))) {
            int dimension = Integer.parseInt(bufferedReader.readLine());

            List<List<Integer>> matrix = new ArrayList<>();

            for(int row = 0; row < dimension; row++) {
                String[] line = bufferedReader.readLine().trim().split("\\s+");
                List<Integer> rowList = new ArrayList<>();
                for(int column = 0; column < dimension; column++) {
                    rowList.add(Integer.parseInt(line[column]));
                }

                matrix.add(rowList);
            }

            return new MatrixImpl(matrix);
        } catch (IOException ioException) {
            throw new IOException("Error while reading input file", ioException);
        }
    }

    public static int nextPowerOfTwo(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Dimension must be positive");
        }

        if ((dimension & (1 << (Integer.SIZE - 1 - Integer.numberOfLeadingZeros(dimension)))) == dimension) {
            return dimension;
        }

        int count = 0;
        while (dimension > 0) {
            dimension >>= 1;
            count++;
        }
        return 1 << count;
    }

    public static boolean areFilesIdentical(String firstFile, String secondFile) throws IOException {
        if (Files.size(Paths.get(firstFile)) != Files.size(Paths.get(secondFile))) return false;

        try (FileInputStream firstFileInputStream = new FileInputStream(firstFile);
             FileInputStream secondFileInputStream = new FileInputStream(secondFile)) {

            int byte1 = firstFileInputStream.read();
            int byte2 = secondFileInputStream.read();

            while (byte1 != -1 || byte2 != -1) {
                if (byte1 != byte2) {
                    return false;
                }
                byte1 = firstFileInputStream.read();
                byte2 = secondFileInputStream.read();
            }
        }

        return true;
    }
}
