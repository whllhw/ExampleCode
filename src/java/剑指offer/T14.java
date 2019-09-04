package 剑指offer;

import java.util.Arrays;

public class T14 {
    public static void main(String[] args) {
        T14 t = new T14();
        int[] nums = new int[]{
                1, 2, 3, 4, 5, 6, 7
        };
        t.reOrderArray(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 调整数组顺序使奇数位于偶数前面
     *
     * @param array 输入
     */
    public void reOrderArray0(int[] array) {
        // 双指针，类似快排中的划分，但是相对位置会被改变
        if (array == null || array.length == 0)
            return;
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            while (left < right && (array[left] & 1) == 1)
                left++;
            while (left < right && (array[right] & 1) == 0)
                right--;
            if (left < right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }
    }

    /**
     * 调整后相对位置不变版本
     *
     * @param nums 输入
     */
    public void reOrderArray(int[] nums) {
        // 思路：
        // 同样遍历数组，不同的是需要整体复制到后一块，留出空位填入

        if (nums == null || nums.length == 0)
            return;
        int i = 0;
        int j;
        while (i < nums.length) {
            while (i < nums.length && (nums[i] & 1) == 1)
                i++;
            j = i + 1;
            while (j < nums.length && (nums[j] & 1) == 0)
                j++;
            if (j >= nums.length)
                break;
            int temp = nums[j];
//            for (int k = j - 1; k >= i; k--) {
//                nums[k + 1] = nums[k];
//            }
            if (j - i >= 0)
                System.arraycopy(nums, i, nums, i + 1, j - i);
            nums[i++] = temp;
            System.out.println(Arrays.toString(nums));
        }

    }
}