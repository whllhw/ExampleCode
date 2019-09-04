package offer;

import java.util.Stack;

/**
 * 用两个栈实现队列
 * 思路：
 * 栈为后进先出，两个栈后进先出后进先出，即可逆转顺序
 * 让先进的先出了。
 */
public class T7 {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        stack1.push(node);
    }

    /**
     * pop时，当stack2为空时把弹出的内容全部放到stack2中
     */
    public int pop() {
        if (stack2.size() == 0) {
            while (stack1.size() > 0) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
