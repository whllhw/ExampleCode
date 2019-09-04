package 剑指offer;

import java.util.ArrayList;
import java.util.LinkedList;

public class T23 {
    /**
     * 从上往下打印二叉树
     *
     * @param root 根节点
     * @return ans
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        // 思路：即层次遍历，用队列保存后续节点即可。

        ArrayList<Integer> ans = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        queue.offer(root);
        while (queue.size() != 0) {
            TreeNode p = queue.poll();
            ans.add(p.val);
            if (p.left != null)
                queue.offer(p.left);
            if (p.right != null)
                queue.offer(p.right);
        }
        return ans;
    }
}
