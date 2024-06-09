package br.ufrj.ic.matrix;

import java.util.List;

public interface Matrix {
    void addRow(List<Integer> row);
    void addElement(Integer row, Integer element);
    Integer get(Integer row, Integer col);
    Integer getRowCount();
    Integer getColumnCount(Integer row);
    Matrix multiply(Matrix B);
    Matrix getSubMatrix(int startRow, int startCol, int size);
    void setSubMatrix(int startRow, int startCol, Matrix subMatrix);
    List<List<Integer>> getData();

    static Matrix add(Matrix A, Matrix B) {
        return null;
    }

    static Matrix subtract(Matrix A, Matrix B) { return  null; }

    void print();

    static Matrix padMatrix() { return  null; }

    static Matrix unpadMatrix(Matrix matrix, int originalRows, int originalCols) { return null; }
}
