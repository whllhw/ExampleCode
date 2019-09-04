package 剑指offer;

public class T16 {
    /**
     * 反转链表
     *
     * @param head 旧链表head
     * @return 新链表head
     */
    public ListNode ReverseList(ListNode head) {
        // 思路：
        // 使用三个指针，分别指向pre cur after
        // 记录前驱、当前、后继节点。
        // +-+    +-+    +-+
        // + + -> + + -> + +
        // +-+    +-+    +-+
        // 更改next就会断开链表连接，故需要after
        // 要指向前驱节点，故需要pre
        // +-+    +-+    +-+
        // + + <- + +    + +
        // +-+    +-+    +-+
        ListNode pre, cur, after;
        pre = null;
        cur = head;
        while (cur != null) {
            after = cur.next;
            cur.next = pre;
            pre = cur;
            cur = after;
        }
        return pre;
    }
}
