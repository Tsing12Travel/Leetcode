package top100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class No207_CanFinish {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];  // 创建一个数组，用于存放每门课程的入度（即先修课程的数量）
        List<List<Integer>> adjacency = new ArrayList<>();  // 创建一个邻接表，用于存放每门课程的后续课程
        Queue<Integer> queue = new LinkedList<>();  // 创建一个队列，用于存放入度为 0 的课程（即没有先修课程，可以直接学习的课程）

        // 初始化邻接表
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());  // 为每个课程添加一个空的邻接表
        }

        // 获取每门课程的入度和邻接表
        for (int[] cp : prerequisites) {
            indegrees[cp[0]]++;  // cp[0] 的入度加一，想要学习 cp[0] 就得先完成 cp[1] 即 cp[0] <- cp[1]
            adjacency.get(cp[1]).add(cp[0]);  // cp[0] 是 cp[1] 的后续课程，所以将 cp[0] 加入 cp[1]
        }

        // 获取所有入度为 0 的课程
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {  // 如果一门课程的入度为 0，那么将其加入队列
                queue.add(i);
            }
        }

        // BFS 拓扑排序
        while (!queue.isEmpty()) {  // 当队列不为空，执行循环
            int pre = queue.poll();  // poll 一个课程，就是学习一个课程
            numCourses--;  // 学完一个课程，待学习的课程数减 1

            for (int cur : adjacency.get(pre)) {  // 学完该课程，需要把其邻接课程的入度减 1
                /*if (--indegrees[cur] == 0) {  // 如果一门后续课程的入度减为 0，那么将其加入队列
                    queue.add(cur);
                }*/
                indegrees[cur]--;
                // 邻接课程 cur 的入度更新后，需要判断是否为 0，若为 0，证明课程 cur 也可以学习了，需要加到可学课程队列中
                if (indegrees[cur] == 0) {
                    queue.add(cur);
                }
            }
        }
        // 到这一步，queue为空（即没有可学课程了），此时判断待学习课程数 numCourses 是否为 0，若为 0，全部课程学习结束
        return numCourses == 0;
    }


    public static void main(String[] args) {
        int[][] prerequisites = new int[][]{{1, 0}};
//        int[][] prerequisites = new int[][]{{1, 0}, {0, 1}};
        int numCourses = 2;
        No207_CanFinish no207_CanFinish = new No207_CanFinish();
        System.out.println(no207_CanFinish.canFinish(numCourses, prerequisites));
    }
}
