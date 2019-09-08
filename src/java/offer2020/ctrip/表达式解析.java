package offer2020.ctrip;

import java.util.LinkedList;
import java.util.Scanner;

public class 表达式解析 {
}


class Main1 {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    // 100%
    static String resolve(String expr) {
        // ((ur)oi)
        // iour
        LinkedList<Character> stack = new LinkedList<>();
        char[] chars = expr.toCharArray();
        for (char aChar : chars) {
            if (aChar == '(') {
                stack.push('(');
            } else if (aChar == ')') {
                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                // no match!
                if (stack.isEmpty()) {
                    return "";
                }
                stack.pop();
                for (char ch : sb.toString().toCharArray()) {
                    stack.push(ch);
                }
            } else {
                stack.push(aChar);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            // no match
            if (stack.peekLast() == '(') {
                return "";
            }
            sb.append(stack.pollLast());
        }
        return sb.toString();
    }

    /******************************结束写代码******************************/


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String res;

        String _expr;
        try {
            _expr = in.nextLine();
        } catch (Exception e) {
            _expr = null;
        }

        res = resolve(_expr);
        System.out.println(res);
    }
}
