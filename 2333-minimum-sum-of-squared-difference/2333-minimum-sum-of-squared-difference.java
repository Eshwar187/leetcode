class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long k = (long) k1 + k2;
        int[] f = new int[100001];
        int max = 0;
        long total = 0;

        for (int i = 0; i < nums1.length; i++) {
            int d = Math.abs(nums1[i] - nums2[i]);
            f[d]++;
            max = Math.max(max, d);
            total += d;
        }

        if (total <= k) return 0;

        for (int d = max; d > 0 && k > 0; d--) {
            int c = f[d];
            if (c == 0) continue;

            long take = Math.min(k, c);
            f[d] -= take;
            f[d - 1] += take;
            k -= take;
        }

        long ans = 0;
        for (int d = 1; d < f.length; d++) {
            ans += (long) d * d * f[d];
        }

        return ans;
    }
}