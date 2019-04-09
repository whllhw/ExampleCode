import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;

public class Main {

    private static PrintStream out = System.out;

    public static void main(String[] args) {
        System.out.println(Math.ceil(1.1f));
        out.println("started");
        int[] nums = {1, 3, 2, -1, 2, 0, 10, 3, 4, 5};
        // int[] b = mergeSort(a);
        // System.out.println(Arrays.toString(b));
        // int[] nums = { 2, 3, 1, 6, 3, 2, 1 };
        // insertSort(nums);
        // System.out.println(Arrays.toString(nums));
        // testKMP();
        // testTraversal();
        // testQuickSort();
        // testTopK();
        // testNorder();
        // testbubbleSort();
        // String s = longestPalindrome("bab");
        // testHeapSort();
        // heapSort(nums);
        // System.out.println(Arrays.toString(nums));
        // testMergeSort();
        // testCountSort();
        // testShellSort();
//        out.println(getAllCombinationWithFor(new int[]{1, 2, 3}));
//        out.println(permutation(new int[]{1, 1, 2}, false));
        testBig();
    }

    /**
     * 大数相乘。分治法
     */
    public static int bigMul(int x, int y) {
        // 划分原数字为高位、低位
        // => A B C D
        // 根据公式
        // = AC*2^n + ((A-B)(D-C)+AC+BD)*2^n/2 + BD
//        pass
        return 0;
    }

    public static void testBig() {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            int bits = random.nextInt(100);
            for (int j = 0; j < bits + 1; j++) {
                sb.append(Math.abs(random.nextInt()));
            }
            bits = random.nextInt(100);
            for (int j = 0; j < bits + 1; j++) {
                sb2.append(Math.abs(random.nextInt()));
            }
            BigInteger x = new BigInteger(sb.toString());
            BigInteger y = new BigInteger(sb2.toString());
            char[] z = bigAdd(sb.toString().toCharArray(), sb2.toString().toCharArray());
            char[] z1 = x.add(y).toString().toCharArray();
            if (!Arrays.equals(z, z1)) {
                out.printf("%s \n + %s \n = %s \n %s \n", sb.toString(), sb2.toString(), new String(z), new String(z1));
                out.println(i);
            } else {
                out.println("ok");
            }
        }
    }

    /**
     * 大数相加
     */
    public static char[] bigAdd(char[] x, char[] y) {
        char[] res = new char[Math.max(x.length, y.length) + 1];
        int ptr = 1;
        while (true) {
            if (x.length - ptr < 0)
                break;
            if (y.length - ptr < 0)
                break;
            res[res.length - ptr] = (char) (x[x.length - ptr] + y[y.length - ptr] - '0' - '0');
            ptr++;
        }
        while (ptr <= x.length) {
            res[res.length - ptr] = (char) (x[x.length - ptr] - '0');
            ptr++;
        }
        while (ptr <= y.length) {
            res[res.length - ptr] = (char) (y[y.length - ptr] - '0');
            ptr++;
        }
        for (int i = res.length - 1; i >= 1; i--) {
            if (res[i] >= 10) {
                res[i - 1] += res[i] / 10;
                res[i] %= 10;
            }
        }
        for (int i = 0; i < res.length; i++) {
            res[i] += '0';
        }
        if (res[0] == '0') {
            char[] s = new char[res.length - 1];
            System.arraycopy(res, 1, s, 0, res.length - 1);
            return s;
        }
        return res;
    }

    /**
     * 生成全排列数
     */
    static List<List<Integer>> permutation(int[] nums, boolean dup) {
        List<List<Integer>> ans = new ArrayList<>();
        doPermutation(nums, 0, ans, dup);
        return ans;
    }

    static void doPermutation(int[] nums, int start, List<List<Integer>> ans, boolean allowedDup) {
        // 思路
        // 下一个排列数可由当前的排列数交换依次交换位置生成
        // 结束条件：已生成最大长度
        // 本级返回
        // 本级做的事情:
        // 对于start指针，不停地与后面的数进行交换(i from start to nums.length)
        // 交换后即可认为长度+1
        // 都是往后面的数进行交换，不会出现重复的数
        // 复杂度O(n^2) 空间复杂度、栈空间O(n)
        if (start == nums.length) {
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < start; i++) {
                temp.add(nums[i]);
            }
            ans.add(temp);
        }
        for (int i = start; i < nums.length; i++) {
            if (allowedDup || !isRepeat(nums, start, i)) {
                swap(nums, i, start);
                doPermutation(nums, start + 1, ans, allowedDup);
                swap(nums, i, start);
            }
        }
    }

    static boolean isRepeat(int[] nums, int pos, int end) {
        for (int i = pos; i < end; i++) {
            if (nums[end] == nums[i]) {
                return true;
            }
        }
        return false;
    }

    static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    /**
     * 生成组合
     */
    static List<List<Integer>> getAllCombinationWithFor(int[] arr) {
        // 思路：通过位图生成
        // 共有2^n-1个组合数，001 010 011 100 101 110 111
        // 每位为1表示取该元素，为0表示不取，依次遍历即可
        List<List<Integer>> ans = new ArrayList<>();
        int n = 1 << arr.length;
        for (int i = 1; i < n; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < arr.length; j++) {
                if ((i & (1 << j)) >= 1) {
                    temp.add(arr[j]);
                }
            }
            ans.add(temp);
        }
        return ans;
    }

    /**
     * 生成组合数
     */
    static List<List<Integer>> getAllCombination(int[] arr) {
        // 思路
        // 组合数共有2^n-1个数。可看成：前面生成的元素序列+当前新的元素
        // 例如: 1,2,3
        // {1}
        // +2      {1,2}       {2}
        // +3
        // {1,3}  {1,2,3}      {2,3}    {3}
        // 注意其添加的顺序
        // 复杂度O(n^2) 空间复杂度O(2^n)
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> t = new ArrayList<>();
        t.add(arr[0]);
        list.add(t);
        for (int i = 1; i < arr.length; i++) {
            int len = list.size();
            for (int j = 0; j < len; j++) {
                List<Integer> temp = new ArrayList<>(list.get(j));
                temp.add(arr[i]);
                list.add(temp);
            }
            List<Integer> x = new ArrayList<>();
            x.add(arr[i]);
            list.add(x);
        }
        return list;
    }

    public static void testReverseList() {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        ListNode tail = reverseList(head);
        while (tail != null) {
            System.out.printf("%d -> ", tail.val);
            tail = tail.next;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode newNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newNode;
    }

    static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private static int expandAround(String s, int left, int right) {
        // 当left与right相等，则是求以left right为中心的奇数长度的回文串长度。
        // 最小返回1（单个字符）
        // 当left right是连着的字符时，求解的是偶数个长度
        int i = left;
        int j = right;
        while (j < s.length() && i >= 0 && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        // 注意此处需要返回长度，去掉最后一次的自增值
        // (j-1) - (i+1) + 1
        return j - i - 1;
    }

    // 最长回文子串
    // 中心拓展法
    public static String longestPalindrome(String s) {
        // 暴力从0开始拓展出回文串
        // 分为奇数和偶数的情况
        // 奇数：求解以i为中心的回文串长度
        // 偶数：求解以i，i+1为中心的回文串长度
        // 还应对应求解出起始位置、结束位置
        // ---------------------------
        // 归纳出奇数、偶数的start, end位置
        if (s.length() == 0) {
            return "";
        }
        int start = 0;
        int stop = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAround(s, i, i + 1);
            int len2 = expandAround(s, i, i);
            int len = Math.max(len1, len2);
            if (len > stop - start) {
                start = i - (len - 1) / 2;
                stop = i + len / 2;
            }
        }

        String sb = s.substring(start, stop + 1);
        System.out.println(sb);
        return sb;
    }

    // * ******************非比较排序**********************
    // 1.计数排序
    // 2.基数排序
    // 3.桶排序
    // * 基于比较的排序最快O(nlogn)（决策树模型）
    // * 用非比较的排序能到线性时间，但需要符合一定的假设
    // * *************************************************
    // 1.计数排序
    // ***************************************************
    // 2.基数排序（多关键字排序）
    //
    // ***************************************************
    // 3.桶排序（数据结构类似hashMap，存放桶的数组，桶内元素为数组）
    // 复杂度O(n+C)
    // 1）计算每个关键字的桶映射O(n)
    // 2）在桶内进行排序，求和O(ni*logni)。决定性能好坏。
    // 空间复杂度O(n+m)，保持m个数组引用
    // 假设数均匀分布在一个范围内，把这一个范围划为几个桶
    // 以映射函数（f(i)=nums[i]/k,k^2=nums.length）把数i映射到第f(i)个桶内
    // 对桶内元素使用快排等算法
    // 依次输出桶内元素即有序
    // 应用：海量数据中排序
    // 例：500万高考分数排序（计数排序）
    // 分析：若采用基于比较排序的算法5_000_000*log(5_000_000)=1.12亿次
    // 但是分数都是在100，900之间，可采用桶排序，在毫秒时间内完成
    // 建立801个桶，分数仍进f(score)=score-100的桶内，只需要500W次
    // 然后依次输出每个桶内个数，即可得到有序序列。
    // 局限性：数据有特殊要求，数值的范围要小。
    // ***************************************************
    // 总结

    /**
     * <code>
     * String DONOTTOUCH = """
     * algorithm    |best       |average    |worst   |worst(space)
     * quickSort    |O(nlogn)               |O(n^2)  |O(logn)
     * mergeSort    |O(nlogn)                        |O(n)
     * countSort    |O(n)       |O(nlogn)            |O(n)
     * heapSort     |O(nlogn)                        |O(n)
     * bubbleSort   |O(n)       |O(n^2)              |O(1)
     * insertionSort|O(n)       |O(n^2)              |O(1)
     * selectionSort|O(n^2)                          |O(1)
     * shellSort    |O(n)       |O((nlogn)^2)        |O(1)
     * bucketSort   |O(n)       |O(n+k)    |O(n^2)   |O(n)
     * radixSort    |O(nk)                           |O(n+k)
     * """;
     * </code>
     */
    // 稳定性（比较次数）
    // 不稳定：选择、快速、希尔、堆
    // 稳定：冒泡、插入、归并、基数

    // 计数排序
    // 复杂度O(n+k)，空间O(max-min+1)
    // 范围固定在[0,k]的数据，利用地址偏移，不需要比较
    // 思路：算出数组最值，得到计数数组大小
    // 遍历整个数组，并计数
    // 预处理，统计数组（有多少个小于等于的 c(i) += c(i-1)）
    // 输出统计值，ans[--counter[nums[i]-min]] = nums[i]
    // 注意输出后自减，以便放在下一个位置
    // 用途：排序字符串、年龄排序
    // 拓展：预处理后，O(1)得到区间[a,b]内的个数（counter[b] - counter[a-1]）
    public static void countSort(int[] nums) {
        int[] ans = new int[nums.length];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        int[] counter = new int[max - min + 1];
        for (int i = 0; i < nums.length; i++) {
            counter[nums[i] - min] += 1;
        }
        for (int i = 1; i < counter.length; i++) {
            counter[i] += counter[i - 1];
        }
        // for (int i = nums.length - 1; i >= 0; i--) {
        for (int i = 0; i < nums.length; i++) {
            ans[--counter[nums[i] - min]] = nums[i];
        }
        System.arraycopy(ans, 0, nums, 0, ans.length);
        // int pos = 0;
        // for (int i = 0; i < counter.length; i++) {
        // if (counter[i] > 0) {
        // for (int j = 0; j < counter[i]; j++)
        // nums[pos++] = min + i;
        // }
        // }
    }

    public static void testCountSort() {
        for (int i = 1; i < 10000; i++) {
            int[] list = randomList(i);
            int[] list2 = Arrays.copyOf(list, list.length);
            countSort(list);
            Arrays.sort(list2);
            if (!Arrays.equals(list, list2)) {
                System.out.println(Arrays.toString(list));
                System.out.println(Arrays.toString(list2));
            }

        }
    }

    public static void testHeapSort() {
        for (int i = 1; i < 10000; i++) {
            int[] nums = randomList(i);
            int[] nums2 = Arrays.copyOf(nums, nums.length);
            // heapSortWithCopyArray(nums);
            heapSort(nums);
            Arrays.sort(nums2);
            if (!Arrays.equals(nums, nums2)) {
                System.out.println(Arrays.toString(nums));
            }
        }
    }

    // 调整为最大堆
    public static void heap(int[] nums, int start, int end) {
        // 思路：输入当前节点的位置
        // 获取左右子节点，先获取两个之中最大的节点
        // 最大的子节点与当前节点比较，若当前节点大于子节点则结束调整
        // 否则（小于子节点）交换两个节点，
        // 并把当前节点指针变为子节点，继续下次调整
        int a = start * 2 + 1;
        if (a > end) {
            return;
        }
        int b = start * 2 + 2;
        if (b <= end && nums[a] < nums[b]) {
            a = b;
        }
        if (nums[start] > nums[a]) {
            return;
        }
        int x = nums[start];
        nums[start] = nums[a];
        nums[a] = x;
        heap(nums, a, end);
    }

    // 堆排序
    // 时间复杂度O(nlogn) 空间复杂度O(1)
    // 原地排序，复杂度与输入无关，不稳定（比较次数不确定）
    // 保持堆的性质 O(logn)
    // 建立堆（O(n)）
    // 用途：TopK问题，维护k个大小的空间
    // 延伸：优先级队列（作业调度）
    public static void heapSort(int[] nums) {
        // 思路：原地调整建立最大值堆
        // （注意从len/2开始）
        // 建立完后，开始输出根节点（与最后面的元素交换）
        // 堆大小减1，并调整堆。直到只有一个元素时排序结束
        int len = nums.length;
        int s = len / 2;
        for (int i = s; i >= 0; i--) {
            heap(nums, i, len - 1);
        }
        for (int i = len - 1; i > 0; i--) {
            int temp = nums[i];
            nums[i] = nums[0];
            nums[0] = temp;
            heap(nums, 0, i - 1);
        }

    }

    // 使用额外空间的堆排序（时间复杂度O(nlogn)空间O(n)）
    public static void heapSortWithCopyArray(int[] nums) {
        // 1. 堆是完全二叉树
        // 2. 每个节点都要小于等于其子节点（最小堆）
        // 3. 每个节点都要大于等于其子节点（最大堆）
        // -------------------------------------
        // 数组模拟完全二叉树
        // i节点的左节点 2*i+1，右节点 2*i+2
        // i的父节点 (i-1)/2
        // -------------------------------------
        // 先建立堆（需要不断调整使变化仍为堆）
        int[] heap = new int[nums.length];
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            int j = size++;
            // 调整堆，比较父节点，如果父节点大于当前节点，则交换两个节点
            // 并改变当前的指针，进入下次循环
            // 否则完成此次调整
            while (j > 0) {
                int p = (j - 1) / 2;
                if (heap[p] > nums[i]) {
                    heap[j] = heap[p];
                    j = p;
                } else {
                    break;
                }
            }
            heap[j] = nums[i];
        }
        // 出堆的过程
        // 每次把根节点输出到数组里
        // 然后对比其子节点，选择最小的一个替换根节点的位置
        // 设置当前节点为子节点，继续向下调整，直到当前节点小于其子节点
        int pos = 0;
        while (size > 0) {
            int cur = 0;
            nums[pos++] = heap[cur];
            int x = heap[--size];
            while (cur * 2 + 1 < size) {
                int a = cur * 2 + 1;
                int b = cur * 2 + 2;
                if (b < size && heap[b] < heap[a]) {
                    a = b;
                }
                if (heap[a] >= x) {
                    break;
                }
                heap[cur] = heap[a];
                cur = a;
            }
            heap[cur] = x;
        }
    }

    public static void testbubbleSort() {
        for (int i = 1; i < 10000; i++) {
            int[] nums = randomList(i);
            int[] nums2 = Arrays.copyOf(nums, nums.length);
            bubbleSort(nums);
            // quickSort(nums, 0, nums.length - 1);
            Arrays.sort(nums2);
            int j = 0;
            while (j < nums.length && nums[j] == nums2[j])
                j++;
            if (j != nums.length) {
                System.out.println(Arrays.toString(nums));
            }
            // System.out.println(i);
        }
    }

    // 冒泡排序，复杂度O（n^2）空间复杂度O(1)
    // 不停地比较，把最大的数冒到最后去
    // 可添加一个改动位，以后的数组都未改动时可直接退出循环
    // 最优情况是数组已经有序，复杂度O（n）
    public static void bubbleSort(int[] nums) {
        boolean flag = true;
        for (int i = 0; i < nums.length && flag; i++) {
            flag = false;
            for (int j = 1; j < nums.length - i; j++) {
                if (nums[j - 1] > nums[j]) {
                    int temp = nums[j - 1];
                    nums[j - 1] = nums[j];
                    nums[j] = temp;
                    flag = true;
                }
            }
        }
    }

    public static void testTopK() {
        for (int i = 1; i < 1000000; i++) {
            int[] nums = randomList(i);
            int K = (int) (Math.random() * i) + 1;
            assert K > 0 && K <= i;
            int ans = topK(nums, K, 0, nums.length - 1);
            int[] nums2 = Arrays.copyOf(nums, nums.length);
            Arrays.sort(nums2);
            if (ans != nums2[K - 1]) {
                System.out.printf("error: %s topK %d %d\n", Arrays.toString(nums2), ans, nums2[K - 1]);
            }
        }
    }

    // 快速排序算法找topK元素
    // 与快排是同一作者，和快排类似的思维，也是不稳定算法
    // 复杂度最坏O(n^2)平均O(nlgn)
    public static int topK(int[] nums, int K, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        // 获取划分的位置
        // 这样前pos个元素都是小于pivot的，得到前pos小元素
        int pos = partition(nums, left, right);
        // 得到当前划分点与left的距离
        int dis = pos - left + 1;
        // 有三种情况，
        // 划分点大于K，则第K大的元素在划分点的左边，递归调用 left pos-1 即可
        // 划分点等于K，则划分点就是第K大的元素
        // 划分点小于K，第K大元素在右边，递归调用 pos+1 right，注意K的位置变为K-dis
        if (dis > K) {
            return topK(nums, K, left, pos - 1);
        } else if (dis < K) {
            return topK(nums, K - dis, pos + 1, right);
        } else {
            return nums[pos];
        }

    }

    public static void testTraversal() {

        TreeNode root = new TreeNode(new TreeNode(new TreeNode(null, null, 4), new TreeNode(null, null, 5), 2),
                new TreeNode(new TreeNode(null, null, 6), new TreeNode(null, null, 7), 3), 1);
        preorderTraversal(root);
        midorderTraversal(root);
        afterorderTraversal(root);
    }

    static class TreeNode {
        public TreeNode left;
        public TreeNode right;
        public int val;

        public TreeNode(TreeNode left, TreeNode right, int val) {
            this.left = left;
            this.right = right;
            this.val = val;
        }
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    public static void testNorder() {
        List<Node> list3 = new ArrayList<>();
        list3.add(new Node(5, null));
        list3.add(new Node(6, null));
        List<Node> list2 = new ArrayList<>();
        List<Node> list1 = new ArrayList<>();
        list1.add(new Node(7, null));
        list2.add(new Node(2, list3));
        list2.add(new Node(3, list1));
        List<Node> list0 = new ArrayList<>();
        list0.add(new Node(8, null));
        list2.add(new Node(4, list0));
        Node root = new Node(1, list2);
        NafterorederTraversal(root);
        NpreorederTraversal(root);
    }

    // N叉树后序遍历
    public static void NafterorederTraversal(Node node) {
        // 是二叉树的拓展
        // 考察栈顶的节点
        // 考察一个节点无子节点，
        // 或者上次访问的节点是子节点最后一个，则出栈访问该节点
        // 否则把其所有的节点倒序入栈（为了可正序出栈）
        // 继续下一次遍历
        if (node == null) {
            return;
        }
        LinkedList<Node> stack = new LinkedList<>();
        Node cur = node;
        Node lastVisited = node;
        stack.push(node);
        while (!stack.isEmpty()) {
            cur = stack.peek();
            if (cur.children == null || cur.children.isEmpty()
                    || lastVisited == cur.children.get(cur.children.size() - 1)) {
                System.out.printf("%d ", cur.val);
                lastVisited = cur;
                stack.pop();
                continue;
            }
            for (int i = cur.children.size() - 1; i >= 0; i--) {
                stack.push(cur.children.get(i));
            }
        }
    }

    // 二叉树后序遍历
    public static void afterorderTraversal(TreeNode node) {
        // 与前序遍历类似
        // 不过需要添加一个访问过的节点
        LinkedList<TreeNode> stack = new LinkedList<>();
        // 当前考察的节点
        TreeNode cur = node;
        // 上次访问过的节点，初始化可为null
        TreeNode lastVisited = node;
        while (cur != null || !stack.isEmpty()) {
            // 还是先一直往左走
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 查看栈顶的节点
            cur = stack.peek();
            // 如果其右子树是空的或者上次访问的节点是他的右子树
            // 则进行输出，并更新lastVisited
            // 弹出这个节点，并把当前节点设置为null，以便下次继续访问栈顶节点
            if (cur.right == null || cur.right == lastVisited) {
                System.out.printf("%d ", cur.val);
                lastVisited = cur;
                stack.pop();
                cur = null;
                // 没有访问过，则继续往右考察
            } else {
                cur = cur.right;
            }
        }
    }

    /**
     * 二叉树中序遍历
     */
    public static void midorderTraversal(TreeNode node) {
        // 与前序遍历相似
        // 不过在考察时先不输出当前的节点
        // 直到左子树为空时再访问当前的节点
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur = node;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
                System.out.printf("%d ", cur.val);
                cur = cur.right;
            }
        }
    }

    // N叉树的前序遍历
    public static void NpreorederTraversal(Node root) {
        // 思路同N叉树的后序遍历，不过把访问放在前面了
        // 节点不为空时访问该节点，并将节点的子节点全部倒序放入栈中
        // 每次取出栈顶元素进行访问
        // 注意当子节点为空时，需要把cur设置为空，避免无法结束循环
        if (root == null) {
            return;
        }
        LinkedList<Node> stack = new LinkedList<>();
        Node cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                System.out.printf("%d ", cur.val);
                if (cur.children != null && !cur.children.isEmpty()) {
                    for (int i = cur.children.size() - 1; i >= 0; i--) {
                        stack.push(cur.children.get(i));
                    }
                } else {
                    cur = null;
                }
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
            }
        }
    }

    // 二叉树的前序遍历
    public static void preorderTraversal(TreeNode node) {
        // 栈用于记录访问的节点，类似调用栈
        LinkedList<TreeNode> stack = new LinkedList<>();
        // 表示当前的节点
        TreeNode cur = node;
        // 当前节点为空，且调用栈中无节点
        while (cur != null || !stack.isEmpty()) {
            // 当前节点不为空，访问它
            // 放入栈中，以便后面回溯
            // 一直往左走，都访问一遍
            while (cur != null) {
                System.out.printf("%d ", cur.val);
                stack.push(cur);
                cur = cur.left;
            }
            // 此时当前节点已经无左节点，
            // 弹出一个准备访问它的右节点
            if (!stack.isEmpty()) {
                cur = stack.pop();
                cur = cur.right;
            }
        }
    }

    // 希尔排序（改进版插入排序）
    // 解决插入算法步进恒为1，增大步长序列使数据基本有序，
    // 最后使用插入排序时能够更快
    // 增大步长序列，每次取（n/=2）
    // （对每一列的数据进行插入）
    // 10 14 73 25 23
    // 13 27 94 33 39
    // 25 59 94 65 82
    // 步长：5
    // 10 14 73
    // 25 23 13
    // 27 94 33
    // 39 25 59
    // 94 65 82
    // 步长：3
    // 缩小步长为1，即是插入排序
    public static void shellSort(int[] nums) {
        // int[] steps = { 4, 2, 1 };
        int step = nums.length / 2;
        while (step > 0) {
            for (int j = 1; j < nums.length; j += step) {
                int i = j;
                while (i >= step && nums[i - step] > nums[i]) {
                    int temp = nums[i - step];
                    nums[i - step] = nums[i];
                    nums[i] = temp;
                    i -= step;
                }
            }
            step /= 2;
        }
    }

    public static void testShellSort() {
        for (int i = 1; i < 10000; i++) {
            int[] list = randomList(10 * i);
            int[] list2 = Arrays.copyOf(list, list.length);
            shellSort(list);
            Arrays.sort(list2);
            if (!Arrays.equals(list, list2)) {
                System.out.println(Arrays.toString(list));
                System.out.println(Arrays.toString(list2));
            }
            System.out.println(i);
        }
    }

    // 插入排序
    // 时间复杂度O(n^2)空间复杂度O(1)
    // 对于有序序列复杂度为线性，最坏情况数组是逆序
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

    public static void testMergeSort() {
        int[] nums = randomList(1024);
        mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
        System.out.println(counter);
    }

    public static int counter = 0;

    public static void merge(int[] nums, int left, int mid, int right) {
        counter++;
        int[] ans = new int[right - left + 1];
        int i = left, j = mid + 1, pos = 0;
        // 由于两个数组已经是有序的了，只需要比较开头的元素大小就能确定位置
        // 比较次数最多情况是两个数组交错，次数为2n-1
        // 最少为一个数组全部小于第二个数组，次数为n
        while (i <= mid && j <= right) {
            if (nums[i] > nums[j]) {
                ans[pos++] = nums[j++];
            } else {
                ans[pos++] = nums[i++];
            }
        }
        while (i <= mid)
            ans[pos++] = nums[i++];
        while (j <= right)
            ans[pos++] = nums[j++];
        for (i = 0; i < ans.length; i++)
            nums[left + i] = ans[i];
    }

    // 归并排序
    // 时间复杂度O(nlogn) 空间复杂度O(n)+O(logn)
    // 不断地把数组平分，分到只有一个元素时认为有序开始归并
    // 不论数组的有序情况，每次都合并消耗O(n)时间，进行O(logn)次合并
    // 因此是稳定的算法
    // 由于是递归（最大深度logn）和复制数组（最大n），空间复杂度O(n+logn)
    public static void mergeSort(int[] nums, int left, int right) {
        // 数组个数小于等于 1 是认为是有序的
        if (left >= right) {
            return;
        }
        // 分：
        // 把原数组分成等量的两份
        // 分别调用自身进行排序
        // 归并：
        // 调用归并把两个排好序的数组合并成一个
        int mid = (right + left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    // 划分从l到r的数组
    // 小于pivot值的放在左半部分，大于pivot值放在右半部分
    // 最后返回pivot的位置
    public static int partition(int[] nums, int left, int right) {
        if (left >= right) {
            return -1;
        }
        // 选中中轴值为第一个
        // i 就要从第二个开始
        int pivot = nums[left];
        int l = left + 1;
        int r = right;
        while (l <= r) {
            // i一直向中间移动到第一大于划分值为止
            while (l <= right && nums[l] < pivot) {
                l++;
            }
            // j一直向中间移动到第一个小于划分值为止
            while (r >= left && nums[r] > pivot) {
                r--;
            }
            // 判断有无相交
            if (l < r) {
                // 无相交直接交换两个值即可
                int t = nums[l];
                nums[l] = nums[r];
                nums[r] = t;
                // 注意与pivot值相同的情况就需要手动向中间移动，不然会出现死循环
                // 不相同的情况在后续迭代指针中会移动
                if (nums[l] == nums[r]) {
                    l++;
                    r--;
                }
            } else {
                break;
            }
        }
        // System.out.printf("i:%d j:%d\n", i, j);
        // 相交的情况就j直接和pivot值交换
        // 此时j左边的全部都大于了pivot，i右边都全部小于pivot
        // 完成了划分操作
        nums[left] = nums[r];
        nums[r] = pivot;
        return r;
    }

    // 快速排序
    // 时间复杂度O(nlogn)空间复杂度O(logn)递归树最高logn
    // 不稳定的算法
    // 最坏情况：顺序或逆序O(n^2)
    // 最好情况：O(n)（每次都选到最中间的元素，平分整个数组）
    public static void quickSort(int[] nums, int l, int r) {
        if (l < r) {
            // 模拟系统调用的栈
            // 分别压栈右左的索引
            // 随后出栈成左右
            // LinkedList<Integer> stack = new LinkedList<>();
            // stack.push(r);
            // stack.push(l);
            // while (!stack.isEmpty()) {
            // int left = stack.pop();
            // int right = stack.pop();
            // int p = partition(nums, left, right);
            // if (p - 1 > left) {
            // stack.push(p - 1);
            // stack.push(left);
            // }
            // if (p + 1 < right) {
            // stack.push(right);
            // stack.push(p + 1);
            // }
            // }
            int p = partition(nums, l, r);
            quickSort(nums, l, p - 1);
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

    // 生成指定大小的随机元素
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

    public static void testKMP() {
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

    // KMP算法，模式匹配
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
        if (Math.round(t) == a)
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