package offer;

import java.util.HashSet;

public class T37 {
    /**
     * 两个链表的第一个公共结点
     * 空间复杂度O(n)
     * 时间复制度O(n+m)
     */
    public ListNode solve(ListNode pHead1, ListNode pHead2) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode p = pHead1;
        while (p != null) {
            set.add(p);
            p = p.next;
        }
        p = pHead2;
        while (p != null) {
            if (set.contains(p)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    /**
     * 两个链表的第一个公共结点
     * 不使用O(n)的空间
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        // 思路：
        // 拥有公共结点的必是Y形的链表
        // 在经过一定长度后，两个链表指针都是相等的
        // 1. 遍历一遍，确定链表长度
        // 2. 长链表的指针先走链表长度之差的长度（双指针赛跑）
        // 3. 两个指针同时遍历，当两个指针相等时即为第一个公共节点

        int lenA = 0, lenB = 0;
        ListNode p = pHead1;
        while (p != null) {
            lenA++;
            p = p.next;
        }
        ListNode q = pHead2;
        while (q != null) {
            lenB++;
            q = q.next;
        }
        // 获取较长的一条
        if (lenA > lenB) {
            p = pHead1;
            q = pHead2;
        } else {
            p = pHead2;
            q = pHead1;
        }
        // 先走lenA-lenB步
        int counter = Math.abs(lenA - lenB);
        while (counter > 0) {
            counter--;
            p = p.next;
        }
        while (p != q && p != null && q != null) {
            p = p.next;
            q = q.next;
        }
        return p;
    }
}
