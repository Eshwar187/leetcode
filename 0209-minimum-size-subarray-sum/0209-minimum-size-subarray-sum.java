class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int sum=0;
        int size=Integer.MAX_VALUE;
        int i=0;
        int j=0;
        while(j<nums.length){
            sum+=nums[j];
            while(sum>=target){
                size=Math.min(size,j-i+1);
                sum-=nums[i];
                i++;
            }
            j++;

        }
        return size==Integer.MAX_VALUE?0:size;
    }
}