package top100;

import java.util.ArrayList;
import java.util.List;

public class No199_RightSideView {



    // 深度优先
    private List<Integer> res = new ArrayList<>();

    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) return;
        if (res.size() <= depth) res.add(root.val);  // 右序遍历，因此只需要保存每层深度的第一个值

        dfs(root.right, depth + 1);
        dfs(root.left, depth + 1);
    }
}
