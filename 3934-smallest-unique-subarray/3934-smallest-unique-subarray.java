
import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        int[] sa = new int[n];
        int[] rank = nums.clone();

        for (int i = 0; i < n; i++) {
            sa[i] = i;
        }

        int classes = 100001;

        for (int k = 1; k < n; k *= 2) {
            sa = sort(sa, rank, k, true, classes);
            sa = sort(sa, rank, 0, false, classes);

            int[] newRank = new int[n];
            int newClasses = 1;
            newRank[sa[0]] = 0;

            for (int i = 1; i < n; i++) {
                int a = sa[i - 1];
                int b = sa[i];

                int a2 = a + k < n ? rank[a + k] : -1;
                int b2 = b + k < n ? rank[b + k] : -1;

                if (rank[a] != rank[b] || a2 != b2) {
                    newClasses++;
                }

                newRank[b] = newClasses - 1;
            }

            rank = newRank;
            classes = newClasses;

            if (classes == n) {
                break;
            }
        }

        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            pos[sa[i]] = i;
        }

        int[] lcp = new int[n];
        int h = 0;

        for (int i = 0; i < n; i++) {
            int r = pos[i];

            if (r == 0) continue;

            int j = sa[r - 1];

            while (i + h < n && j + h < n &&
                   nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;

            if (h > 0) h--;
        }

        int ans = n;

        for (int r = 0; r < n; r++) {
            int start = sa[r];

            int common = lcp[r];

            if (r + 1 < n) {
                common = Math.max(common, lcp[r + 1]);
            }

            int len = common + 1;

            if (start + len <= n) {
                ans = Math.min(ans, len);
            }
        }

        return ans;
    }

    private int[] sort(int[] sa, int[] rank, int offset,
                       boolean second, int classes) {
        int n = sa.length;
        int[] count = new int[classes + 1];
        int[] result = new int[n];

        for (int x : sa) {
            int key = second
                ? (x + offset < n ? rank[x + offset] + 1 : 0)
                : rank[x] + 1;

            count[key]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int x = sa[i];

            int key = second
                ? (x + offset < n ? rank[x + offset] + 1 : 0)
                : rank[x] + 1;

            result[--count[key]] = x;
        }

        return result;
    }
}
