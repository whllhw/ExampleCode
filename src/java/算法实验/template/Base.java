package 算法实验.template;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

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

    public static int[][] readCost(Scanner sc, int size) {
        int[][] cost = new int[size][size];
        int t;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((t = sc.nextInt()) == -1) {
                    cost[i][j] = Integer.MAX_VALUE;
                } else {
                    cost[i][j] = t;
                }
            }
        }
        return cost;
    }

    /**
     * 读入边生成权重矩阵，无节点0。
     *
     * @param sc
     * @param size     节点个数
     * @param sizeEdge 边个数
     * @param directed 是否为有向图
     * @return 权重矩阵
     */
    public static int[][] readEdge(Scanner sc, int size, int sizeEdge, boolean directed) {
        int[][] cost = new int[size + 1][size + 1];
        for (int i = 0; i < sizeEdge; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            cost[a][b] = c;
            if (!directed)
                cost[b][a] = c;
        }
        return cost;
    }
}
