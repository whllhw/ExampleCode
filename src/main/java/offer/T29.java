package offer;

public class T29 {
    public static void main(String[] args) {
        T29 t = new T29();
        System.out.println(t.MoreThanHalfNum_Solution(new int[]{
                1, 2, 1, 2
        }));
    }

    /**
     * 数组中出现次数超过一半的数字
     *
     * @param array 数组
     * @return int
     */
    public int MoreThanHalfNum_Solution(int[] array) {
        // 思路：鸽巢原理
        // 用counter去抵消相同的数量的数，大于一半肯定counter>=1
        // 但是counter = 1可能出现刚好一半的情况，需要提前返回
        if (array == null || array.length == 0)
            return 0;
        int ans = array[0];
        int counter = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == ans) {
                counter++;
            } else {
                counter--;
            }
            if (counter == 0) {
                ans = array[i];
                counter = 1;
                // 新的最后一个数
                if (i == array.length - 1) {
                    return 0;
                }
            }
        }
        return ans;
    }

    /**
     * 快排划分
     *
     * @param nums 数组
     * @return int
     */
    public int partition(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int target = nums.length / 2;

        int left = 0;
        int right = nums.length - 1;
        int mid = partition(nums, left, right);
        // 当mid的值不是中间值时，执行划分
        // 范围可以缩小到左、右
        while (mid != target) {
            if (mid < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            mid = partition(nums, left, right);
        }
        int ans = nums[mid];
        // 检查是否为众数
        int counter = 0;
        for (int num : nums) {
            if (num == ans)
                counter++;
        }
        if (counter * 2 > nums.length)
            return ans;
        else
            return 0;
    }

    /**
     * 划分模板
     * 填坑法
     *
     * @param nums
     * @param left  边界
     * @param right 边界
     * @return 划分的index
     */
    private int partition(int[] nums, int left, int right) {
        // 挖出了一个坑，先填入小于priv的数
        int priv = nums[left];
        int p = left;
        int q = right;
        while (p < q) {
            while (p < q && nums[q] >= priv) {
                q--;
            }
            nums[p] = nums[q];
            while (p < q && nums[p] <= priv) {
                p++;
            }
            nums[q] = nums[p];
        }
        nums[p] = priv;
        return p;
    }
}
