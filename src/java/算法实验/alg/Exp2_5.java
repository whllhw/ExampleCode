package 算法实验.alg;

import java.io.File;
import java.util.Scanner;

public class Exp2_5 {
    /**
     * 时间复杂度O(n^2)
     *
     * @param a 字符串
     * @param b 字符串
     * @return 最小的编辑距离
     */
    public static int editDistance(char[] a, char[] b) {
        // E[i,j]表示a从0到i，b从0到j中的最小编辑距离
        // 递推公式：E[i,j] = min( E[i-1,j] + 1 ,E[i][j-1] + 1, E[i-1][j-1] + diff)
        // i-1 加上一个字符
        // j-1 加上一个字符
        // 前面i-1 和 j-1替换一个字符
        // 初始化：
        // E[i,o] = i
        // E[0,i] = i
        int m = a.length;
        int n = b.length;
        int[][] E = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            E[i][0] = i;
        }
        for (int i = 1; i <= n; i++) {
            E[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                E[i][j] = Math.min(
                        E[i - 1][j] + 1,
                        E[i][j - 1] + 1
                );
                int diff = a[i - 1] == b[j - 1] ? 0 : 1;
                E[i][j] = Math.min(E[i][j],
                        E[i - 1][j - 1] + diff
                );
            }
        }
        return E[m][n];
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("/home/jax/IdeaProjects/code/src/main/java/alg/alg/Exp2_5.txt"));
        System.out.println(editDistance(
                scanner.next().toCharArray(),
                scanner.next().toCharArray()));

    }
}
