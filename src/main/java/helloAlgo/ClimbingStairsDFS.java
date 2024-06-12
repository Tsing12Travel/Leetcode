package helloAlgo;

public class ClimbingStairsDFS {
    public int dfs(int i) {
        if (i == 1 || i == 2) {
            return i;
        }

        int count = dfs(i - 1) + dfs(i - 2);
        return count;
    }


    public int climbStairsDFS(int n) {
        return dfs(n);
    }

    public static void main(String[] args) {
        ClimbingStairsDFS c = new ClimbingStairsDFS();
        System.out.println(c.climbStairsDFS(3));
    }
}
