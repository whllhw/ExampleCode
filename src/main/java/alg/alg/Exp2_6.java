package alg.alg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exp2_6 {
    /**
     * 01背包求解
     * 时间复杂度O(nW)
     *
     * @param totalWeight 背包重量
     * @param weight      物品重量
     * @param values      物品价值
     * @return 最大价值
     */
    public static int knapsack(int totalWeight, int[] weight, int[] values) {
        // dp[i,W] = max(dp[i-1,W-wi]+vi,dp[i-1,w])
        // 考虑第i件：装入物品 或者 不装入
        // 初始化条件：
        // dp[i][0] = 0
        // dp[0][i] = 0
        int size = values.length;
        int[][] dp = new int[size + 1][totalWeight + 1];

        for (int j = 1; j <= size; j++) {
            for (int w = 1; w <= totalWeight; w++) {
                if (w >= weight[j - 1]) {
                    dp[j][w] = Math.max(
                            dp[j - 1][w - weight[j - 1]] + values[j - 1],
                            dp[j - 1][w]
                    );
                } else {
                    dp[j][w] = dp[j - 1][w];
                }
            }
        }


        return dp[size][totalWeight];
    }

    public static void solvePath(int[] weight, int[] values, int[][] dp) {

    }

    /**
     * 状态化搜索
     *
     * @param i
     * @param w
     * @param weight
     * @param values
     * @param k
     * @return
     */
    public static int MFK(int i, int w, int[] weight, int[] values, int[][] k) {
        if (k[i][w] < 0) {
            int value;
            if (w < weight[i]) {
                value = MFK(i - 1, w, weight, values, k);
            } else {
                value = Math.max(MFK(i - 1, w, weight, values, k),
                        MFK(i - 1, w - weight[i], weight, values, k) + values[i]
                );
            }
            k[i][w] = value;
        }
        return k[i][w];
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("/home/jax/IdeaProjects/code/src/main/java/alg/alg/Exp2_6.txt"));
        int size = sc.nextInt();
        int totalWeight = sc.nextInt();
        int[] values = new int[size];
        int[] weight = new int[size];
        for (int i = 0; i < size; i++) {
            weight[i] = sc.nextInt();
        }
        for (int i = 0; i < size; i++) {
            values[i] = sc.nextInt();
        }

        System.out.println(knapsack(totalWeight, weight, values));


    }
}
