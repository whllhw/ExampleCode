import java.util.*;
import java.math.*;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ans = 0;
        int L = sc.nextInt();
        long K = sc.nextLong();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int[] list = new int[L];
        for (int i = 0; i < L; i++) {
            int temp = sc.nextInt();
            // list[i] = temp;
            if (min > temp) {
                min = temp;
            }
            if (max < temp) {
                max = temp;
            }
        }
        // Arrays.sort(list);
        // min = list[0];
        // max = list[L - 1];
        System.out.println(BigInteger.valueOf(max - min).add(BigInteger.valueOf(K)).divide(BigInteger.valueOf(K)));
    }
}