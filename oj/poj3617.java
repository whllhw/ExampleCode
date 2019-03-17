import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * 贪心算法，构造出字典序最小的字符串 字典序比较
 * 遇到相同大小的字符则比较下一个字符
 * {@link} https://vjudge.net/problem/POJ-3617
 */
public class poj3617 {
    public static void main(String[] args) throws FileNotFoundException {
        // Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("poj3617.in"));
        int length = in.nextInt();
        StringBuffer S = new StringBuffer();
        while (length-- > 0) {
            S.append(in.next());
        }
        // 不断地取S中较小的字符放到T末尾
        // 1. 相同时则要比较下一个字符大小
        // 2. 下一个也可能相同，继续比较
        int left = 0, right = S.length() - 1;
        int counter = 1;
        while (left <= right) {
            boolean flag = false;
            for (int i = 0; left + i <= right; i++) {
                if (S.charAt(left + i) > S.charAt(right - i)) {
                    flag = true;
                    break;
                } else if (S.charAt(left + i) < S.charAt(right - i)) {
                    flag = false;
                    break;
                }
                // 相等时，则继续比较下一个
            }
            if (flag)
                System.out.print(S.charAt(right--));
            else
                System.out.print(S.charAt(left++));
            if(counter++ % 80 == 0){
                System.out.println();
            }
        }
        in.close();
    }
}