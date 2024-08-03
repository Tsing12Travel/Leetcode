package top100;

public class No236_LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 只要当前根节点是 p 和 q 中的任意一个，就返回（因为不能比这个更深了，再深 p 和 q 中的一个就没了）
        if (root == null || root == p || root == q) return root;

        // 根节点不是 p 和 q 中的任意一个，那么就继续分别往左子树和右子树找 p 和 q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 左右子树都找到 p 和 q 了，那就说明 p 和 q 分别在左右两个子树上，所以此时的最近公共祖先就是 root
        if (left != null && right != null) return root;

        // 左子树没有 p 也没有 q，就返回右子树的结果；右子树没有 p 也没有 q 就返回左子树的结果
        return left != null ? left : right;

        /* 以下为原写法
        // 左子树没有 p 也没有 q，就返回右子树的结果
        if (left == null) {
            return right;
        }
        // 右子树没有 p 也没有 q 就返回左子树的结果
        if (right == null) {
            return left;
        }
        // 左右子树都找到 p 和 q 了，那就说明 p 和 q 分别在左右两个子树上，所以此时的最近公共祖先就是 root
        return root;*/
    }


    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if(root == null) return null;
        if(root.val == p.val || root.val == q.val){
            return root; // could be x or y
        }
        // traverse down to search
        TreeNode leftSearchResult = lowestCommonAncestor(root.left, p, q);
        TreeNode rightSearchResult = lowestCommonAncestor(root.right, p, q);

        // only found on node on the current side
        if(leftSearchResult == null) return rightSearchResult;
        if(rightSearchResult == null) return leftSearchResult;

        return root;
    }
}
