class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int[][] sortedQueries = new int[queries.length][2];

        for (int i = 0; i < queries.length; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));

        int[] ans = new int[queries.length];
        int[] count = new int[n + 1];

        int left = 0, right = 0, active = 0;

        for (int[] q : sortedQueries) {
            int start = q[0] - x;
            int end = q[0];

            while (right < logs.length && logs[right][1] <= end) {
                int id = logs[right][0];
                if (count[id] == 0) active++;
                count[id]++;
                right++;
            }

            while (left < right && logs[left][1] < start) {
                int id = logs[left][0];
                count[id]--;
                if (count[id] == 0) active--;
                left++;
            }

            ans[q[1]] = n - active;
        }

        return ans;
    }
}