package 剑指offer;

public class T18 {
    /**
     * 树的子结构
     *
     * @param root1 A
     * @param root2 B
     * @return B是否为A的子树
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        // 思路：
        // 先判断根节点是否相同，相同则调用helper进入进行判断
        // 不相同，则需要遍历整个树
        // 1. 注意代码的鲁棒性，对null值的处理
        // 2. 使用result进行剪枝，避免找到了还继续找
        boolean result = false;
        if (root1 != null && root2 != null) {
            if (root1.val == root2.val) {
                result = helper(root1, root2);
            }
            if (!result)
                result = HasSubtree(root1.left, root2);
            if (!result)
                result = HasSubtree(root1.right, root2);
        }
        return result;
    }

    private boolean helper(TreeNode root1, TreeNode root2) {
        if (root2 == null)
            return true;
        if (root1 == null)
            return false;
        if (root1.val != root2.val)
            return false;
        return helper(root1.left, root2.left) && helper(root1.right, root2.right);
    }
}
