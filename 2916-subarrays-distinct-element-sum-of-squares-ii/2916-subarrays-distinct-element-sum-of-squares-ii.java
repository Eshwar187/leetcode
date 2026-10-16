class Solution {
    public int sumCounts(int[] nums) {
        int MOD = 1_000_000_007;
        int n = nums.length;
        long ans = 0;

        long[] sum = new long[4 * n];
        long[] sq = new long[4 * n];
        long[] lazy = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int l = last[x] + 1;
            int r = i;

            update(1, 0, n - 1, l, r, sum, sq, lazy, MOD);
            last[x] = i;

            ans = (ans + sq[1]) % MOD;
        }

        return (int) ans;
    }

    void update(int node, int start, int end, int l, int r,
                long[] sum, long[] sq, long[] lazy, int MOD) {
        if (l > end || r < start) return;

        if (l <= start && end <= r) {
            long len = end - start + 1;
            sq[node] = (sq[node] + 2 * sum[node] + len) % MOD;
            sum[node] = (sum[node] + len) % MOD;
            lazy[node]++;
            return;
        }

        int mid = (start + end) / 2;
        push(node, start, end, sum, sq, lazy, MOD);

        update(node * 2, start, mid, l, r, sum, sq, lazy, MOD);
        update(node * 2 + 1, mid + 1, end, l, r, sum, sq, lazy, MOD);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }

    void push(int node, int start, int end,
              long[] sum, long[] sq, long[] lazy, int MOD) {
        if (lazy[node] == 0 || start == end) return;

        int mid = (start + end) / 2;
        int left = node * 2, right = node * 2 + 1;
        long v = lazy[node];

        apply(left, mid - start + 1, v, sum, sq, lazy, MOD);
        apply(right, end - mid, v, sum, sq, lazy, MOD);

        lazy[node] = 0;
    }

    void apply(int node, long len, long v,
               long[] sum, long[] sq, long[] lazy, int MOD) {
        sq[node] = (sq[node] + 2 * v % MOD * sum[node] % MOD
                    + v * v % MOD * len % MOD) % MOD;
        sum[node] = (sum[node] + v * len) % MOD;
        lazy[node] = (lazy[node] + v) % MOD;
    }
}
