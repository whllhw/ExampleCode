package offer2020.ctrip;

import java.util.Scanner;

public class 链表排序 {

}


class Main {

    /*请完成下面这个函数，实现题目要求的功能
     ******************************开始写代码******************************/
    static ListNode partition(ListNode head, int m) {
        ListNode left = new ListNode(0);
        ListNode right = new ListNode(0);
        ListNode curRight = right;
        ListNode curLeft = left;
        ListNode node = head;
        while (node != null) {
            if (node.val <= m) {
                curLeft.next = node;
                curLeft = node;
            } else {
                curRight.next = node;
                curRight = node;
            }
            node = node.next;
        }
        curLeft.next = right.next;
        return left.next;
    }

    /******************************结束写代码******************************/


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ListNode head = null;
        ListNode node = null;
        int m = in.nextInt();
        while (in.hasNextInt()) {
            int v = in.nextInt();
            if (head == null) {
                node = new ListNode(v);
                head = node;
            } else {
                node.next = new ListNode(v);
                node = node.next;
            }
        }
        head = partition(head, m);
        if (head != null) {
            System.out.print(head.val);
            head = head.next;
            while (head != null) {
                System.out.print(",");
                System.out.print(head.val);
                head = head.next;
            }
        }
        System.out.println();
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
