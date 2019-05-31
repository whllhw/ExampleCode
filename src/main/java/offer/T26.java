package offer;

import java.util.HashMap;

public class T26 {
    /**
     * 复杂链表的复制
     *
     * @param pHead 待复制链表
     * @return 新链表
     */
    public RandomListNode Clone(RandomListNode pHead) {
        // 思路：
        // 先复制next，并把其用map保存起来
        // 再复制random，再使用map去获取
        if (pHead == null)
            return null;
        RandomListNode head = new RandomListNode(-1);
        RandomListNode p = pHead;
        RandomListNode q = head;
        // 保存新node->旧node的映射
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        while (p != null) {
            q.next = new RandomListNode(p.label);
            map.put(p, q.next);
            q = q.next;
            p = p.next;
        }
        p = pHead;
        q = head.next;
        while (p != null) {
            if (p.random != null) {
                q.random = map.get(p.random);
            }
            p = p.next;
            q = q.next;
        }
        return head.next;
    }

    class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }
}

