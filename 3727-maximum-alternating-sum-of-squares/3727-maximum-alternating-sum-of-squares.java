class Solution {
    public long maxAlternatingSum(int[] nums) {
        Arrays.sort(nums);
        int n=nums.length;
        long ans=0;
        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++) {
            arr[i] = nums[i];
        }
        Arrays.sort(arr, (a, b) ->
            Integer.compare(Math.abs(a), Math.abs(b))
        );

        for (int i = 0; i < n; i++) {
            long square = 1L * arr[i] * arr[i];

            if (i < n / 2) {
                ans -= square;
            } else {
                ans += square;
            }
        }

        return ans;
    }
}