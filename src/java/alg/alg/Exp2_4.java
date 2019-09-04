package alg.alg;

import alg.template.Base;

import java.io.File;
import java.util.*;

public class Exp2_4 {
    /**
     * 有向加权图的最长路径。（无环）
     * 复杂度组成：
     * O(V+E) 拓扑排序
     * O(n^2) 转换为逆邻接表
     * O(n^2) 遍历节点，并寻找其前驱节点中最长的路径
     *
     * @param cost 权重矩阵
     * @return 最长路径
     */
    public static int longsetPathInDAG(int[][] cost) {
        // 从入度为0的点开始算，（拓扑排序的顺序）
        // 所有能到v的路径中选择最长的路径
        // 递推关系：
        // dilg(v) = max( dilg(u) + w(u,v) )
        //实现思路：
        // 1. 读入权重矩阵。
        // 2. 转化为逆邻接表。
        // 3. 获取拓扑排序序列。利用逆邻接表，维护入度为0的集合...。
        // 4. 按拓扑序列寻找最大路径。利用逆邻接表，找到其相邻节点中消耗最大的，并记录下来。
        // 5. 更新节点的最长路径的父节点

        int sizeNode = cost.length - 1;
        int[] dilg = new int[sizeNode + 1];
        int[] parent = new int[sizeNode + 1];

        Map<Integer, List<Integer>> graph = convert(cost, sizeNode);
        List<Integer> dagSort = DAGSort(graph);
        int start = 0;
        int maxLen = Integer.MIN_VALUE;
        for (int node : dagSort) {
            dilg[node] = helper(node, dilg, parent, graph.get(node), cost);
            if (dilg[node] > maxLen) {
                maxLen = dilg[node];
                start = node;
            }
        }
        // 读取路径
        int p = start;
        LinkedList<Integer> res = new LinkedList<>();
        while (p != 0) {
            res.add(p);
            p = parent[p];
        }
        System.out.println("最长路径为");
        while (!res.isEmpty()) {
            System.out.print(res.poll());
            if (res.size() != 0)
                System.out.print("->");
        }

        return maxLen;
    }

    /**
     * 寻找到达 node 节点的最长路径
     *
     * @param node 本次要到达的节点
     * @param dilg 记录到达节点的消耗
     * @param list 能够到达node的节点
     * @param cost 权重矩阵
     * @return 到达node节点的最长路径
     */
    private static int helper(int node, int[] dilg, int[] parent, List<Integer> list, int[][] cost) {

        int max = Integer.MIN_VALUE;
        // 开始节点，消耗为0
        if (list.size() == 0) {
            return 0;
        }
        for (int x : list) {
            if (cost[x][node] != Integer.MAX_VALUE) {
                int temp = dilg[x] + cost[x][node];
                if (temp > max) {
                    max = temp;
                    parent[node] = x;
                }
            }
        }
        if (max == Integer.MIN_VALUE) {
            throw new IllegalArgumentException();
        }
        return max;
    }

    /**
     * 转换权重矩阵为逆临接表
     * 将出度表转为入度表
     *
     * @param cost 权重矩阵
     * @return 逆临接表
     */
    public static Map<Integer, List<Integer>> convert(int[][] cost, int size) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= size; i++) {
            graph.put(i, new LinkedList<>());
        }
        for (int i = 0; i < cost.length; i++) {
            for (int j = 0; j < cost.length; j++) {
                if (cost[i][j] != 0) {
                    graph.get(j).add(i);
                }
            }
        }
        return graph;
    }

    /**
     * 拓扑排序
     *
     * @param srcGraph 逆临接表
     * @return 结果
     */
    private static List<Integer> DAGSort(Map<Integer, List<Integer>> srcGraph) {
        // 实现方式：Kahn/DFS
        // 这里选择Kahn算法。维护一个入度为0的集合即可，当其加入排序后，对应连接的节点度减一
        // 当又出现节点度为0时加入集合。
        // 当没有入度为0的点，但是仍然有节点没有加入到结果中，则表示图存在环。

        // 深拷贝
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : srcGraph.entrySet()) {
            List<Integer> l = new LinkedList<>(entry.getValue());
            graph.put(entry.getKey(), l);
        }
        // 存放入度为0的点
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> ans = new LinkedList<>();
        while (!graph.isEmpty()) {
            boolean flag = false;
            for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
                List<Integer> v = entry.getValue();
                if (v == null || v.size() == 0) {
                    queue.add(entry.getKey());
                    flag = true;
                }
            }
            if (!flag) {
                // 存在环
                throw new IllegalArgumentException("图中存在环");
            }
            while (!queue.isEmpty()) {
                // 输出结果，并去掉该点
                int node = queue.poll();
                ans.add(node);
                graph.remove(node);
                // 删除与之相关的逆邻接矩阵
                for (List<Integer> v : graph.values()) {
                    if (v.contains(node))
                        v.remove(Integer.valueOf(node));
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(new File("/home/jax/IdeaProjects/code/src/main/java/alg/alg/Exp2_4.txt"));
        int size = sc.nextInt();
        int sizeEdge = sc.nextInt();
        int[][] cost = Base.readEdge(sc, size, sizeEdge, true);
        System.out.println("\n路径长度为：" + longsetPathInDAG(cost));

    }
}
