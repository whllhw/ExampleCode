package 算法实验;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Package01 {

    public static void main(String[] args) throws FileNotFoundException {
        Package01 p = new Package01();
        p.input(new FileInputStream("/home/jax/IdeaProjects/code/src/main/java/alg/Package01.txt"));
    }

    public void input(InputStream inputStream) {
        Scanner sc = new Scanner(inputStream);
        int len = sc.nextInt();
        int totalWeight = sc.nextInt();
        int[] values = new int[len];
        int[] weight = new int[len];
        for (int i = 0; i < len; i++) {
            values[i] = sc.nextInt();
            weight[i] = sc.nextInt();
        }
        solve(weight, values, totalWeight);
    }

    private void printResult(LinkedList<Node> stack, int[] weight) {
        Iterator<Node> iter = stack.descendingIterator();
        Node root = iter.next();
        int baseWeight = root.v;
        int i = 0;
        while (iter.hasNext()) {
            Node node = iter.next();
            if (node.w - baseWeight == weight[i]) {
                baseWeight = node.w;
                System.out.printf("%d->%d\n", i + 1, weight[i]);
            }
            i++;
        }
    }

    /**
     * 分支定界法求解01背包问题
     */
    public void solve(int[] weight, int[] values, int totalWeight) {
        // 思路：
        // 利用上界函数进行减枝，求解状态空间树
        // ub=v+(W-w)*(vi/wi)
        // 已选物品的总价值v，加上背包剩余W-w空间乘最佳单位回报（剩下物品的最大值）

        // 1. 按价值排序
        double[] preValuesDivWeight = new double[values.length];
        for (int i = 0; i < weight.length; i++) {
            preValuesDivWeight[i] = (double) values[i] / weight[i];
        }
        sort(preValuesDivWeight, weight, values);
        // 2. 记录状态空间
        LinkedList<Node> stack = new LinkedList<>();
        Node root = new Node();
        root.ub = preValuesDivWeight[0] * totalWeight;
        stack.push(root);
        int size = weight.length;
        // 对于每个物品都有放 不放两种情况
        // 不放：背包空间不足
        // 当都可行时选择上界函数值最大的那个
        for (int currentIndex = 0; currentIndex < size; currentIndex++) {

            Node top = stack.peek();
            // 不选择物品
            Node node0 = new Node();
            if (currentIndex < size - 1) {
                // 剩下还有物品可选
                node0.ub = top.v + Math.max(0, totalWeight - top.w) * preValuesDivWeight[currentIndex + 1];
            } else {
                // 无物品可选了
                node0.ub = top.v;
            }
            node0.v = top.v;
            node0.w = top.w;
            if (weight[currentIndex] + top.w <= totalWeight) {
                // 可以选择当前的物品
                Node node1 = new Node();
                node1.w = weight[currentIndex] + top.w;
                node1.v = values[currentIndex] + top.v;
                if (currentIndex < size - 1) {
                    // 剩下还有物品可选
                    node1.ub = node1.v +
                            Math.max(0, totalWeight - node1.w) * preValuesDivWeight[currentIndex + 1];
                } else {
                    // 无物品可选了
                    node1.ub = node1.v;
                }
                // 比较两个节点的上界
                if (node0.ub > node1.ub) {
                    stack.push(node0);
                } else {
                    stack.push(node1);
                }
            } else {
                // 背包不能容下当前的物品
                // 只能放弃当前物品
                stack.push(node0);
            }
        }
        printResult(stack, weight);
    }

    private void sort(double[] nums, int[]... values) {
        // 对于有序序列复杂度为O（n）（检查一遍有序即可）
        // 步骤
        // 1.认为第一元素是有序的
        // 2.每次与前一个元素比较，当小于时就交换位置，指针向前移动
        // 3.不小于时，就到下一个位置
        for (int i = 1; i < nums.length; i++) {
            while (i > 0 && nums[i - 1] < nums[i]) {
                swap(nums, i, i - 1);
                for (int[] v : values) {
                    swap(v, i, i - 1);
                }
                i--;
            }
        }
    }

    private void swap(double[] nums, int i, int j) {
        double temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    class Node {
        int w = 0;
        int v = 0;
        double ub = 0;

        @Override
        public String toString() {
            return "Node{" +
                    "w=" + w +
                    ", v=" + v +
                    ", ub=" + ub +
                    '}';
        }
    }
}