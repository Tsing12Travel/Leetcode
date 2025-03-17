package leetCode;

import java.util.LinkedList;
import java.util.Queue;

public class No695_MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j);
                    res = Math.max(res, area);
                }
            }
        }

        return res;
    }

    private int dfs(int[][] grid, int r, int c) {
        if (!inArea(grid, r, c)) return 0;
        if (grid[r][c] != 1) return 0;

        grid[r][c] = 2;

        return 1 + dfs(grid, r - 1, c) + dfs(grid, r + 1, c) + dfs(grid, r, c - 1) + dfs(grid, r, c + 1);
    }

    private boolean inArea(int[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
    }


    // DFS
    public int maxAreaOfIsland2(int[][] grid) {
        /*
        * 解题思路
            遍历整个网格
            找到每个 1（代表岛屿），然后使用 DFS（或 BFS）计算其面积。
            计算面积时，标记已访问的格子（设为 0），防止重复计算。
            DFS 递归计算面积
            遇到 1 时，递归搜索 上下左右，并累计岛屿的面积。
            每访问一个 1，面积就 +1，直到所有相连的 1 变成 0。
            遍历整个网格，记录最大面积
            每次发现新的岛屿，就更新 maxArea 变量。
        * */
        int maxArea = 0;
        int m = grid.length, n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {  // 遇到陆地，计算岛屿面积
                    maxArea = Math.max(maxArea, dfs2(grid, i, j));
                }
            }
        }
        return maxArea;
    }

    private int dfs2(int[][] grid, int x, int y) {
        int m = grid.length, n = grid[0].length;
        if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == 0) {
            return 0;
        }

        grid[x][y] = 0; // 标记已访问
        int area = 1; // 当前格子计入面积

        // 递归搜索上下左右
        area += dfs(grid, x - 1, y); // 上
        area += dfs(grid, x + 1, y); // 下
        area += dfs(grid, x, y - 1); // 左
        area += dfs(grid, x, y + 1); // 右

        return area;
    }


    // 使用队列（Queue）实现 BFS 迭代方式，避免深度递归带来的栈溢出问题
    public int maxAreaOfIsland3(int[][] grid) {
        int maxArea = 0;
        int m = grid.length, n = grid[0].length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = 0;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    grid[i][j] = 0; // 标记已访问

                    while (!queue.isEmpty()) {
                        int[] cell = queue.poll();
                        int x = cell[0], y = cell[1];
                        area++;

                        for (int[] dir : directions) {
                            int newX = x + dir[0], newY = y + dir[1];
                            if (newX >= 0 && newY >= 0 && newX < m && newY < n && grid[newX][newY] == 1) {
                                queue.offer(new int[]{newX, newY});
                                grid[newX][newY] = 0; // 标记已访问
                            }
                        }
                    }
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }


    public static void main(String[] args) {
        No695_MaxAreaOfIsland No695 = new No695_MaxAreaOfIsland();
        int[][] grid1 = {
                {0, 0, 1, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 1, 0, 0},
                {1, 1, 0, 0, 0}
        };
        System.out.println("最大岛屿面积: " + No695.maxAreaOfIsland(grid1)); // 输出: 5

        int[][] grid2 = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0}
        };
        System.out.println("最大岛屿面积: " + No695.maxAreaOfIsland(grid2)); // 输出: 4
    }
}
