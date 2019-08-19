package tencent;

import java.util.Arrays;
import java.util.Scanner;

public class Q2 {
//     2
//     2 1 4 3
//     4
//     1 2 0 2

    // 1 2 3 4
    // 4 3 2 1
    // 4 3 2 1
    // 1 2 3 4

    private static int counter = 0;

    // 0
    // 6
    // 6
    // 0
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int size = (int) Math.pow(2, n);
        int[] Q = new int[size];
        for (int i = 0; i < size; i++) {
            Q[i] = sc.nextInt();
        }
        int m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            solve(Q, sc.nextInt());
        }

    }

    private static void solve(int[] Q, int q) {
        // 对Q进行翻转
        int eachSize = (int) Math.pow(2, q);
        int pos = 0;
        int temp;
        while (pos < Q.length) {
            for (int i = pos; i < pos + eachSize / 2; i++) {
                temp = Q[i];
                Q[i] = Q[pos + eachSize + pos - i - 1];
                Q[pos + eachSize + pos - i - 1] = temp;
            }
            pos += eachSize;
        }
        int[] tempQ = Arrays.copyOf(Q, Q.length);
        counter = 0;
        mergeSort(tempQ, 0, tempQ.length - 1);
        System.out.println(counter);
    }


    private static void merge(int[] nums, int left, int mid, int right) {
        // 合并两个数组，用于寻找逆序对
        int[] ans = new int[right - left + 1];
        int i = left, j = mid + 1, pos = 0;
        while (i <= mid && j <= right) {
            if (nums[i] > nums[j]) {
                ans[pos++] = nums[j++];
                counter += mid - i + 1;
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

    private static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (right + left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }
}
