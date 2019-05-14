package alg.alg;

import java.io.File;
import java.util.Scanner;

public class Exp2_8 {
    /**
     * 矩阵连续相乘，求最小的划分方法
     * 复杂度O(n^3)
     * 滑动窗口大小n
     * 滑动n
     * 窗口内寻找最值n
     *
     * @param m 矩阵大小，m0,m1 为第一个矩阵
     * @return 最少划分次数
     */
    public static int chainMatrixMult(int[] m) {
        // Ai = m0*m2
        // C[i,j] = Ai*...*Aj 消耗的乘法次数
        // 递推公式
        // C[i,j] = min(C[i,k] + C[k+1,j] + mi-1×mk×mj)
        //
        int size = m.length / 2;
        int[][] c = new int[size + 1][size + 1];
        int[][] path = new int[size + 1][size + 1];

        for (int dis = 2; dis <= size; dis++) {

            for (int i = 1; i <= size - dis + 1; i++) {

                int j = dis + i - 1;
                int min = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int temp = c[i][k] + c[k + 1][j] +
                            m[(i - 1) << 1] * m[k * 2 - 1] * m[j * 2 - 1];
                    if (temp < min) {
                        min = temp;
                        // 记录本次的划分点
                        path[i][j] = k;
                    }
                }
                c[i][j] = min;
            }
        }
        System.out.println("矩阵划分如下：");
        System.out.println(solvePath(path, 1, size));
        return c[1][size];
    }

    /**
     * 递归求解路径
     */
    public static StringBuilder solvePath(int[][] path, int left, int right) {
        StringBuilder sb = new StringBuilder();
        int mid = path[left][right];
        if (left != mid) {
            sb.append("(");
            sb.append(solvePath(path, left, mid));
            sb.append(")");
        } else {
            sb.append(left);
        }
        sb.append("×");
        if (right != mid + 1) {
            sb.append("(");
            sb.append(solvePath(path, mid + 1, right));
            sb.append(")");
        } else {
            sb.append(right);
        }
        return sb;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("/home/jax/IdeaProjects/code/src/main/java/alg/alg/Exp2_8.txt"));
        int size = 2 * scanner.nextInt();
        int[] m = new int[size];
        for (int i = 0; i < size; i++) {
            m[i] = scanner.nextInt();
        }
        System.out.println(chainMatrixMult(m));
    }

}
