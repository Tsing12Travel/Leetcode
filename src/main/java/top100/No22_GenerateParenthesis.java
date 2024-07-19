package top100;

import java.util.ArrayList;
import java.util.List;

public class No22_GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        dfs(n, n, path, res);
        return res;
    }

    private void dfs(int left, int right, StringBuilder path, List<String> res) {
        if (left == 0 && right == 0) {  // 左右都到头了，说明有效括号已经构建完成
            res.add(path.toString());
            return;
        }

        // 剪枝(把不符合要求的剪去，如右括号在左括号左边的各种组合，可以注释掉这行对比输出结果)
        if (left > right) return;

        if (left > 0) {
            path.append("(");
            dfs(left - 1, right, path, res);
            path.deleteCharAt(path.length() - 1);
        }

        if (right > 0) {
            path.append(")");
            dfs(left, right - 1, path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }


    public static void main(String[] args) {
        No22_GenerateParenthesis sol = new No22_GenerateParenthesis();
        System.out.println(sol.generateParenthesis(3));
    }
}
