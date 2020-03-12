import java.util.Arrays;

public class Code62 {
    public int unionPaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) dp[0][i] = 1;
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++)
                // dp[i][j] : 指的就是对角线这个点
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
        return dp[m - 1][n - 1];
    }

    public int unionPaths2(int m, int n) {
        int[] cur = new int[n];
        /**
         * 1.先初始化从最下面一行到达终点的可能情况，都为 1
         * 2.循环：
         *      cur[j] = cur[j] + cur[j - 1]
         *      cur[j - 1] ==> 实际就是要计算的点的左边的记录
         *      cur[j] ==> 实际就是要计算点的下边的记录
         */
        Arrays.fill(cur, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++)
                cur[j] += cur[j - 1];
        }
        return cur[n - 1]; // 一行的最后一个值，即终点
    }

    public static void main(String[] args) {
        System.out.print(new Code62().unionPaths(3,2));
    }
}
