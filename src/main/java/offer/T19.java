package offer;

public class T19 {
    /**
     * 二叉树的镜像
     *
     * @param root 根节点
     */
    public void Mirror(TreeNode root) {
        // 思路：
        // 前序遍历树，当节点有子节点就交换两个节点
        if (root == null)
            return;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        Mirror(root.left);
        Mirror(root.right);
    }
}
