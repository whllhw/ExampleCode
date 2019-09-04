package 算法实验;

import 算法实验.template.Base;

public class T8_1 extends Base {
    static int[][] F = new int[100][100];

    static {
        for (int i = 0; i < F.length; i++) {
            for (int j = 0; j < F[0].length; j++) {
                F[i][j] = i == 0 ? 0 : -1;
            }
            F[i][0] = 0;
        }
    }

    /**
     * 求不相邻硬币最大和问题，（自底而上）
     * 时间复杂度O(n) 空间O(n)
     */
    // 思路：dp[i]表示i子数组中数值最大
    // 递推公式：
    // dp[i] = max(dp[i-1],dp[i-2]+nums[i])
    // dp[0] = 0
    // dp[1] = nums[0]
    static void coinRow(int[] nums) {
        int[] res = new int[nums.length + 1];
        boolean[] callback = new boolean[nums.length];
        res[0] = 0;
        res[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // res[2] = max(res[1],res[0]+nums[1])
            res[i + 1] = Math.max(res[i], res[i - 1] + nums[i]);
            if (res[i + 1] != res[i]) {
                callback[i] = true;
            } else if (i == 1) {
                callback[0] = true;
            }
        }
        out.println(res[nums.length]);
        for (int i = 0; i < callback.length; i++) {
            if (callback[i]) {
                out.printf(" %d ->", nums[i]);
            }
        }
    }

    /**
     * 递归解决不相邻问题，（自顶而下）
     * 时间复杂度O(2^n)
     * 递归树
     */
//                   size            1
//                  /    \
//              size-2   size-1      2
//              /  \     / \
//           size-4 size-5...        4
    static int maxCoinRow(int[] nums, int size, int ans) {
        if (size <= 0) {
            return ans;
        }
        int a = maxCoinRow(nums, size - 2, ans) + nums[size - 1];
        int b = maxCoinRow(nums, size - 1, ans);
        return Math.max(a, b);
    }

    /**
     * dp 解决找零问题（自底而上）
     * 复杂度O(nm) 空间O(n)
     * dp[i] = min(dp[i-di])+1
     * min(dp[i-di]): 取所有i-di中的最小值
     * dp[i]表示组合成i所需的硬币数
     */

    static void minCoin(int[] nums, int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int temp = Integer.MAX_VALUE;
            int j = 0;
            while (j < nums.length && i - nums[j] >= 0) {
                if (temp > dp[i - nums[j]]) {
                    temp = dp[i - nums[j]];
                }
                j++;
            }
            dp[i] = temp + 1;
        }
        out.println(dp[n]);
        int i = n;
        while (i != 0) {
            int j = 0;
            int temp = Integer.MAX_VALUE;
            int pos = 0;
            while (j < nums.length && i - nums[j] >= 0) {
                if (temp > dp[i - nums[j]]) {
                    temp = dp[i - nums[j]];
                    pos = j;
                }
                j++;
            }
            out.printf("%d ", nums[pos]);
            i -= nums[pos];
        }
    }

    /**
     * 寻找硬币最多的一个路径（自底而上）
     * <p>
     * dp[i][j]表示到达i,j获取的硬币
     * dp[i][j] = max(d[i-1][j],d[i][j-1])+c[i-1][j-]
     */
    static void robotCoinCollection(int[][] c) {
        int[][] dp = new int[c.length + 1][c[0].length + 1];
        for (int i = 1; i <= c.length; i++) {
            for (int j = 1; j <= c[0].length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + c[i - 1][j - 1];
            }
        }
        out.println(dp[c.length][c[0].length]);
    }

    static int dfsRobotCoinCollection(int[][] c, int x, int y, int ans) {
        if (x >= c.length || y >= c[0].length) {
            return ans;
        }
        int a = dfsRobotCoinCollection(c, x + 1, y, ans + c[x][y]);
        int b = dfsRobotCoinCollection(c, x, y + 1, ans + c[x][y]);
        return Math.max(a, b);
    }

    /**
     * 01背包
     * 状态方程：
     * dp[i][j] = max(dp[i-1][j],dp[i-1][j-weight[i]] + value[i]) 当j >= weight[i]时
     * 否则 dp[i][j] = dp[i-1][j]
     * dp[i][j]表示给定i个物品，放进最大重量j的背包的最大价值
     * dp[i-1][j-weight[i]] + value[i]
     * 表示前i-1件物品能放入j-weight[i]的背包中，加上value[i]即是最优解
     * 复杂度O(nw) 空间O(nw)
     * 回溯O(n)
     */
    //      0   j-wi         j     W
    // 0    0    0           0     0
    // i-1  dp[j-1,j-wi]  dp[j-1,j]
    // i                  dp[i,j]
    // n                          target
    static int pack01(int[] weight, int[] values, int maxW) {
        int[][] dp = new int[values.length + 1][maxW + 1];
        // dp[0][i] = 0 i from 0 to maxW
        // dp[i][0] = 0 i from 0 to value.length
        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= maxW; j++) {
                if (j >= weight[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j],
                            dp[i - 1][j - weight[i - 1]] + values[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        int i = values.length;
        int j = maxW;
        while (i > 0) {
            if (dp[i][j] != dp[i - 1][j]) {
                System.out.printf("%d ->", values[i - 1]);
                j -= weight[i - 1];
            }
            i--;
        }
        return dp[values.length][maxW];
    }

    /**
     * 记忆化搜索版本的01背包，未计算设置为-1
     * 递归实现，自顶而下，不会重复计算值（空间利用效率低）
     */
    static int MFKnapscak(int[] weight, int[] value, int i, int maxW) {
        if (F[i][maxW] < 0) {
            int temp = 0;
            if (maxW < weight[i - 1]) {
                temp = MFKnapscak(weight, value, i - 1, maxW);
            } else {
                temp = Math.max(MFKnapscak(weight, value, i - 1, maxW),
                        MFKnapscak(weight, value, i - 1, maxW - weight[i - 1]) + value[i - 1]);
            }
            F[i][maxW] = temp;
        }
        return F[i][maxW];
    }

    /**
     * WarShall算法:
     * 动态规划求解有向图的传递闭包:（自底而上）
     * R(k)[i,j]定义存在一条路径，i到j，且i到j中的路径上节点都不大于k
     * R(0) = 图的邻接矩阵
     * 于是有递推公式
     * R(i)[i,j] = R(i-1)[i,j] || R(i-1)[i,k] and R(i-1)[k,j]
     * R(n)为所求的传递闭包
     * 复杂度O(n^3) 空间O(n^2)
     */
    static int[][] warShall(int[][] RX) {
        int[][] R = new int[RX.length][RX.length];
        for (int i = 0; i < RX.length; i++) {
            System.arraycopy(RX[i], 0, R[i], 0, RX.length);
        }
        for (int k = 1; k <= R.length; k++) {
            for (int i = 0; i < R.length; i++) {
                for (int j = 0; j < R.length; j++) {
                    if (R[i][k - 1] > 0 && R[k - 1][j] > 0) {
                        R[i][j] = 1;
                    } else if (R[i][j] == 1) {
                        R[i][j] = 1;
                    }
                }
            }
        }
        return R;
    }

    /**
     * Floyd算法
     * 动态规划计算完全最短路径
     * 思路同WarShall，矩阵为权重矩阵。
     * R(k+1)[i][j] = min(R(k)[i][k]+R(k)[k][j],R(k)[i][j])
     * Integer.MAX_VALUE表示不连通，同时注意溢出判断
     * 复杂度O(n^3) 空间O(n^2)
     */
    static int[][] floyd(int[][] RX) {
        int[][] R = new int[RX.length][RX.length];
        for (int i = 0; i < R.length; i++) {
            System.arraycopy(RX[i], 0, R[i], 0, R.length);
        }
        for (int k = 1; k <= R.length; k++) {
            for (int i = 0; i < R.length; i++) {
                for (int j = 0; j < R.length; j++) {
                    int sum = R[i][k - 1] + R[k - 1][j];
                    if (sum < 0) {
                        sum = Integer.MAX_VALUE;
                    }
                    R[i][j] = Math.min(R[i][j], sum);
                }
            }
        }
        return R;
    }

    /**
     * 《算法分析与设计基础》
     * 动态规划题目
     * 小结：
     * 硬币找零问题、背包问题都可以使用动态规划求解。
     * 求出递推关系式，最优问题的解是子问题的最优解组成的
     * 记忆化搜索则是结合了自顶而下，自底而上的优势，自顶而下只求解子问题一次，记录在表中
     */
    public static void main(String[] args) {
//        coinRow(new int[]{5, 1, 2, 10, 6, 2});
//        int[] nums = new int[]{5, 1, 2, 10, 6, 2};
//        System.out.println(maxCoinRow(nums, nums.length, 0));
//        minCoin(new int[]{1, 3, 4}, 6);
//        int[][] map = new int[][]{
//                {0, 0, 0, 0, 1, 0},
//                {0, 1, 0, 1, 0, 0},
//                {0, 0, 0, 1, 0, 1},
//                {0, 0, 1, 0, 0, 1},
//                {1, 0, 0, 0, 1, 0}
//        };
//        robotCoinCollection(map);
//        out.println(dfsRobotCoinCollection(map, 0, 0, 0));
//        int[] value = new int[]{12, 10, 20, 15};
//        int[] weight = new int[]{2, 1, 3, 2};
//        out.println(pack01(weight, value, 5));
//        out.println(MFKnapscak(weight, value, value.length, 5));
//        int[][] R = new int[][]{
//                {0, 1, 0, 0},
//                {0, 0, 0, 1},
//                {0, 0, 0, 0},
//                {1, 0, 1, 0}
//        };
//        println(warShall(R));
//        println(R);
        int none = Integer.MAX_VALUE;
        int[][] R = new int[][]{
                {0, none, 3, none},
                {2, 0, none, none},
                {none, 7, 0, 1},
                {6, none, none, 0}
        };
        println(floyd(R));
    }
}
