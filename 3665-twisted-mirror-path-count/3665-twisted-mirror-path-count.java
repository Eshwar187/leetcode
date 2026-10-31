class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) continue;

                for (int d = 0; d < 2; d++) {
                    int x = i;
                    int y = j;
                    int dir = d;

                    while (true) {
                        if (dir == 0) y++;
                        else x++;

                        if (x >= m || y >= n) break;

                        if (grid[x][y] == 0) {
                            dp[x][y] = (dp[x][y] + dp[i][j]) % MOD;
                            break;
                        }

                        dir ^= 1;
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}