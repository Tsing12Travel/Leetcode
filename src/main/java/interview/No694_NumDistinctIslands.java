package interview;

import java.util.HashSet;
import java.util.Set;

// 2025.03.17 钉钉 RAG 组一面
/*
 * 给定一个非空 01 二维数组表示的网格，一个岛屿由四连通（上、下、左、右四个方向）的 1 组成，你可以认为网格的四周被海水包围
 * 请你计算这个网络中共有多少个形状不同的岛屿。两个岛屿被认为是相同的，当且仅当一个岛屿可以通过平移变换（不可以旋转、翻转）和另一个岛屿重合
 * */
public class No694_NumDistinctIslands {
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        Set<String> uniqueIslands = new HashSet<>();  // 存储不同形状的岛屿

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder shape = new StringBuilder();
                    dfs(grid, i, j, i, j, shape);
                    uniqueIslands.add(shape.toString());  // 记录岛屿形状
                }
            }
        }

        return uniqueIslands.size();
    }

    private void dfs(int[][] grid, int baseX, int baseY, int x, int y, StringBuilder shape) {
        int m = grid.length, n = grid[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != 1) return;

        grid[x][y] = 2;  // 标记为访问过
//        shape.append((x - baseX) + "," + (y - baseY) + ";");  // 记录相对位置
        shape.append((x - baseX)).append(",").append(y - baseY).append(";");  // 记录相对位置

        dfs(grid, baseX, baseY, x - 1, y, shape);
        dfs(grid, baseX, baseY, x + 1, y, shape);
        dfs(grid, baseX, baseY, x, y - 1, shape);
        dfs(grid, baseX, baseY, x, y + 1, shape);
    }


    public static void main(String[] args) {
        No694_NumDistinctIslands No694 = new No694_NumDistinctIslands();

        int[][] grid1 = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 1, 1},
                {0, 1, 0, 0, 0},
                {1, 1, 1, 0, 0}
        };
        System.out.println("不同岛屿的数量: " + No694.numDistinctIslands(grid1));

        int[][] grid2 = {
                {1, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 1, 1},
                {0, 1, 0, 0, 0},
                {1, 1, 1, 0, 0}
        };
        System.out.println("不同岛屿的数量: " + No694.numDistinctIslands(grid2));
    }
}
