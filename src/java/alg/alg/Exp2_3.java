package alg.alg;

public class Exp2_3 {
    /**
     * 动态规划求二项式系数，时间复杂度O(n^2)
     *
     * @param n
     * @param k
     * @return C(n, k)
     */
    public static int binomial(int n, int k) {
        // 初始化：（两头是1）
        // dp[i,0] = 1
        // dp[i,i] = 1
        // 递推公式：
        // dp[i,j] = dp[i-1,j] + dp[i - 1,j-1]
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0)
                    dp[i][j] = 1;
                else
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
            }
        }
        return dp[n][k];
    }

    public static void main(String[] args) {
        System.out.println(binomial(5, 3));
    }
}
