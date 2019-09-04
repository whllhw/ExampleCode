package 剑指offer;

import java.util.LinkedList;

public class T5 {
    public static void main(String[] args) {
        T5 t = new T5();
        t.test();
    }

    /**
     * 从尾到头打印链表
     *
     * @param root
     */
    public void printReversLinkedList(LinkNode root) {

//        if (root.next != null)
//            printReversLinkedList(root.next);
//        System.out.println(root.val);
        LinkedList<LinkNode> stack = new LinkedList<>();
        LinkNode p = root;
        while (p != null) {
            stack.push(p);
            p = p.next;
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop().val);
        }
    }


    public void test() {
        LinkNode root = new LinkNode(1,
                new LinkNode(2, new LinkNode(3,
                        new LinkNode(4, null))));
        printReversLinkedList(root);
    }

    class LinkNode {

        LinkNode next;
        int val;

        public LinkNode(int val, LinkNode next) {
            this.next = next;
            this.val = val;
        }
    }
}
