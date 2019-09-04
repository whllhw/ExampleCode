package alg.alg;

import alg.template.Base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Exp1 extends Base {

    /**
     * 分治：求解最近点对问题
     */
    public static MutPoint doSolveClosetPoint(List<Point> pointList, int left, int right) {
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
            return new MutPoint(pointList.get(left),
                    pointList.get(right - 1),
                    pointList.get(left).getDistance(pointList.get(right - 1)));
        }
        int mid = left + (right - left) / 2;
        double mid_x = pointList.get(mid).x;
        MutPoint mid_a = doSolveClosetPoint(pointList, left, mid);
//        System.out.println(mid_a);
        MutPoint mid_b = doSolveClosetPoint(pointList, mid, right);
//        System.out.println(mid_b);
        double min_mid = Math.min(mid_a.dis, mid_b.dis);
        MutPoint min_point_from_sub = mid_a.dis < mid_b.dis ? mid_a : mid_b;
        List<Point> point_set_left = new ArrayList<>();
        List<Point> point_set_right = new ArrayList<>();
        int pos = mid;
        while (pos < right
                && pointList.get(pos).x <= mid_x + min_mid) {
            point_set_left.add(pointList.get(pos));
            pos++;
        }
        pos = mid - 1;
        while (pos >= left
                && pointList.get(pos).x >= mid_x - min_mid) {
            point_set_right.add(pointList.get(pos));
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
        if (n <= 0) {
            return null;
        }
        int[][] ans = new int[n][n];
        doRoundRobinTour(ans, n);
//        println(ans);
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
        // 找出近似的中位数，与left交换
        int middlePos = solveMiddlePos(nums, left, right);
        int temp = nums[middlePos];
        nums[middlePos] = nums[left];
        nums[left] = temp;

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
//        System.out.println(String.format("左右划分大小：%d %d", low - left, right - low));
        return low;
    }

    /**
     * 求解topK问题
     * 复杂度O(n)
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
        if (end - start + 1 < 75) {
            Arrays.sort(nums);
            return nums[k - 1];
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
     * 寻找中间位置的近似解
     * 目的：避免划分时选择到最差的情况，最坏复杂度到O(n^2)
     */
    public static int solveMiddlePos(int[] nums, int start, int end) {
        // 思路：
        // 1. 将原始数组划分为5个小数组
        // 2. 使用插入排序每个小数组
        // 3. 此时取小数组的中间值（2）再进行排序
        // 4. 得到中位数的中位数，即可保证最坏划分为1/4 3/4，不会出现更极端的情况
        // 消耗O(n)时间，避免出现O(n^2)的情况
        // 注意维护排序时索引的变化
        final int eachSubSize = 5;
        int size = end - start + 1;
        int subSize = size / eachSubSize;
        if (subSize == 0) {
            return start;
        }
        int[][] list = new int[subSize][eachSubSize];
        int[][] index = new int[subSize][eachSubSize];
        int pos = start;
        for (int i = 0; i < subSize; i++) {
            for (int j = 0; j < eachSubSize; j++) {
                list[i][j] = nums[pos];
                index[i][j] = pos;
                pos++;
            }
        }
        for (int i = 0; i < subSize; i++) {
            insertionSortMut(list[i], index[i]);
        }
        int[] midList = new int[subSize];
        int[] midIndex = new int[subSize];
        for (int i = 0; i < subSize; i++) {
            midList[i] = list[i][eachSubSize / 2];
            midIndex[i] = index[i][eachSubSize / 2];
        }
        insertionSortMut(midList, midIndex);
        return midIndex[subSize / 2];
    }

    public static void insertionSortMut(int[] list, int[] index) {
        for (int i = 1; i < list.length; i++) {
            while (i >= 1 && list[i] < list[i - 1]) {
                int temp = list[i];
                list[i] = list[i - 1];
                list[i - 1] = temp;
                temp = index[i];
                index[i] = index[i - 1];
                index[i - 1] = temp;
                i--;
            }
        }
    }

    /**
     * 快速排序。复杂度O(nlogn) 最坏O(n^2)
     */
    public static void quickSort(int[] nums, int start, int end) {
        if (end <= start) {
            return;
        }
        int mid = partition(nums, start, end);
        quickSort(nums, start, mid - 1);
        quickSort(nums, mid + 1, end);
    }

    public static int[] randomList(int len) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * 100000);
        }
        return ans;
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
