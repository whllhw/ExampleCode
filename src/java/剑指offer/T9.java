package 剑指offer;

public class T9 {
    /**
     * 斐波那契数列
     *
     * @param n n
     * @return f(n)
     */
    public int Fibonacci(int n) {
        // O(n) 解法
        // 直接即可递推
        int a = 0;
        int b = 1;

        for (int i = 0; i < n; i++) {
            int c = b;
            b = a + b;
            a = c;
        }
        return a;

    }
}
