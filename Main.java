import java.util.*;
import java.io.*;
import edu.princeton.cs.algs4.*;
import org.junit.Test;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Main.in"));
    }

    public static void testQuickSort() {
        for (int i = 0; i < 100000; i++) {
            int[] ans = randomList(i);
            Arrays.sort(ans);
            String system = Arrays.toString(ans);
            String self = Arrays.toString(quickSort(ans, ans.length));
            if (!system.equals(self)) {
                System.out.println(self);
                System.out.println(system);
                return;
            }
        }
    }

    public static int[] randomList(int len) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * 100000);
        }
        return ans;
    }

    // 使用额外空间的快排
    public static int[] quickSort(int[] num, int len) {
        if (num == null || len <= 1) {
            return num;
        }
        // 最后一个数做为划分的基准
        int pivot = num[len - 1];
        int counter = 1;
        int[] less = new int[len];
        int[] greater = new int[len];
        int i = 0;
        int j = 0;
        // 把比基准小的数放到less
        // 否则把数放到greater中
        // 注意基准数不再参与后续的排序
        // 把出现相同的数量记录下来，合并时再放到一起
        for (int pos = 0; pos < len - 1; pos++) {
            if (num[pos] < pivot)
                less[i++] = num[pos];
            else if (num[pos] > pivot)
                greater[j++] = num[pos];
            else
                counter++;
        }
        int[] a = quickSort(less, i);
        int[] b = quickSort(greater, j);
        int[] merge = new int[i + j + counter];
        System.arraycopy(a, 0, merge, 0, i);
        for (int x = 0; x < counter; x++)
            merge[i + x] = pivot;
        System.arraycopy(b, 0, merge, i + counter, j);
        // System.out.println(Arrays.toString(merge));
        return merge;
    }

    public void testKMP() {
        String s1 = "9abcabaabc";
        String s2 = "abcaba";
        System.out.println(KMP(s1.toCharArray(), 0, s2.toCharArray()));

        s1 = "";
        s2 = "adwa";
        System.out.println(KMP(s1.toCharArray(), 0, s2.toCharArray()));

        s1 = "qwer";
        s2 = "";
        System.out.println(KMP(s1.toCharArray(), 0, s2.toCharArray()));

        s1 = "12345";
        s2 = "321";
        System.out.println(KMP(s1.toCharArray(), 0, s2.toCharArray()));

    }

    private static int[] getNext(char[] T) {
        int[] next = new int[T.length + 1];
        int i = 0;
        int j = -1;
        next[0] = -1;
        while (i < T.length) {
            if (j == -1 || T[i] == T[j]) {
                i++;
                j++;
                // j 等于 -1 时，表示返回了开头，自然是next[i] = 0（i 处发生不匹配时，返回第一字符位置）
                // 否则表示第 i 处与第 j 处匹配
                // 即若在下一处（i+1）发生不匹配，返回的地方是下一个字符（j+1）
                next[i] = j;
            } else {
                // 在 j 处发生不匹配，利用前次算出的 next[j] 返回该位置
                j = next[j];
            }
        }
        return next;
    }

    public static int KMP(char[] S, int pos, char[] T) {
        int i = pos;
        int j = 1;
        int[] next = getNext(T);
        System.out.println(Arrays.toString(next));
        while (i < S.length && j < T.length) {
            if (j == -1 || S[i] == T[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == T.length) {
            return i - j;
        }
        return -1;
    }

    public static long 斐波那契数列(int n) {
        long a = 0;
        long b = 1;
        long c = 0;
        for (int i = 0; i < n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        double t = 1.0 / Math.sqrt(5) * Math.pow((0.5 + Math.sqrt(5) / 2), n);
        if ((long) Math.round(t) == a)
            System.out.println(t);
        return a;
    }

    public static long 汉诺塔(int n, int from, int to, int temp) {
        if (n == 0) {
            return 0L;
        }
        long a = 汉诺塔(n - 1, from, temp, to);
        System.out.println(String.format("%d->%d", from, to));
        long c = 汉诺塔(n - 1, temp, to, from);
        return a + 1 + c;
    }

    public static void 中缀表达式转化后缀表达式() {
        Map<Character, Integer> map = new HashMap<>();
        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 2);
        map.put('/', 2);
        map.put('(', 0);
        String str = "9+(3-1)*3+10/2";
        StringBuilder sb = new StringBuilder();
        LinkedList<Character> list = new LinkedList<>();
        char[] ch = str.toCharArray();
        for (char i : ch) {
            if (Character.isDigit(i)) {
                sb.append(i);
                continue;
            }
            if (i == '(') {
                list.push('(');
                continue;
            }
            if (i == ')') {
                while (list.peek() != '(') {
                    sb.append(list.pop());
                }
                list.pop();
                continue;
            }
            // 栈顶元素优先级大于当前元素则全部出栈
            if (!list.isEmpty() && map.get(list.peek()) > map.get(i)) {
                while (!list.isEmpty())
                    sb.append(list.pop());
            }
            list.push(i);
        }
        while (!list.isEmpty()) {
            sb.append(list.pop());
        }
        System.out.println(sb.toString());
    }

    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid])
                hi = mid - 1;
            else if (key > a[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }
}