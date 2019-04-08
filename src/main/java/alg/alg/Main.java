package alg.alg;

import alg.template.Base;

import java.util.Arrays;
import java.util.List;

public class Main extends Base {
    // 分治：求解最近点对问题
    public static Point solveClosetPoint(List<Point> pointList, int left, int right) {
        if (left > right) {
            return null;
        }
        return null;
    }

    // 分治：求解循环赛
    public static void roundRobinTour(int n) {

    }

    public static int partition(int[] nums, int left, int right) {
        // 填坑法实现划分元素
        // 思路：
        // 第1个元素是坑，从高位找到小于标准值的填到低位
        // 此时高位元素为坑，于是又从地位找元素填坑
        // 直到两者相遇，表示为同一个坑，用标准值填即可
        // 复杂度O(n)
        if (left >= right) {
            return -1;
        }
        int pivot = nums[left];
        int low = left;
        int high = right;
        while (low < high) {
            while (high > low && nums[high] >= pivot) {
                high--;
            }
            nums[low] = nums[high];
            while (low < high && nums[low] <= pivot) {
                low++;
            }
            nums[high] = nums[low];
        }
        nums[low] = pivot;
        return low;
    }

    /**
     * 求解topK问题
     * 复杂度O(nlogn)
     */
    public static int topK(int[] nums, int start, int end, int k) {
        // 思路：使用快排中的划分，将原数组划为logn大小的
        // 根据中间值来判断
        // mid = k pivot即是topK
        // mid > k topK在左边
        // mid < k topK在右边并更新k值=k-dis
        // 注意：递归调用时mid与左边界的距离才是k（dis=mid-start+1）
        if (start == end) {
            return nums[start];
        }
        // x0 x1 x2 x3 x4 pos x6 x7 x8
        //  pos > k 时,在左部分,仍然是第k个
        // x1 x2 x3 x4
        // pos < k 时,在右部分，不是第k个了
        // x5 x6 x7
        int mid = partition(nums, start, end);
        int dis = mid - start + 1;
        if (dis > k) {
            return topK(nums, start, mid - 1, k);
        } else if (dis < k) {
            return topK(nums, mid + 1, end, k - dis);
        }
        return nums[mid];
    }

    /**
     * 复杂度O(nlogn) 最坏O(n^2)
     */
    public static void quickSort(int[] nums, int start, int end) {
        if (end <= start) {
            return;
        }
        int mid = partition(nums, start, end);
        quickSort(nums, start, mid - 1);
        quickSort(nums, mid + 1, end);
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{
//                2, 3, 1, 3, 2, 10, 5, 3, 1
//        };
//        int[] copy = Arrays.copyOf(nums, nums.length);
//        Arrays.sort(copy);
//        System.out.println(copy[8 - 1]);
//        System.out.println(topK(nums, 0, nums.length - 1, 8));
//        testTopK();
//        testQuickSort();
    }

    public static void testTopK() {
        for (int i = 1; i < 1000000; i++) {
            int[] nums = randomList(i);
            int K = (int) (Math.random() * i) + 1;
            assert K > 0 && K <= i;
            int ans = topK(nums, 0, nums.length - 1, K);
            int[] nums2 = Arrays.copyOf(nums, nums.length);
            Arrays.sort(nums2);
            System.out.println(i);
            if (ans != nums2[K - 1]) {
                System.out.printf("error: %s topK %d %d\n", Arrays.toString(nums2), ans, nums2[K - 1]);
            }
        }
    }

    public static int[] randomList(int len) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * 100000);
        }
        return ans;
    }

    public static void testQuickSort() {
        for (int i = 286; i < 100000; i++) {
            // System.out.println(i);
            int[] ans = randomList(i);
            int[] ans2 = Arrays.copyOf(ans, ans.length);
            long start = System.currentTimeMillis();
            Arrays.sort(ans);
            long stop = System.currentTimeMillis();
            String system = Arrays.toString(ans);
            long start2 = System.currentTimeMillis();
            quickSort(ans2, 0, ans2.length - 1);
            int[] temp = ans2;
            // int[] temp = quickSortWithCopyArray(ans2, ans2.length);
            long stop2 = System.currentTimeMillis();
            String self = Arrays.toString(temp);
            System.out.printf("i:%d sys:%d my:%d\n", i, stop - start, stop2 - start2);
            if (!system.equals(self)) {
                System.out.println(self);
                System.out.println(system);
                return;
            }
        }
    }

    static class Point {
        int x;
        int y;
    }
}
