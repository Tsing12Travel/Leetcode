package top100;

import java.util.LinkedList;
import java.util.Queue;

public class No200_NumIslands {
    /*深度优先搜索 (DFS)
    思路：
    遍历网格中的每个位置。
    如果当前位置是陆地（值为 '1'），说明发现了一座岛屿，岛屿数量加 1。
    使用 DFS 将与当前陆地相连的所有陆地标记为已访问（比如将其改为 '0'）。
    继续遍历网格，找到下一个岛屿。*/
    public int numIslands(char[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    public void dfs(char[][] grid, int i, int j) {
        // 边界条件：超出网格范围或遇到水/已访问
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != '1') return;

        // 将当前位置标记为已访问
        grid[i][j] = '2';

        // 递归访问上下左右
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }


    public int numIslands2(char[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    dfs2(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs2(char[][] grid, int r, int c) {
        // base case
        if (!inArea(grid, r, c)) return;

        // 如果这个格子不是岛屿，直接返回
        if (grid[r][c] != '1') return;

        // 将格子标记为“已遍历过”
        grid[r][c] = '2';

        // 访问上下左右四个相邻结点
        dfs2(grid, r - 1, c);
        dfs2(grid, r + 1, c);
        dfs2(grid, r, c - 1);
        dfs2(grid, r, c + 1);
    }

    // 判断坐标 (r, c) 是否在网格中
    private boolean inArea(char[][] grid, int r, int c) {
        return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length;
    }


    /*广度优先搜索 (BFS)
    思路：
    遍历网格中的每个位置。
    如果当前位置是陆地（值为 '1'），说明发现了一座岛屿，岛屿数量加 1。
    使用 BFS 从当前位置开始，将所有相连的陆地标记为已访问（比如将其改为 '0'）。
    继续遍历网格，找到下一个岛屿。*/
    public int numIslands3(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int numIslands = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    numIslands++;
                    bfs(grid, i, j);
                }
            }
        }

        return numIslands;
    }

    private void bfs(char[][] grid, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        grid[i][j] = '0'; // 标记为已访问

        // 定义四个方向
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] direction : directions) {
                int newRow = current[0] + direction[0];
                int newCol = current[1] + direction[1];

                // 检查新位置是否在网格内且未访问
                if (newRow >= 0 && newRow < grid.length &&
                        newCol >= 0 && newCol < grid[0].length &&
                        grid[newRow][newCol] == '1') {
                    queue.offer(new int[]{newRow, newCol});
                    grid[newRow][newCol] = '0'; // 标记为已访问
                }
            }
        }
    }


    public static void main(String[] args) {
        // char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        No200_NumIslands No200 = new No200_NumIslands();
        System.out.println(No200.numIslands(grid));
    }
}
