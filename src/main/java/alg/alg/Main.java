package alg.alg;

import alg.template.Base;

import java.util.*;

public class Main extends Base {
    public static void main(String[] args) {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(9.83, -81.96));
        pointList.add(new Point(-88.29, 44.76));
        pointList.add(new Point(21.97, -81.49));
        pointList.add(new Point(2.44, -1.83));
        pointList.add(new Point(-89.17, 63.58));
        pointList.add(new Point(20, -49.92));
        pointList.add(new Point(-81.21, -48.01));
        pointList.add(new Point(-33.28, -49.09));
        pointList.add(new Point(-54.05, 12.88));
        pointList.add(new Point(-64.85, -53.12));
        pointList.add(new Point(12.07, 64.91));
        pointList.add(new Point(-72.9, -21.57));
        pointList.add(new Point(12.93, -92.71));
        pointList.add(new Point(-27.71, -0.19));
        pointList.add(new Point(73.17, 32.17));
        MutPoint a = closetPoint(pointList);
        System.out.println(a);
        Random r = new Random();
        r.setSeed(0);
        while (true) {
            pointList.clear();
            for (int i = 0; i < 100000; i++) {
                pointList.add(new Point(r.nextDouble() * 1000000,
                        r.nextDouble() * 1000000));
            }
            System.out.println(closetPoint(pointList));
        }
    }

    /**
     * 分治：求解最近点对问题
     */
    public static MutPoint doSolveClosetPoint(List<Point> point_list, int left, int right) {
        /*
         * 思路：
         * 首先按x排序
         * 1. 划分整个x轴划分为左一半，右一半，求解左右子问题的最近点对
         * 2. 求解中心轴的最近点对
         * 1) 首先按y排序
         * 2) 遍历左边部分的点，寻找右部分的y轴最接近的6个点（由鸽舍原理可证明）
         * 3) 得到中间（mid - min,mid + min）的最近点
         * 3. 返回左右子问题、中间的最近点
         */
        if (right - left < 2) {
            return new MutPoint(null, null, Double.MAX_VALUE);
        } else if (right - left == 2) {
            return new MutPoint(point_list.get(left),
                    point_list.get(right - 1),
                    point_list.get(left).getDistance(point_list.get(right - 1)));
        }
        int mid = left + (right - left) / 2;
        double mid_x = point_list.get(mid).x;
        MutPoint mid_a = doSolveClosetPoint(point_list, left, mid);
//        System.out.println(mid_a);
        MutPoint mid_b = doSolveClosetPoint(point_list, mid, right);
//        System.out.println(mid_b);
        double min_mid = Math.min(mid_a.dis, mid_b.dis);
        MutPoint min_point_from_sub = mid_a.dis < mid_b.dis ? mid_a : mid_b;
        List<Point> point_set_left = new ArrayList<>();
        List<Point> point_set_right = new ArrayList<>();
        int pos = mid;
        while (pos < right
                && point_list.get(pos).x <= mid_x + min_mid) {
            point_set_left.add(point_list.get(pos));
            pos++;
        }
        pos = mid - 1;
        while (pos >= left
                && point_list.get(pos).x >= mid_x - min_mid) {
            point_set_right.add(point_list.get(pos));
            pos--;
        }
        point_set_left.sort(new compareByY());
        point_set_right.sort(new compareByY());
        double min_mid_dis = Double.MAX_VALUE;
        MutPoint min_point_com = null;
        int right_pos = 0;
        for (int i = 0; i < point_set_left.size(); i++) {
            double x = point_set_left.get(i).x;
            double y = point_set_left.get(i).y;
            right_pos = Math.max(0, right_pos - 6);
            while (right_pos < point_set_right.size()
                    && point_set_right.get(right_pos).y < y - min_mid) {
                right_pos++;
            }
            int counter = 0;
            while (counter < 6 && right_pos < point_set_right.size()) {
                double current_min_dis = point_set_left.get(i)
                        .getDistance(point_set_right.get(right_pos));
                if (current_min_dis < min_mid_dis) {
                    min_mid_dis = current_min_dis;
                    min_point_com = new MutPoint(
                            point_set_left.get(i),
                            point_set_right.get(right_pos),
                            current_min_dis
                    );
                }
                right_pos++;
                counter++;
            }
        }
        if (min_mid < min_mid_dis) {
            return min_point_from_sub;
        } else {
            return min_point_com;
        }
    }

    public static MutPoint closetPoint(List<Point> pointList) {
        pointList.sort(
                (x, y) -> {
                    if (x.x > y.x)
                        return 1;
                    else if (x.x == y.x)
                        return 0;
                    else
                        return -1;
                }
        );
        return doSolveClosetPoint(pointList, 0, pointList.size());
    }

    static class compareByY implements Comparator<Point> {
        @Override
        public int compare(Point o1, Point o2) {
            if (o1.y > o2.y) {
                return 1;
            } else if (o1.y == o2.y) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    static class MutPoint {
        public Point x;
        public Point y;
        public double dis;

        public MutPoint(Point x, Point y, double dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }

        @Override
        public String toString() {
            return String.format("%f,%s,%s", x == null ? Double.MAX_VALUE : x.getDistance(y), x == null ? "null" : x.toString(), y == null ? "null" : y.toString());
        }
    }

    /**
     * 分治：求解循环赛
     * 队伍数=2^n
     * a[i][j]表示i队，第j天的对手
     * T(n) = T(n/2) + (n/2)^2
     * 由master method可得
     * 复杂度O(n^2)
     */
    public static int[][] roundRobinTour(int n) {
        int[][] ans = new int[n][n];
        doRoundRobinTour(ans, n);
        println(ans);
        return ans;
    }

    public static void doRoundRobinTour(int[][] ans, int n) {
        if (n == 0 || n == 1) {
            ans[0][0] = 1;
            return;
        }
        doRoundRobinTour(ans, n / 2);
        // 通过递归，自底而上解决问题
        // 例：
        // 1
        // 生成第一行，然后对称复制
        // 1 2
        // 2 1
        // 生成第一、第二行，然后对称复制
        // 1 2 3 4
        // 2 1 4 3
        // 3 4 1 2
        // 4 3 2 1
        // 思路：
        // 按行按列迭代生成并复制
        int m = n / 2;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                ans[i][j + m] = ans[i][j] + m;
                ans[i + m][j] = ans[i][j + m];
                ans[i + m][j + m] = ans[i][j];
            }
        }
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

//    public static void main(String[] args) {
//        int[] nums = new int[]{
//                2, 3, 1, 3, 2, 10, 5, 3, 1
//        };
//        int[] copy = Arrays.copyOf(nums, nums.length);
//        Arrays.sort(copy);
//        System.out.println(copy[8 - 1]);
//        System.out.println(topK(nums, 0, nums.length - 1, 8));
//        testTopK();
//        testQuickSort();
//        roundRobinTour(8);
//    }

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
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getDistance(Point other) {
            return Math.sqrt(
                    Math.pow(other.x - x, 2)
                            + Math.pow(other.y - y, 2)
            );
        }

        @Override
        public String toString() {
            return String.format("(%f,%f)", x, y);
        }
    }
}
