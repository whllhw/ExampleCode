package 剑指offer;

import java.util.ArrayList;

public class T41 {
    public static void main(String[] args) {
        T41 t = new T41();
        t.FindContinuousSequence(9);
    }

    /**
     * 和为s的连续正数序列
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        // 思路：可以参考和为s的两个数字，使用两个指针，但是位置初始化为1,2
        // 随后也是一样的根据大小进行移动位置
        // 改进：使用了一个curSum记录当前low+...+high的值
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        // 初始化位置：1 2
        int low = 1;
        int high = 2;
        int curSum = low + high;
        // 至少要两个数，边界值为sum/2 (sum+1)/2
        int maxHigh = (sum + 1) / 2;
        while (high <= maxHigh) {
            if (curSum == sum) {
                // 输出
                ArrayList<Integer> tmp = new ArrayList<>();
                for (int i = low; i <= high; i++) {
                    tmp.add(i);
                }
                ans.add(tmp);
                // 移动到下一个位置
                curSum -= low;
                low++;
            } else if (curSum > sum) {
                // 太大了，缩小点
                curSum -= low;
                low++;

            } else {
                // 不够，放大点
                high++;
                curSum += high;
            }
        }
        return ans;
    }

    /**
     * if (low == high) {
     * high++;
     * curSum += high;
     * }
     * 和为s的两个数字
     * 在递增序列中，查找和为s的两个数字
     */
    public ArrayList<Integer> FindNumbersWithSum(int[] nums, int target) {
        // 思路：头尾双指针法，往中间靠拢
        // 当指针之和小于target时 low++
        // 等于时，输出
        // 大于时，high--
        // 结束条件为low<high
        int low = 0;
        int high = nums.length - 1;
        ArrayList<Integer> ans = new ArrayList<>();

        while (low < high) {
            int tmp = nums[low] + nums[high];
            if (tmp < target) {
                low++;
            } else if (tmp == target) {
                ans.add(nums[low]);
                ans.add(nums[high]);
                return ans;
            } else {
                high--;
            }
        }
        return ans;
    }
}
