package lhw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        int[] nums = new int[length];
        for (int i = 0; i < length; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.print(solve(nums));
    }

    static int solve(int[] nums) {
        int min = Integer.MAX_VALUE;
        // int offset = 0;
        for (int offset = 0; offset < nums.length / 2; offset++) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += Math.abs(nums[i] - (offset + i) % nums.length - 1);
            }
            if (sum < min) {
                min = sum;
            }
        }
        return min;
    }
}