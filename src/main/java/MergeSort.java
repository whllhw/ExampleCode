import java.util.*;
import java.io.*;

class template1 {
    public static void main(String[] args) throws FileNotFoundException {
        // Scanner in = new Scanner(System.in);
        // Scanner in = new Scanner(new File("template.in"));

        // in.close();
        int[] i = {
            1,3,4,2,5,1,2,0,-1
        };
        MergeSort.sort(i);
        for(int x = 0;x<i.length;x++){
            System.out.print(i[x]);
            System.out.print(" ");
        }
    }

}

public class MergeSort {
    public static int[] sort(int[] nums) {
        int[] temp = new int[nums.length];
        sort(nums, 0, nums.length - 1, temp);
        return nums;
    }

    private static void sort(int[] nums, int left, int right, int[] temp) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sort(nums, left, mid, temp);
            sort(nums, mid + 1, right, temp);
            merge(nums, left, mid, right, temp);
        }
    }

    private static void merge(int[] nums, int left, int mid, int right, int[] temp) {

        int i = left;
        int j = mid + 1;
        int z = 0;
        while (i <= mid && j <= right) {
            if (nums[i] > nums[j]) {
                temp[z++] = nums[j++];
            } else {
                temp[z++] = nums[i++];
            }
        }
        while (i <= mid) {
            temp[z++] = nums[i++];
        }
        while (j <= right) {
            temp[z++] = nums[j++];
        }
        z = 0;
        while (left <= right) {
            nums[left++] = temp[z++];
        }

    }
}