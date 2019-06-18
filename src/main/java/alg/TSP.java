package alg;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.PriorityQueue;
import java.util.Scanner;

public class TSP {
    int[][] graphs;
    PriorityQueue<Node> pt;

    /**
     * 分支限界法 解TSP问题
     */
    public static void main(String[] args) throws FileNotFoundException {
        TSP tsp = new TSP();
        tsp.input(new FileInputStream("/home/jax/IdeaProjects/code/src/main/java/alg/TSP.txt"));

        System.out.println(tsp.solve());
    }

    public void input(InputStream inputStream) {
        Scanner sc = new Scanner(inputStream);
        int size = sc.nextInt();
        int[][] graphs = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                graphs[i][j] = sc.nextInt();
                if (i == j) {
                    graphs[i][i] = Integer.MAX_VALUE;
                }
            }
        }
        this.graphs = graphs;
        pt = new PriorityQueue<>();
    }

    /**
     * 通过贪心算法获取上界
     */
    private int getUpBound() {
        int[] visited = new int[graphs.length];
        visited[0] = 1;
        return dfsSolveUpBound(visited, 0, 1, 0);
    }

    /**
     * @param visited          记录已经访问过的节点
     * @param currentNode      当前节点
     * @param visitedNodeCount 已经访问过的节点数
     * @param cost             已经消耗的cost
     * @return 贪心得到的cost
     */
    private int dfsSolveUpBound(int[] visited, int currentNode, int visitedNodeCount, int cost) {
        // 贪心
        // 每次选择相邻的 未被访问过的边作为下一个访问的节点
        // 结束条件则是已经访问完所有的节点
        int sizeNode = visited.length;
        if (visitedNodeCount == sizeNode) {
            // 走过了所有节点，应该返回原点了
            return cost + graphs[currentNode][0];
        }
        int minCost = Integer.MAX_VALUE;
        int p = -1;
        for (int i = 0; i < sizeNode; i++) {
            if (visited[i] == 0 && minCost > graphs[currentNode][i]) {
                minCost = graphs[currentNode][i];
                p = i;
            }
        }
        assert p >= 0;
        visited[p] = 1;
        return dfsSolveUpBound(visited, p, visitedNodeCount + 1, cost + minCost);
    }

    /**
     * 取每行最小边之和作为下界
     * 获取下界
     */
    private int getLowBound() {
        // 思路：
        // 选择每个节点最小的边
        // 总和作为下界（最小能达到的cost）
        int low = 0;
        int sizeNode = graphs.length;

        for (int[] graph : graphs) {
            int min1 = Integer.MAX_VALUE;
            int min2 = Integer.MAX_VALUE;

            for (int j = 0; j < sizeNode; j++) {
                // 这里求出最小的两个数，但是为什么没用到第二个数？
                if (min2 > graph[j]) {
                    min2 = graph[j];
                }
                if (min2 < min1) {
                    int t = min1;
                    min1 = min2;
                    min2 = t;
                }
            }
            low += min1;
        }
        return low;
    }

    /**
     * 加入p点后，获取下界函数值
     *
     * @param p 加入的节点
     */
    private int getLowBound(Node p) {
        // 限界函数的值 = 已经走过的路程
        // + (p能到的最小值 + 能到p的最小值)/2 （这里的节点有st ed两个点组成了边）
        // + 除了路径上的点的两个最短距离相加/2
        int ret = p.sumv * 2;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int sizeG = graphs.length;
        for (int i = 0; i < sizeG; i++) {
            if (p.visited[i] == 0 && min1 > graphs[i][p.st]) {
                min1 = graphs[i][p.st];
            }
        }
        ret += min1;
        for (int i = 0; i < sizeG; i++) {
            if (p.visited[i] == 0 && min2 > graphs[p.ed][i]) {
                min2 = graphs[p.ed][i];
            }
        }
        ret += min2;
        for (int i = 0; i < sizeG; i++) {
            if (p.visited[i] == 0) {
                min1 = min2 = Integer.MAX_VALUE;
                for (int j = 0; j < sizeG; j++) {
                    if (min1 > graphs[i][j]) {
                        min1 = graphs[i][j];
                    }
                }
                for (int j = 0; j < sizeG; j++) {
                    if (min2 > graphs[j][i]) {
                        min2 = graphs[j][i];
                    }
                }
                ret += min1 + min2;
            }
        }
        return ret / 2;
    }

    /**
     * 分支限界求TSP
     */
    public int solve() {
        int up = getUpBound();
        int low = getLowBound();
        int sizeG = graphs.length;
        // 从0节点开始出发，
        // 构造出start，lb为无节点算出的下界
        Node start = new Node(sizeG);
        start.st = 0;
        start.ed = 0;
        start.k = 1;
        start.visited[0] = 1;
        start.sumv = 0;
        start.lb = low;

        int ret = Integer.MAX_VALUE;
        // 加入优先队列中
        // 这样每次出队的就是lb最小的结果
        pt.add(start);

        while (pt.size() > 0) {

            Node tmp = pt.poll();
            // 只需要访问一个节点了，可能出结果了，单独处理
            if (tmp.k == sizeG - 1) {
                // 找出最后未访问的节点
                int p = -1;
                for (int i = 0; i < sizeG; i++) {
                    if (tmp.visited[i] == 0) {
                        p = i;
                        break;
                    }
                }
                // 这个是当前解的cost
                int ans = tmp.sumv + graphs[p][tmp.st] + graphs[tmp.ed][p];
                Node judge = pt.peek();
                // 若cost比队首的下界cost都小的话，
                // 那么这就是一组最优解！
                if (ans <= judge.lb) {
                    ret = Math.min(ans, ret);
                    break;
                } else {
                    //  否则这里可能只是求到了局部最优解，
                    //  但是还是先保存一下供以后对比判断
                    // 同时更新一下贪心算法求到的上界（能达到的最优解）
                    up = Math.min(up, ans);
                    ret = Math.min(ret, ans);
                    continue;
                }

            }

            // 开始广搜了，
            // 遍历没有访问过的节点入队
            for (int i = 0; i < sizeG; i++) {
                if (tmp.visited[i] == 0) {
                    // 构造出下一个要访问的节点
                    Node next = new Node(sizeG);
                    next.st = tmp.st;
                    // 更新访问的cost
                    next.sumv = tmp.sumv + graphs[tmp.ed][i];
                    // 结束节点为i
                    next.ed = i;
                    // 路径长度+1
                    next.k = tmp.k + 1;
                    // 更新visited数组
                    System.arraycopy(tmp.visited, 0, next.visited, 0, sizeG);
                    next.visited[i] = 1;
                    // 加入next后lb也要进行更新
                    next.lb = getLowBound(next);
                    // 这里如果lb都比上界大了（贪心算法得到的局部最优解）
                    // 说明这个方法肯定不是最优解，连局部最优都算不上，舍弃
                    if (next.lb > up) {
                        continue;
                    }
                    // 可能是最优解，放入优先队列中
                    pt.add(next);
                }

            }

        }
        return ret;
    }

    class Node implements Comparable<Node> {
        /**
         * 标记已走的点
         */
        int[] visited;
        /**
         * 起点
         */
        int st;
        /**
         * 起点的邻接点
         */
        int st_p;
        /**
         * 终点
         */
        int ed;
        /**
         * 终点的邻接点
         */
        int ed_p;
        /**
         * 走过的点数
         */
        int k;
        /**
         * cost
         */
        int sumv;
        /**
         * 目标函数值
         */
        int lb;

        /**
         * @param size 节点的个数
         */
        public Node(int size) {
            visited = new int[size];
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(lb, o.lb);
        }
    }
}
