class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        long sum=0;
        long max=0;
        Map<Integer,Integer> map=new HashMap<>();
        int dups=0;
        for(int i=0;i<k;i++){
            if(!map.containsKey(nums[i])) map.put(nums[i],0);
            map.put(nums[i],map.get(nums[i])+1);
            sum+=nums[i];
            if(map.get(nums[i])>1) dups++;
        }
        if(dups==0) max=Math.max(sum,max);
        for(int i=k;i<nums.length;i++){
            int numToAdd=nums[i];
            int numToRem=nums[i-k];
            if(!map.containsKey(numToAdd)) map.put(nums[i],0);
            map.put(numToAdd,map.get(numToAdd)+1);
            if(map.get(numToAdd)>1) dups++;
            sum+=numToAdd;
            if(map.get(numToRem)>1) dups--;
            map.put(numToRem,map.get(numToRem)-1);
            sum-=numToRem;
            if(dups==0) max=Math.max(sum,max);
            
        }
        return max;
    }
}