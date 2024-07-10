package top100;

import java.util.LinkedList;
import java.util.Queue;

// 题目要求：返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数 -> 求腐烂橘子到所有新鲜橘子的最短路径 -> BFS

/* 思路：
一开始，我们找出所有腐烂的橘子，将它们放入队列，作为第 0 层的结点。
然后进行 BFS 遍历，每个结点的相邻结点可能是上、下、左、右四个方向的结点，注意判断结点位于网格边界的特殊情况。
由于可能存在无法被污染的橘子，我们需要记录新鲜橘子的数量。
在 BFS 中，每遍历到一个橘子（污染了一个橘子），就将新鲜橘子的数量减一。如果 BFS 结束后这个数量仍未减为零，说明存在无法被污染的橘子。*/

public class No994_OrangesRotting {
    public int orangesRotting(int[][] grid) {
        int fresh = 0;  // fresh 表示新鲜橘子的数量
        int row = grid.length;
        int col = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) fresh++;
                if (grid[i][j] == 2) queue.add(new int[]{i, j});
            }
        }

        int round = 0;  // round 表示腐烂的轮数，或者分钟数
        while (fresh > 0 && !queue.isEmpty()) {
            round++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int x = curr[0];
                int y = curr[1];

                if (x - 1 >= 0 && grid[x - 1][y] == 1) {
                    fresh--;
                    grid[x - 1][y] = 2;
                    queue.add(new int[]{x - 1, y});
                }

                if (x + 1 < grid.length && grid[x + 1][y] == 1) {
                    fresh--;
                    grid[x + 1][y] = 2;
                    queue.add(new int[]{x + 1, y});
                }

                if (y - 1 >= 0 && grid[x][y - 1] == 1) {
                    fresh--;
                    grid[x][y - 1] = 2;
                    queue.add(new int[]{x, y - 1});
                }

                if (y + 1 < grid[0].length && grid[x][y + 1] == 1) {
                    fresh--;
                    grid[x][y + 1] = 2;
                    queue.add(new int[]{x, y + 1});
                }
            }
        }

        if (fresh > 0) {
            return -1;
        } else {
            return round;
        }
    }
}
