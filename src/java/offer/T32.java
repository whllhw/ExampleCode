package offer;

public class T32 {
    public static void main(String[] args) {
        T32 t = new T32();
        System.out.println(t.NumberOf1Between1AndN_Solution(21345));
    }

    /**
     * 从1到n整数中1出现的次数
     *
     * @param n n
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        return helper(Integer.valueOf(n).toString());
    }

    /**
     * 找规律得出的递归算法
     * 复杂度O(logn)
     *
     * @param num
     * @return
     */
    private int helper(String num) {
        if (num == null || num.length() == 0)
            return 0;
        int len = num.length();
        int first = num.charAt(0) - '0';
        if (len == 1 && first == 0)
            return 0;
        if (len == 1 && first > 0)
            return 1;
        // 设num 21345
        int numFirstDigit = 0;
        // 1出现在最高位的数量
        // （10000-19999）
        if (first > 1)
            numFirstDigit = powerBase10(len - 1);
        else if (first == 1)
            // 当num=12345时，计算方式不同(10000-12345)
            numFirstDigit = Integer.valueOf(num.substring(1)) + 1;
        // 1出现在除第1位的数量，
        // 分成first段（1346-11345 11346-21345）
        // 按排列组合，任选一位1，其他位有10种选法 4 × 10^3 × first
        // (1346-21345)除万位中
        int numOtherDigits = first * (len - 1) * powerBase10(len - 2);
        // 递归调用求得1345的结果 (1-1345)
        // 每次都会减小一位
        int numRecursive = helper(num.substring(1));
        return numFirstDigit + numOtherDigits + numRecursive;
    }

    private int powerBase10(int i) {
        int result = 1;
        for (int j = 0; j < i; j++) {
            result *= 10;
        }
        return result;
    }

    public int helper(int n) {
        // 根据规律推出公式
        // xi = (n/(i*10)) * i + if (k>i*2-1) i else (k < i) 0 else k - i + 1
        // 逐位去计数1的
        if (n <= 0)
            return 0;
        int count = 0;
        for (long i = 1; i <= n; i *= 10) {
            long diviver = i * 10;
            count += (n / diviver) * i
                    + Math.min(Math.max(n % diviver - i + 1, 0), i);
        }
        return count;
    }
}
