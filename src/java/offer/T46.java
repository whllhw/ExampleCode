package offer;


public class T46 {
    /**
     * 求1+2+3...+n
     * 要求不使用乘除 if while for 等条件判断语句
     */
    public int Sum_Solution(int n) {
        // 使用短路求值特性
        int sum = n;
        boolean ans = (n > 0) && ((sum += Sum_Solution(n - 1)) > 0);
        return sum;
    }

    public int solve(int n) {
        // 利用异常退出递归
        try {
            int i = 1 % n;
            return n + solve(n - 1);
        } catch (Exception e) {
            return 0;
        }
    }

    public int solve0(int n) {
        // 使用对数化相乘为相加
        int a = n;
        int b = n + 1;
        return (int) Math.pow(10, Math.log10(a) + Math.log10(b)) >> 1;
    }
}
