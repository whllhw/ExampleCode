package 剑指offer;

public class T47 {
    /**
     * 不使用四则运算符求两个数之和
     */
    public int Add(int num1, int num2) {
        int sum, carry;
        do {
            // 不考虑进位的情况
            sum = num1 ^ num2;
            // 计算出进位
            // 这里利用相同为1的与运算，并左移动一位
            carry = (num1 & num2) << 1;
            num1 = sum;
            num2 = carry;
            // 当进位不为0时，仍然要相加两个数，
            // 就是一个递归的过程
            // 相当于调用Add(sum,carry)
            // Add(num1^num2,(num1&num2)<<1)
        } while (num2 != 0);
        return num1;
    }

}
