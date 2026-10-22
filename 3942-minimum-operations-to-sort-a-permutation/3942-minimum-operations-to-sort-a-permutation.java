
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int p = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                p = i;
                break;
            }
        }

        boolean normal = true;
        boolean reversed = true;

        for (int i = 0; i < n; i++) {
            if (nums[(p + i) % n] != i) {
                normal = false;
            }

            if (nums[(p - i + n) % n] != i) {
                reversed = false;
            }
        }

        if (normal) {
            return Math.min(p, n - p + 2);
        }

        if (reversed) {
            return Math.min((p + 1) % n + 1, n - p);
        }

        return -1;
    }
}
