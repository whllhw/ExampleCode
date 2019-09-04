package 剑指offer;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int val) {
        this.val = val;
    }


    @Override
    public String toString() {
        return String.format("TreeNode[%d,left:%s,right:%s]", val, left, right);
    }
}
