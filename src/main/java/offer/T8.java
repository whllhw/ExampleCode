package offer;

public class T8 {
    /**
     * 旋转数组的最小数字
     * 给定一个递增数组的旋转数组，找出其中的最小数字
     * 旋转数组：类似循环移位
     *
     * @param array 旋转数组
     * @return 最小数字
     */
    public int minNumberInRotateArray(int[] array) {
        // 思路：
        // 二分查找的变形
        // 题目其实就是找出两个子数组的分界线，
        // 由递增条件可知，中间要么在较大的数组中，要么就在较小的数组中
        // 1.当中间值大于left，表示在较大的数组中，要搜索的值就在后一部分
        // 2.否则当中间值小于right，就是在当前的数组中，可以调整right值
        // 由于到了搜索的数组中，left一定指向前部分的最后一个，right指向后部分的第一个
        // 最后的最小数字就是right所指元素
        if (array == null || array.length == 0) {
            return 0;
        }

        int left = 0;
        int right = array.length - 1;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (right - left == 1) {
                return array[right];
            }
            if (array[left] == array[mid] && array[mid] == array[right]) {
                return findArray(array, left, right);
            }
            if (array[mid] >= array[left]) {
                left = mid;
            } else if (array[mid] <= array[right]) {
                right = mid;
            }
        }
        return array[right];
    }

    public int findArray(int[] nums, int left, int right) {
        int min = Integer.MAX_VALUE;
        for (int i = left; i < right; i++) {
            min = Math.min(nums[i], min);
        }
        return min;
    }
}
