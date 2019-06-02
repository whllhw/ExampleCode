package offer;

import java.util.Arrays;

public class T33 {
    public static void main(String[] args) {
        T33 t = new T33();
        System.out.println(t.PrintMinNumber(new int[]{
                3, 32, 321
        }));
    }

    /**
     * 把数组排成最小的数
     *
     * @param numbers nums
     */
    public String PrintMinNumber(int[] numbers) {
        return solve(numbers);
    }

    public String solve(int[] nums) {
        // 思路：
        // 1. 比较两个数 a b 拼接出来的值 ab ba
        // 2. 直接转为String进行比较即可
        // 3. 调用sort，并传入Comparator即可
        // 4. 使用StringBuilder输出即可
        Integer[] values = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            values[i] = nums[i];
        }
        Arrays.sort(values, (o1, o2) -> {
            String a = String.valueOf(o1);
            String b = String.valueOf(o2);
            String ab = a + b;
            String ba = b + a;
            int result = ab.compareTo(ba);
            return Integer.compare(result, 0);
        });
        StringBuilder sb = new StringBuilder();
        for (Integer value : values) {
            sb.append(value);
        }
        return sb.toString();
    }
}
