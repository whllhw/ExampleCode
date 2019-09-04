package 剑指offer;

public class T13 {
    /**
     * 在O(1)时间删除链表节点
     *
     * @param head 头节点
     * @param del  待删除的节点
     */
    public static void delete(ListNode head, ListNode del) {
        // 思路：
        // 最常见的就是直接遍历到节点后删除，前节点.next = del.next
        // 需要O(n)
        // 这里有个巧妙的地方，利用单向性，直接把后节点的值覆盖到当前节点，再删除后一个节点
        // 但是当删除节点为末尾的节点时，仍然要遍历找到前驱
        // 平均时间复杂度：[(n-1)O(1) + O(n)]/n = O(1)
        if (del == null)
            return;

        if (del.next != null) {
            del.val = del.next.val;
            del.next = del.next.next;
        } else {
            for (ListNode p = head; p != null; p = p.next) {
                if (del == p.next) {
                    p.next = null;
                }
            }
        }

    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1,
                new ListNode(2, new ListNode(3, null)));
        delete(listNode, listNode);
        System.out.println(listNode);

        ListNode head = new ListNode(1,
                new ListNode(2, new ListNode(3, null)));
        delete(head, head.next.next);
        System.out.println(head);
    }

}
