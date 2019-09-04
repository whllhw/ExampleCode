package 剑指offer;


import java.util.LinkedList;

public class T22 {
    /**
     * 栈的压入、弹出序列
     *
     * @param pushA 压入
     * @param popA  弹出
     * @return 是否合法
     */
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        // 思路：
        // 直观思路就是模拟一个栈的行为，
        // 当栈顶元素不是目标值的时候，压入元素直到栈顶元素是目标值，
        // 弹出元素，移动到下一个目标值。

        // 当合法时，两个指针会走到末尾
        // 当不合法时，是p q走不到末尾
        if (pushA == null || popA == null)
            return false;
        LinkedList<Integer> stack = new LinkedList<>();
        int p = 0, q = 0;
        int len = pushA.length;
        while (p < len || q < len) {
            while (p < len && pushA[p] != popA[q]) {
                stack.push(pushA[p]);
                p++;
            }
            if (p < len) {
                p++;
            } else {
                if (stack.pop() != popA[q]) {
                    break;
                }
            }
            q++;
        }
        return p == len && q == len;
    }
}
