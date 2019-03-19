import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(Math.ceil(1.1f));
        int[] nums = { 1, 3, 2, -1, 2, 0, 10, 3, 4, 5 };
        // int[] b = mergeSort(a);
        // System.out.println(Arrays.toString(b));
        // int[] nums = { 2, 3, 1, 6, 3, 2, 1 };
        insertSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    // 插入排序
    public static void insertSort(int[] nums) {
        // 对于有序序列复杂度为O（n）（检查一遍有序即可）
        // 步骤
        // 1.认为第一元素是有序的
        // 2.每次与前一个元素比较，当小于时就交换位置，指针向前移动
        // 3.不小于时，就到下一个位置
        for (int i = 1; i < nums.length; i++) {
            while (i > 0 && nums[i] < nums[i - 1]) {
                int temp = nums[i];
                nums[i] = nums[i - 1];
                nums[i - 1] = temp;
                i--;
            }
        }
    }

    public static int[] merge(int[] left, int[] right) {
        int[] ans = new int[left.length + right.length];
        int i = 0, j = 0, pos = 0;
        // 由于两个数组已经是有序的了，只需要比较开头的元素大小就能确定位置
        // 比较次数最多情况是两个数组交错，次数为2n-1
        // 最少为一个数组全部小于第二个数组，次数为n
        while (i < left.length && j < right.length) {
            if (left[i] > right[j]) {
                ans[pos++] = right[j++];
            } else {
                ans[pos++] = left[i++];
            }
        }
        while (i < left.length)
            ans[pos++] = left[i++];
        while (j < right.length)
            ans[pos++] = right[j++];
        return ans;
    }
    /**
     * 归并排序
     */
    public static int[] mergeSort(int[] nums) {
        // 数组个数小于等于 1 是认为是有序的
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        // 分：
        // 把原数组分成等量的两份
        // 分别调用自身进行排序
        // 归并：
        // 调用归并把两个排好序的数组合并成一个
        int mid = nums.length / 2;
        int[] na = new int[mid];
        int[] nb = new int[nums.length - mid];
        int pos = 0;
        for (int i = 0; i < mid; i++)
            na[i] = nums[pos++];
        for (int i = 0; i < nums.length - mid; i++)
            nb[i] = nums[pos++];
        int[] left = mergeSort(na);
        int[] right = mergeSort(nb);
        return merge(left, right);
    }

    public static int partition(int[] nums, int l, int r) {
        if (l > r) {
            return -1;
        }
        // 选中中轴值为第一个
        // i 就要从第二个开始
        int pivot = nums[l];
        int i = l + 1;
        int j = r;
        while (i <= j) {
            // i一直向中间移动到第一大于划分值为止
            while (i <= r && nums[i] < pivot) {
                i++;
            }
            // j一直向中间移动到第一个小于划分值为止
            while (j >= l && nums[j] > pivot) {
                j--;
            }
            // 判断有无相交
            if (i < j) {
                // 无相交直接交换两个值即可
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
                // 注意与pivot值相同的情况就需要手动向中间移动，不然会出现死循环
                // 不相同的情况在后续迭代指针中会移动
                if (nums[i] == nums[j]) {
                    i++;
                    j--;
                }
            } else {
                break;
            }
        }
        System.out.printf("i:%d j:%d\n", i, j);
        // 相交的情况就j直接和pivot值交换
        // 此时j左边的全部都大于了pivot，i右边都全部小于pivot
        // 完成了划分操作
        nums[l] = nums[j];
        nums[j] = pivot;
        return j;
    }

    public static void quickSort(int[] nums, int l, int r) {
        if (l < r) {
            int p = partition(nums, l, r);
            quickSort(nums, l, p);
            quickSort(nums, p + 1, r);
        }
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
            // System.out.printf("i:%d sys:%d my:%d\n", i, stop - start, stop2 - start2);
            if (!system.equals(self)) {
                System.out.println(self);
                System.out.println(system);
                return;
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

    // 使用额外空间的快排
    public static int[] quickSortWithCopyArray(int[] num, int len) {
        if (num == null || len <= 1) {
            return num;
        }
        // 最后一个数做为划分的基准
        int pivot = num[len - 1];
        int counter = 1;
        int[] less = new int[len];
        int[] greater = new int[len];
        int i = 0;
        int j = 0;
        // 把比基准小的数放到less
        // 否则把数放到greater中
        // 注意基准数不再参与后续的排序
        // 把出现相同的数量记录下来，合并时再放到一起
        for (int pos = 0; pos < len - 1; pos++) {
            if (num[pos] < pivot)
                less[i++] = num[pos];
            else if (num[pos] > pivot)
                greater[j++] = num[pos];
            else
                counter++;
        }
        int[] a = quickSortWithCopyArray(less, i);
        int[] b = quickSortWithCopyArray(greater, j);
        int[] merge = new int[i + j + counter];
        System.arraycopy(a, 0, merge, 0, i);
        for (int x = 0; x < counter; x++)
            merge[i + x] = pivot;
        System.arraycopy(b, 0, merge, i + counter, j);
        // System.out.println(Arrays.toString(merge));
        return merge;
    }

    public void testKMP() {
        String s1 = "9abcabaabc";
        String s2 = "abcaba";
        System.out.println(KMP(s1.toCharArray(), 0, s2.toCharArray()));

        s1 = "";
        s2 = "adwa";
        System.out.println(KMP(s1.toCharArray(), 0, s2.toCharArray()));

        s1 = "qwer";
        s2 = "";
        System.out.println(KMP(s1.toCharArray(), 0, s2.toCharArray()));

        s1 = "12345";
        s2 = "321";
        System.out.println(KMP(s1.toCharArray(), 0, s2.toCharArray()));

    }

    private static int[] getNext(char[] T) {
        int[] next = new int[T.length + 1];
        int i = 0;
        int j = -1;
        next[0] = -1;
        while (i < T.length) {
            if (j == -1 || T[i] == T[j]) {
                i++;
                j++;
                // j 等于 -1 时，表示返回了开头，自然是next[i] = 0（i 处发生不匹配时，返回第一字符位置）
                // 否则表示第 i 处与第 j 处匹配
                // 即若在下一处（i+1）发生不匹配，返回的地方是下一个字符（j+1）
                next[i] = j;
            } else {
                // 在 j 处发生不匹配，利用前次算出的 next[j] 返回该位置
                j = next[j];
            }
        }
        return next;
    }

    public static int KMP(char[] S, int pos, char[] T) {
        int i = pos;
        int j = 1;
        int[] next = getNext(T);
        System.out.println(Arrays.toString(next));
        while (i < S.length && j < T.length) {
            if (j == -1 || S[i] == T[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == T.length) {
            return i - j;
        }
        return -1;
    }

    public static long 斐波那契数列(int n) {
        long a = 0;
        long b = 1;
        long c = 0;
        for (int i = 0; i < n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        double t = 1.0 / Math.sqrt(5) * Math.pow((0.5 + Math.sqrt(5) / 2), n);
        if ((long) Math.round(t) == a)
            System.out.println(t);
        return a;
    }

    public static long 汉诺塔(int n, int from, int to, int temp) {
        if (n == 0) {
            return 0L;
        }
        long a = 汉诺塔(n - 1, from, temp, to);
        System.out.println(String.format("%d->%d", from, to));
        long c = 汉诺塔(n - 1, temp, to, from);
        return a + 1 + c;
    }

    public static void 中缀表达式转化后缀表达式() {
        Map<Character, Integer> map = new HashMap<>();
        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 2);
        map.put('/', 2);
        map.put('(', 0);
        String str = "9+(3-1)*3+10/2";
        StringBuilder sb = new StringBuilder();
        LinkedList<Character> list = new LinkedList<>();
        char[] ch = str.toCharArray();
        for (char i : ch) {
            if (Character.isDigit(i)) {
                sb.append(i);
                continue;
            }
            if (i == '(') {
                list.push('(');
                continue;
            }
            if (i == ')') {
                while (list.peek() != '(') {
                    sb.append(list.pop());
                }
                list.pop();
                continue;
            }
            // 栈顶元素优先级大于当前元素则全部出栈
            if (!list.isEmpty() && map.get(list.peek()) > map.get(i)) {
                while (!list.isEmpty())
                    sb.append(list.pop());
            }
            list.push(i);
        }
        while (!list.isEmpty()) {
            sb.append(list.pop());
        }
        System.out.println(sb.toString());
    }

    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid])
                hi = mid - 1;
            else if (key > a[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }
}