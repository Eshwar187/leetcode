
class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;

        int LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // Find next strictly greater element
        int[] next = new int[n];
        int[] st = new int[n];
        int top = -1;

        for (int i = n - 1; i >= 0; i--) {
            while (top >= 0 && nums[st[top]] <= nums[i]) {
                top--;
            }

            next[i] = (top == -1) ? n : st[top];
            st[++top] = i;
        }

        // Binary lifting tables
        int[][] up = new int[LOG][n + 1];
        long[][] cost = new long[LOG][n + 1];

        // Important: initialize sentinel
        for (int j = 0; j < LOG; j++) {
            up[j][n] = n;
        }

        for (int i = 0; i < n; i++) {
            up[0][i] = next[i];

            cost[0][i] =
                (long) nums[i] * (next[i] - i)
                - (prefix[next[i]] - prefix[i]);
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                int mid = up[j - 1][i];

                up[j][i] = up[j - 1][mid];

                cost[j][i] =
                    cost[j - 1][i] + cost[j - 1][mid];
            }
        }

        // Calculate cost of subarray [l, r]
        long ans = 0;
        int right = -1;

        for (int left = 0; left < n; left++) {

            while (right + 1 < n) {
                int r = right + 1;
                int idx = left;
                long operations = 0;

                for (int j = LOG - 1; j >= 0; j--) {
                    if (idx < n && up[j][idx] <= r + 1) {
                        operations += cost[j][idx];
                        idx = up[j][idx];
                    }
                }

                if (idx <= r) {
                    operations +=
                        (long) nums[idx] * (r - idx + 1)
                        - (prefix[r + 1] - prefix[idx]);
                }

                if (operations > k) {
                    break;
                }

                right++;
            }

            ans += right - left + 1;
        }

        return ans;
    }
}
