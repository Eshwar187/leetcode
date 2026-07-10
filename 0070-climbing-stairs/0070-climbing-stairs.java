class Solution {
    public int climbStairs1(int n,int[] dp) {
        if(n<=2) return n;
        if(dp[n]!=0) return dp[n];
        return dp[n]=climbStairs1(n-1,dp)+climbStairs1(n-2,dp);
    }
    public int climbStairs(int n) {
        int[] dp=new int[n+1];
        return climbStairs1(n,dp);
    }
}