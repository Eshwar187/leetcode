class Solution {
    public boolean canAliceWin(int n) {
        return solve(n, 10, new Boolean[n + 1][11]);
    }

    private boolean solve(int n, int stones, Boolean[][] dp) {
        if (stones == 0 || n < stones) {
            return false;
        }

        if (dp[n][stones] != null) {
            return dp[n][stones];
        }

        return dp[n][stones] = !solve(n - stones, stones - 1, dp);
    }
}