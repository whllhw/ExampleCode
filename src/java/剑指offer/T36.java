package 剑指offer;

public class T36 {
    public static void main(String[] args) {
        T36 t = new T36();
        System.out.println(t.InversePairs(new int[]{
                1, 2, 3, 4, 5, 6, 7, 0}));
    }

    /**
     * 数组中的逆序对
     * 利用归并排序计算出左数组大于右数组的个数
     * 时间复杂度O(n×logn)
     */
    public int InversePairs(int[] array) {
        return (int) mergeSort(array, 0, array.length - 1);
    }

    private long mergeSort(int[] nums, int left, int right) {
        // 思路：
        // 可以利用归并过程中合并，计数两个子数组逆序对

        // 只有一个元素
        if (left >= right)
            return 0;
        int mid = (left + right) / 2;
        return (mergeSort(nums, left, mid) +
                mergeSort(nums, mid + 1, right) +
                merge(nums, left, mid, right)) % 1000000007;
    }

    private long merge(int[] nums, int left, int mid, int right) {
        // 归并过程，与常写的正向遍历不同，这里要逆向
        // 由于两个子数组已经是有序的，最后的项是最大的
        // 只需要比较两个最大项
        // 1. 大于，counter+=右数组剩下的元素个数
        // 2. 小于，证明左数组这一项没有符合条件（不代表右数组没有使之成立的项）
        // ，右数组移动到下一项（继续寻找成立的项）
        long counter = 0;
        int[] ans = new int[right - left + 1];
        int i = mid;
        int j = right;
        int pos = right - left;
        while (i >= left && j >= mid + 1) {
            // 最大值比较，大于最大值，当前元素逆序对数目就是右数组的长度
            if (nums[i] > nums[j]) {
                ans[pos--] = nums[i--];
                counter += j - mid;
                if (counter > 1000000007)
                    counter %= 1000000007;
            } else {
                // 不符条件，右数组移动到下一个更小的元素
                ans[pos--] = nums[j--];
            }
        }
        while (i >= left)
            ans[pos--] = nums[i--];
        while (j >= mid + 1)
            ans[pos--] = nums[j--];
        // 复制回nums数组
        for (int x = 0; x < ans.length; x++)
            nums[left + x] = ans[x];
        return counter;
    }
}
