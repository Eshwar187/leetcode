import java.util.Arrays;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int[] dp = new int[1000003];
        int ans = 0;

        for (int num : nums) {
            int same = dp[num];
            int prev = dp[num - 1];

            dp[num] = Math.max(dp[num], prev + 1);
            dp[num + 1] = Math.max(dp[num + 1], same + 1);

            ans = Math.max(ans, Math.max(dp[num], dp[num + 1]));
        }

        return ans;
    }
}