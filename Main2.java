import java.util.*;
import java.io.*;

public class Main2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Main.in"));
        int N = sc.nextInt();
        while (N-- > 0) {
            int len = sc.nextInt();
            int[] a = new int[len];
            for (int i = 0; i < len; i++) {
                a[i] = sc.nextInt();
            }
            solve(a);
        }
    }

    public static void solve(int[] a) {
        long ans = 0L;
        int pos = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i];
                pos = i;
            }
        }
        int[] r = new int[a.length];
        r[pos] = 1;
        int i = pos + 1 + 2*a.length;
        for (int j = 0; j < a.length; j++) {
            int ia = i % a.length;
            int ib = (i - 1) % a.length;
            if (a[ia] > a[ib]) {
                r[ia] = r[ib] + 1;
            }
            i++;
        }
        i = pos - 1 + 2*a.length;
        for (int j = 0; j < a.length; j++) {
            int ia = i % a.length;
            int ib = (i + 1) % a.length;
            if (a[ia] > a[ib]) {
                r[ia] = r[ib] + 1;
            }else if(a[ia] == a[ib] && r[ia] == 0) {
                r[ia] = r[ib];
            }
            i--;
        }
        for (int x : r) {
            if(x == 0){
                ans += 1;
            }else{
                ans += x;
            }
        }
        System.out.println(ans);
    }
}