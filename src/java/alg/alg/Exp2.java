package alg.alg;

import java.io.File;
import java.util.Scanner;

public class Exp2 {

    /**
     * 多段图最短路径，复杂度O(n^2*m)
     *
     * @param W     G[i][j] = cost 带权图，i 到 j 消耗为 cost
     * @param e     e[i] = j 表明节点i的所处的阶段为j
     * @param sizeI 阶段数量
     * @return 最短路径
     */
    public static int multistageGraph(int[][] W, int[] e, int sizeI) {
        // int[][] W 二维表，W[i][j]存放第i段中到j点的
        // 递推式：
        // W(i,p) = min( W(i-1,q) + w(q,p) ) , i > 1
        // 思路：
        // 从最后一段开始求能到该点的最小权值。直到能到源点为止。

        int[][] cost = new int[sizeI + 1][e.length];
        // 初始化汇点的cost
        for (int i = 0; i < W.length; i++) {
            // 汇点为结束点
            if (e[i] != sizeI)
                continue;
            for (int j = 0; j < W.length; j++) {
                cost[sizeI - 1][j] = W[i][j];
            }
        }
        int startPoint = -1;
        for (int i = sizeI - 2; i > 0; i--) {
            for (int j = 0; j < W.length; j++) {
                // 不是第i阶段的点跳过
                if (e[j] != i)
                    continue;
                // 逆推到第i阶段最小cost
                cost[i][j] = helper(cost[i + 1], W[j]);
                if (i == 1)
                    startPoint = j;
            }

        }
        return cost[1][startPoint];
    }

    /**
     * 对于某个节点，求解其到下一阶段的最小权重
     *
     * @param cost 与其直接相连的节点集合的cost
     * @param w    相连的权重
     * @return 权重的最小值
     */
    private static int helper(int[] cost, int[] w) {
        int min = (1 << 31) - 1;
        for (int i = 0; i < cost.length; i++) {
            if (cost[i] > 0
                    && w[i] > 0
                    && min > w[i] + cost[i]) {
                min = w[i] + cost[i];
            }
        }
        return min;
    }

    public static void main(String[] args) throws Exception {
//        String path = System.getProperty("base.dir");
        Scanner sc = new Scanner(new File("/home/jax/IdeaProjects/code/src/main/java/alg/alg/Exp2_1.txt"));
        int nodeSize = sc.nextInt();
        int sizeI = sc.nextInt();
        int[][] W = new int[nodeSize + 1][nodeSize + 1];
        int[] e = new int[nodeSize + 1];
        // 读入各个阶段
        for (int i = 0; i < sizeI; i++) {
            String[] j = sc.nextLine().split(" ");
            if (j.length == 1 && j[0].isEmpty()) {
                i--;
                continue;
            }
            for (String x : j) {
                e[Integer.valueOf(x)] = i + 1;
            }
        }
        int i, j, cost;
        while ((i = sc.nextInt()) != -1) {
            j = sc.nextInt();
            cost = sc.nextInt();
            W[i][j] = cost;
            W[j][i] = cost;
        }
        System.out.println(multistageGraph(W, e, sizeI));
    }
}
