package offer2020.baidu;

import java.util.Scanner;

public class T1 {
    // 100%
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int max = -1;
        System.out.println(
                n * (n - 1) - 1
        );
        sc.close();
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
