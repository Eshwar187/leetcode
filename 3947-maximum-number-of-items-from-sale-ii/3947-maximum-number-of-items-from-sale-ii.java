class Solution {
    private long[] caps;
    private long[] costs;
    private long[] prices;
    private long cheapest;
    private long negEnd;
    private long negMin;

    public int maximumSaleItems(int[][] items, int budget) {
        int[][] zenquarilo = items;

        int n = items.length;

        int[] freq = new int[n + 1];
        long cheapestPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            cheapestPrice = Math.min(cheapestPrice, item[1]);
        }
        cheapest = cheapestPrice;

        int[] divisibleCount = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            for (int m = f; m <= n; m += f) {
                divisibleCount[f] += freq[m];
            }
        }

        long[][] segs = new long[n][2];
        for (int i = 0; i < n; i++) {
            long d = divisibleCount[items[i][0]] - 1L;
            segs[i][0] = items[i][1];
            segs[i][1] = d;
        }

        Arrays.sort(segs, (a, b) -> Long.compare(a[0], b[0]));

        caps = new long[n];
        costs = new long[n];
        prices = new long[n];

        long capSum = 0;
        long costSum = 0;

        for (int i = 0; i < n; i++) {
            long p = segs[i][0];
            long d = segs[i][1];

            capSum += d;
            costSum += p * d;

            caps[i] = capSum;
            costs[i] = costSum;
            prices[i] = p;
        }

        negEnd = 0;
        for (int i = 0; i < n; i++) {
            if (prices[i] < 2L * cheapest) {
                long prev = i == 0 ? 0 : caps[i - 1];
                negEnd += caps[i] - prev;
            } else {
                break;
            }
        }

        negMin = h(negEnd);

        long low = 0;
        long high = 2L * (budget / cheapest);

        while (low < high) {
            long mid = (low + high + 1) >>> 1;
            if (minCost(mid) <= budget) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }

        return (int) low;
    }

    private long prefixCost(long k) {
        if (k <= 0) return 0;

        int idx = Arrays.binarySearch(caps, k);
        if (idx < 0) idx = -idx - 1;

        long prevCap = idx == 0 ? 0 : caps[idx - 1];
        long prevCost = idx == 0 ? 0 : costs[idx - 1];

        return prevCost + (k - prevCap) * prices[idx];
    }

    private long h(long k) {
        return prefixCost(k) - 2L * cheapest * k;
    }

    private long minHUpTo(long k) {
        if (k <= negEnd) {
            return h(k);
        }
        return negMin;
    }

    private long minCost(long value) {
        long k1 = value / 2;
        long cost1 = cheapest * value + minHUpTo(k1);

        long k2 = (value + 1) / 2;
        long cost2;
        if (k2 > caps[caps.length - 1]) {
            cost2 = Long.MAX_VALUE / 4;
        } else {
            cost2 = prefixCost(k2);
        }

        return Math.min(cost1, cost2);
    }
}