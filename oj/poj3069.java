import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * 贪心算法，每次只覆盖最右的一个点
 * 模拟覆盖的过程
 * {@link} https://vjudge.net/problem/POJ-3069
 */
public class poj3069 {
    private static int solve(int[] lines, int R) {
        Arrays.sort(lines);

        int counter = 0;
        int pos = 0;
        int i = 0;
        while (pos < lines.length) {
            int last = lines[pos];
            // 往前走，直到最右，得到放置点
            while (pos < lines.length && lines[pos] - last <= R) {
                pos++;
            }
            pos--;
            last = lines[pos];
            // 继续往前走，得到最右的点
            while (pos < lines.length && lines[pos] - last <= R) {
                pos++;
            }
            // 完成一次放置
            counter++;
        }
        return counter;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("poj3069.in"));
        while (in.hasNext()) {
            int r = in.nextInt();
            int len = in.nextInt();
            if (r == -1 || len == -1) {
                break;
            }
            int[] lines = new int[len];
            while (len > 0) {
                lines[--len] = in.nextInt();
            }
            System.out.println(solve(lines, r));
        }
        in.close();
    }
}