package top100;

public class No200_NumIslands {
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


    public static void main(String[] args) {
        // char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        No200_NumIslands No200 = new No200_NumIslands();
        System.out.println(No200.numIslands(grid));
    }
}
