package alg.template;

import java.io.PrintStream;
import java.util.Arrays;

public class Base {
    protected static PrintStream out = System.out;

    protected static void println(int[] t) {
        out.println(Arrays.toString(t));
    }

    protected static void println(int[][] t) {
        if (t == null) {
            out.println("null");
            return;
        }
        out.println("[");
        for (int i = 0; i < t.length; i++) {
            println(t[i]);
        }
        out.println("]");
    }
}
