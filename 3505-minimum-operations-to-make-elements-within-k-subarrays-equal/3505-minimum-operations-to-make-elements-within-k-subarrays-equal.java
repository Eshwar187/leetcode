class Solution {
    TreeMap<Integer, Integer> low = new TreeMap<>();
    TreeMap<Integer, Integer> high = new TreeMap<>();
    long lowSum = 0, highSum = 0;
    int lowSize = 0, highSize = 0;

    void add(TreeMap<Integer, Integer> map, int v) {
        map.merge(v, 1, Integer::sum);
    }

    void remove(TreeMap<Integer, Integer> map, int v) {
        int c = map.get(v);
        if (c == 1) map.remove(v);
        else map.put(v, c - 1);
    }

    void addValue(int v) {
        if (low.isEmpty() || v <= low.lastKey()) {
            add(low, v);
            lowSum += v;
            lowSize++;
        } else {
            add(high, v);
            highSum += v;
            highSize++;
        }
        balance();
    }

    void removeValue(int v) {
        if (low.containsKey(v)) {
            remove(low, v);
            lowSum -= v;
            lowSize--;
        } else {
            remove(high, v);
            highSum -= v;
            highSize--;
        }
        balance();
    }

    void balance() {
        int target = (lowSize + highSize + 1) / 2;

        while (lowSize > target) {
            int v = low.lastKey();
            remove(low, v);
            lowSum -= v;
            lowSize--;

            add(high, v);
            highSum += v;
            highSize++;
        }

        while (lowSize < target) {
            int v = high.firstKey();
            remove(high, v);
            highSum -= v;
            highSize--;

            add(low, v);
            lowSum += v;
            lowSize++;
        }
    }

    long getCost() {
        long median = low.lastKey();
        return median * lowSize - lowSum
                + highSum - median * highSize;
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];

        low.clear();
        high.clear();
        lowSum = highSum = 0;
        lowSize = highSize = 0;

        for (int i = 0; i < x; i++) {
            addValue(nums[i]);
        }

        cost[0] = getCost();

        for (int i = x; i < n; i++) {
            removeValue(nums[i - x]);
            addValue(nums[i]);
            cost[i - x + 1] = getCost();
        }

        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[k + 1][n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(dp[j], INF);
        }

        for (int j = 1; j <= k; j++) {
            for (int i = n - x; i >= 0; i--) {
                dp[j][i] = dp[j][i + 1];

                if (dp[j - 1][i + x] != INF) {
                    dp[j][i] = Math.min(
                        dp[j][i],
                        cost[i] + dp[j - 1][i + x]
                    );
                }
            }
        }

        return dp[k][0];
    }
}