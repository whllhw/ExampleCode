package offer;

public class T39 {
    /**
     * 二叉树的深度
     */
    public int TreeDepth(TreeNode root) {
        // 二叉树的深度即为左子树、右子树中最大的深度+1
        if (root == null) {
            return 0;
        }
        return Math.max(TreeDepth(root.left), TreeDepth(root.right)) + 1;
    }

    /**
     * 判断平衡二叉树
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        return solve(root) != -1;
    }

    public int solve(TreeNode root) {
        // 当树是平衡二叉树时返回子树高度
        // 否则返回-1 进行了剪枝，不会重复访问节点

        if (root == null) {
            return 0;
        }
        int left = solve(root.left);
        if (left == -1) {
            return -1;
        }
        int right = solve(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);
    }
}
