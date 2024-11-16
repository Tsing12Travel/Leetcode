package leetCode;

public class No463_IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return dfs(grid, i, j);
                }
            }
        }

        return 0;
    }

    // 岛屿的周长就是岛屿方格和非岛屿方格相邻的边的数量
    // 将这个“相邻关系”对应到 DFS 遍历中，就是：每当在 DFS 遍历中，从一个岛屿方格走向一个非岛屿方格，就将周长加 1
    private int dfs(int[][] grid, int r, int c) {
        // 从一个岛屿方格走向网格边界，周长加 1
        if (!(0 <= r && r < grid.length && 0 <= c && c < grid[0].length)) return 1;
        // 从一个岛屿方格走向水域方格，周长加 1
        if (grid[r][c] == 0) return 1;

        // 已遍历，不需要重复计算
        if (grid[r][c] != 1) return 0;
        // 标记已遍历，避免「兜圈子」
        grid[r][c] = 2;

        return dfs(grid, r - 1, c) + dfs(grid, r + 1, c) + dfs(grid, r, c - 1) + dfs(grid, r, c + 1);
    }


    // 暴力解法：求边长，判断每个陆地的四周是什么，从上下左右四个方向找，遇到非陆地就可以+1
    public int islandPerimeter2(int[][] grid) {
        // 边长可以是 4、3、2、1、0
        // 判断每个陆地的四周，如果出现水，就可以+1
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    if (j - 1 < 0 || grid[i][j - 1] == 0) count++;
                    if (j + 1 >= grid[0].length || (j + 1 < grid[0].length && grid[i][j + 1] == 0)) count++;
                    if (i - 1 < 0 || grid[i - 1][j] == 0) count++;
                    if (i + 1 >= grid.length || (i + 1 < grid.length && grid[i + 1][j] == 0)) count++;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
//        int[][] grid = {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
//        int[][] grid = {{1}};
        int[][] grid = {{1, 0}};
        No463_IslandPerimeter test = new No463_IslandPerimeter();
        System.out.println(test.islandPerimeter(grid));
    }
}
