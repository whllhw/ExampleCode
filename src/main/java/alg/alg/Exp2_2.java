package alg.alg;

import alg.template.Base;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Exp2_2 {
    /**
     * Floyd算法求解所有点对间的最小距离。时间复杂度O(n^3)
     *
     * @param cost 权重矩阵
     * @return dis 距离矩阵
     */
    public static int[][] floydAllPairsShortestPaths(int[][] cost) {
        // 递推式：加入第k个节点后更新距离矩阵
        // dis_next[i,j] = (dis_prev[i,k] + dis_prev[k,j] , dis_prev[i,j])
        int[][] dis = new int[cost.length][];
        for (int i = 0; i < cost.length; i++) {
            dis[i] = Arrays.copyOf(cost[i], cost[i].length);
        }

        for (int i = 0; i < cost.length; i++) {
            for (int j = 0; j < cost.length; j++) {
                for (int k = 0; k < cost.length; k++) {
                    if (dis[j][i] == Integer.MAX_VALUE || dis[i][k] == Integer.MAX_VALUE)
                        continue;
                    // 更新权重矩阵
                    dis[j][k] = Math.min(
                            dis[j][i] + dis[i][k], dis[j][k]
                    );

                }
            }
        }

        return dis;
    }


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("/home/jax/IdeaProjects/code/src/main/java/alg/alg/Exp2_2.txt"));
        int size = scanner.nextInt();
        int[][] cost = Base.readCost(scanner, size);
        int[][] dis = floydAllPairsShortestPaths(cost);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (dis[i][j] == Integer.MAX_VALUE)
                    System.out.print("∞");
                else
                    System.out.print(dis[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
