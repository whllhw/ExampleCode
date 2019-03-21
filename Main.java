import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(Math.ceil(1.1f));
        int[] nums = { 1, 3, 2, -1, 2, 0, 10, 3, 4, 5 };
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
        String s = longestPalindrome("bab");
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

    // 桶排序

    // 堆排序

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

    // 冒泡排序，复杂度O（n^2）
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

    // 归并排序
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
        if (l >= r) {
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
        // System.out.printf("i:%d j:%d\n", i, j);
        // 相交的情况就j直接和pivot值交换
        // 此时j左边的全部都大于了pivot，i右边都全部小于pivot
        // 完成了划分操作
        nums[l] = nums[j];
        nums[j] = pivot;
        return j;
    }

    // 快速排序
    public static void quickSort(int[] nums, int l, int r) {
        if (l < r) {
            // 模拟系统调用的栈
            // 分别压栈右左的索引
            // 随后出栈成左右
            LinkedList<Integer> stack = new LinkedList<>();
            stack.push(r);
            stack.push(l);
            while (!stack.isEmpty()) {
                int left = stack.pop();
                int right = stack.pop();
                int p = partition(nums, left, right);
                if (p - 1 > left) {
                    stack.push(p - 1);
                    stack.push(left);
                }
                if (p + 1 < right) {
                    stack.push(right);
                    stack.push(p + 1);
                }
            }
            // int p = partition(nums, l, r);
            // quickSort(nums, l, p - 1);
            // quickSort(nums, p + 1, r);
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