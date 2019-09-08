package offer2020.iqiyi;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class 排序计数 {

    private int[] nums;
    private int[] dest;
    private int counter = 0;

    /**
     * DFS暴力，数据过36%
     * leetcode 903
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int N = sc.nextInt();
        排序计数 main = new 排序计数();
        main.dest = new int[N - 1];
        main.nums = new int[N];
        for (int i = 0; i < N - 1; i++) {
            main.dest[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            main.nums[i] = i + 1;
        }

        main.solve(0);
        System.out.println(main.counter);
        sc.close();
    }

    private void solve(int pos) {
        if (pos == nums.length) {
            if (vaild()) {
                counter++;
            }
            return;
        }
        int temp = 0;
        for (int i = pos; i < nums.length; i++) {
            temp = nums[i];
            nums[i] = nums[pos];
            nums[pos] = temp;
            solve(pos + 1);
            nums[pos] = nums[i];
            nums[i] = temp;
        }
    }

    private boolean vaild() {
        for (int i = 0; i < nums.length - 1; i++) {
            if (dest[i] == 1) {
                if (nums[i + 1] <= nums[i]) {
                    return false;
                }
            } else {
                if (nums[i + 1] >= nums[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
