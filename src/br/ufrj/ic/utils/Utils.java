package br.ufrj.ic.utils;

public class Utils {
    protected Utils() {}

    public static int nextPowerOfTwo(int dimension) {
        int count = 0;
        while (dimension > 0) {
            dimension >>= 1;
            count++;
        }
        return 1 << count;
    }
}
