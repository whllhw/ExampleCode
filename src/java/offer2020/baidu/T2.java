package offer2020.baidu;

import java.io.BufferedInputStream;
import java.math.BigInteger;
import java.util.Scanner;

public class T2 {
    // 100%
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        BigInteger[] arr = new BigInteger[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new BigInteger(sc.next());
        }
        BigInteger N = BigInteger.valueOf(n);
        int index;
        BigInteger counter = BigInteger.ZERO;
        while ((index = check(arr)) != -1) {
            BigInteger time = arr[index].divide(N);
            counter = counter.add(time);
            arr[index] = arr[index].mod(N);
            for (int i = 0; i < n; i++) {
                if (i == index) {
                    continue;
                }
                arr[i] = arr[i].add(time);
            }
        }
        System.out.println(counter);
        sc.close();
    }

    private static int check(BigInteger[] arr) {
        int index = 0;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            if (arr[i].compareTo(arr[index]) > 0) {
                index = i;
            }
        }
        if (arr[index].compareTo(BigInteger.valueOf(n)) >= 0) {
            return index;
        } else {
            return -1;
        }
    }
}
