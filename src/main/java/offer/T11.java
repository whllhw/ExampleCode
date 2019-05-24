package offer;

public class T11 {
    public static void main(String[] args) {
        T11 t = new T11();
        t.Power(2, 10);
        t.Power(-1, 10);
        t.Power(2, -2);
        t.Power(-2, -2);
        t.Power(0, 1);
        t.Power(1, 0);
    }

    /**
     * 整数次方
     *
     * @param base     底数
     * @param exponent 指数
     * @return 结果
     */
    public double Power(double base, int exponent) {
        // 主要考察程序的功能测试、边界测试、负面测试
        // 出错情况如下：
        // 1. 0的多少次方都是零
        // 2. 0的0次方，出错
        // 3. 0的负次方，出错

        // 解决：底数为0，且指数小于等于0，抛出错误
        if (Double.compare(0d, base) == 0) {
            return 0d;
        }
        if (exponent < 0) {
            exponent = -exponent;
            base = 1 / base;
        }
        double ans = 1;
        for (int i = 0; i < exponent; i++) {
            ans *= base;
        }
        return ans;
    }

    /**
     * 更高效的解法
     *
     * @param base     底数
     * @param exponent 指数
     * @return
     */
    public double PowerLogn(double base, int exponent) {
        // 递归的解法，
        // 由公式：An = A(n/2)*A(n/2)   n为偶数
        // An = A(n/2)*A(n/2)*A  n为奇数
        // 运用递归，复杂度降到O(logn)
        if (base == 0d) {
            return 0d;
        }
        if (exponent < 0) {
            base = 1 / base;
            exponent = -exponent;
        }
        return Power0(base, exponent);
    }

    /**
     * @param base     正底数
     * @param exponent 正指数
     * @return
     */
    private double Power0(double base, int exponent) {
        if (base == 0d) {
            return 0;
        }
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }
        double ans;
        ans = Power0(base, (exponent >> 1));
        ans *= ans;
        if ((exponent & 1) == 1) {
            ans *= base;
        }
        return ans;
    }
}
