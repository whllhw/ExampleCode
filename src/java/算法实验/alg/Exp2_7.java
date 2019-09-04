package 算法实验.alg;

import java.io.File;
import java.util.Scanner;

public class Exp2_7 {
    /**
     * 最优二分检索树
     * 复杂度：
     * O(n^3)
     * 滑动窗口大小变化size
     * 取端点区间size-dis
     * 选取区间内的最值 j - i + 1
     *
     * @param p 概率向量
     * @return
     */
    public static double optimalBinarySearchTrees(double[] p) {
        // 不断地拓展二分检索树
        // c[i,j] 表示第i个节点到第j的节点的最小消耗
        // 递推关系：
        // c[i,j] = i<k<j min(c[i,k-1] + c[k+1,j]) + sum(pi+...+pj)
        // 初始化：
        // c[i,i-1] = 0
        // c[i,i] = pi 只有一个节点
        int size = p.length;
        double[][] c = new double[size + 2][size + 1]; // overflow
        int[][] table = new int[size + 1][size + 1];
        for (int i = 1; i <= size; i++) {
            c[i][i] = p[i - 1];
        }
        // 滑动窗口法
        // 按滑动窗口大小变化
        for (int dis = 1; dis <= size; dis++) {
            // 左区间值变化范围：
            // [1,size - dis + 1]
            // 右区间值变化范围：
            // j = dis + i - 1
            for (int i = 1; i <= size - dis + 1; i++) {
                int j = dis + i - 1;
                double min = Double.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    double x = c[i][k - 1] + c[k + 1][j];
                    if (min > x) {
                        min = x;
                        table[i][j] = k;
                    }
                }
                double sum = 0d;
                for (int k = i; k <= j; k++) {
                    sum += p[k - 1];
                }
                c[i][j] = min + sum;
            }
        }
        System.out.println("根节点：" + table[1][size]);
        return c[1][size];
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("/home/jax/IdeaProjects/code/src/main/java/alg/alg/Exp2_7.txt"));

        int size = sc.nextInt();
        double[] p = new double[size];
        for (int i = 0; i < size; i++) {
            p[i] = sc.nextDouble();
        }
        System.out.println(optimalBinarySearchTrees(p));

    }
}
