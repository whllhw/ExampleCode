package offer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class T30 {
    public static void main(String[] args) {
        T30 t = new T30();
        t.GetLeastNumbers_Solution(new int[]{1, 3, 2, 2, 5}, 3);
    }

    /**
     * 最小的k个数
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] nums, int k) {
        return heap(nums, k);
    }

    /**
     * 使用partition函数进行划分，
     * 当划分点不是第k个时，选取左子数组或右子数组继续进行划分
     * 修改原数组，获得k个最小的数。
     * 时间复杂度O（n）
     */
    private ArrayList<Integer> partition(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return new ArrayList<>();
        }
        int left = 0;
        int right = nums.length - 1;
        int mid = partition(nums, left, right);
        // 划分的index不等于k-1
        while (mid != k - 1) {
            // 超出范围则把right缩小
            if (mid > k - 1) {
                right = mid - 1;
            } else {
                // 未到范围则把left放大
                left = mid + 1;
            }
            mid = partition(nums, left, right);
        }
        // 这下是刚好前k个是最小的了
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(nums[i]);
        }
        return ans;
    }

    /**
     * partition函数模板，填坑法实现
     *
     * @param nums  数组
     * @param left  下界
     * @param right 上界
     */
    private int partition(int[] nums, int left, int right) {
        int priv = nums[left];
        int p = left, q = right;
        while (p < q) {
            while (p < q && nums[q] >= priv)
                q--;
            nums[p] = nums[q];
            while (p < q && nums[p] <= priv)
                p++;
            nums[q] = nums[p];
        }
        nums[p] = priv;
        return p;
    }

    /**
     * 利用k个大小的最大堆进行处理
     * 相对partition能更好处理海量数据的情况（只需要存储k堆）
     * 复杂度O（n×log(n)）
     */
    private ArrayList<Integer> heap(int[] nums, int k) {
        // 思路：
        // 1. 先把k个数添加进堆中
        // 2. 建立堆
        // 3. 遍历剩下未添加进堆的元素，当其小于堆顶的时候，添加进堆顶
        // 4. 从0开始堆调整，下沉这个元素
        // 5. 最后返回这个堆即可
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length)
            return new ArrayList<>();
        int[] heap = new int[k];
        // 1.
        for (int i = 0; i < k; i++) {
            heap[i] = nums[i];
        }
        // 2.
        for (int i = nums.length / 2; i >= 0; i--) {
            adjust(heap, i, heap.length - 1);
        }
        // 3.
        for (int i = k; i < nums.length; i++) {
            // 4.
            if (nums[i] < heap[0]) {
                heap[0] = nums[i];
                adjust(heap, 0, heap.length - 1);
            }
        }
        // 5.
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(heap[i]);
        }
        return ans;
    }

    /**
     * 堆调整模板
     *
     * @param heap  待调整的堆
     * @param start 开始位置
     * @param end   结束（用于判断子节点）
     */
    private void adjust(int[] heap, int start, int end) {
        int root = start;
        int a = 2 * root + 1;
        int b = 2 * root + 2;
        while (a <= end) {
            // 选择子节点中最大的元素
            int max = heap[a];
            if (b <= end && heap[b] > max) {
                max = heap[b];
                a = b;
            }
            // 子节点比根节点大
            // 交换两个节点并把根节点变为子节点，继续下一步调整
            if (max > heap[root]) {
                int temp = heap[root];
                heap[root] = max;
                heap[a] = temp;
                root = a;
                a = 2 * root + 1;
                b = 2 * root + 2;
            } else {
                return;
            }
        }
    }

    /**
     * 优先队列实现，固定其大小即可，方法同堆排序
     */
    public ArrayList<Integer> PriorityQueue(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length)
            return new ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, Comparator.reverseOrder());
        for (int i = 0; i < nums.length; i++) {
            if (queue.size() != k)
                queue.offer(nums[i]);
            else if (queue.peek() > nums[i]) {
                queue.poll();
                queue.offer(nums[i]);
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        Iterator<Integer> it = queue.iterator();
        while (it.hasNext()) {
            list.add(it.next());
        }
        return list;
    }
}
