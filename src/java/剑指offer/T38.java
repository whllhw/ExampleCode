package 剑指offer;

public class T38 {

    public static void main(String[] args) {
        T38 t = new T38();
        System.out.println(t.GetNumberOfK(new int[]{
                1, 2, 3, 3, 3, 3, 3, 6
        }, 3));
    }

    /**
     * 数字在排序数组中出现的次数
     */
    public int GetNumberOfK(int[] nums, int k) {
        // 思路：
        // 利用有序数组的二分查找，查找target的边界
        // 具体就是改进二分搜索时相等的情况
        // 1. 当=target时判断前一个数/后一个数的情况，是否是target，否则结束
        // 2. 是则改变low/high，继续搜索
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = getFirstK(nums, k, 0, nums.length - 1);
        int end = getLastK(nums, k, 0, nums.length - 1);
        if (start == -1 || end == -1) {
            return 0;
        }
        return end - start + 1;
    }

    private int getFirstK(int[] nums, int target, int low, int high) {
        if (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
                // 改进：判断前一个数，准备缩小范围
            } else if (mid - 1 >= 0 && nums[mid - 1] == target) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return getFirstK(nums, target, low, high);
    }

    /**
     * 二分搜索非递归模板
     */
    private int getLastK(int[] nums, int target, int low, int high) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
                // 改进：判断后一个数，准备缩小范围
            } else if (mid + 1 < nums.length && nums[mid + 1] == target) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
