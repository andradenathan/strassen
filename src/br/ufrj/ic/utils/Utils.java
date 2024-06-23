package br.ufrj.ic.utils;

import br.ufrj.ic.matrix.Matrix;
import br.ufrj.ic.matrix.MatrixImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    protected Utils() {}

    public static Matrix parseInput(String inputFileName) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader((new FileReader(inputFileName)))) {
            bufferedReader.readLine();

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
        int count = 0;
        while (dimension > 0) {
            dimension >>= 1;
            count++;
        }
        return 1 << count;
    }
}
