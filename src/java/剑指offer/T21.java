package 剑指offer;

import java.util.Stack;

/**
 * 实现min栈
 * 思路：
 * 直接设置一个min变量保存当前最小值即可，
 * 当遇到大的数保存 node - min
 * 当遇到更小的数则更新min，压入负值
 * 在出栈时则判断是否小于0，小于0则更新min=min-x！
 * <p>
 * 思路2：
 * 使用两个栈，一个正常，一个同步压入最小数值或者栈顶的数值
 */
public class T21 {
    private final Stack<Integer> stack = new Stack<>();
    private int min = 0;

    public void push(int node) {
        if (stack.isEmpty()) {
            min = node;
        }
        if (node < min) {
            int temp = node - min;
            min = node;
            stack.push(temp);
        } else {
            stack.push(node - min);
        }
    }

    public void pop() {
        int x = stack.pop();
        if (x > 0) {
            return;
        }
        min = min - x;
    }

    public int top() {
        int x = stack.peek();
        if (x > 0) {
            return x + min;
        } else {
            return min;
        }
    }

    public int min() {
        return min;
    }
}
