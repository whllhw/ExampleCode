package 剑指offer;

import java.util.Arrays;

public class T43 {
    private static ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    /**
     * n个骰子的点数
     * 计算出他们和s所有出现的概率
     * 递归实现
     *
     * @param sizeOfTotal 从size-1开始计算的，故停止在1
     * @param sum         上一个骰子的数量和
     * @param padding     （无用，用于节省空间）
     */
    public static void helper(int sizeOfTotal, int sum, int padding) {
        if (sizeOfTotal == 1) {
            int[] ret = (int[]) threadLocal.get();
            ret[sum - padding]++;
            return;
        }
        for (int i = 1; i <= 6; i++) {
            helper(sizeOfTotal - 1, sum + i, padding);
        }
    }

    public static void solve(int size) {
        // 前面的size段数据为0，这里直接使用偏移，节省了这些空间
        int len = 6 * size - size + 1;
        threadLocal.set(new int[len]);
        for (int i = 1; i <= 6; i++) {
            // 当第一个骰子数为i时，获取结果
            helper(size, i, size);
        }
    }

    public static void solve0(int size) {

    }


    public static void main(String[] args) {
        solve(2);
        System.out.println(Arrays.toString((int[]) threadLocal.get()));
        threadLocal.remove();
        // help gc
        threadLocal = null;
    }
}
