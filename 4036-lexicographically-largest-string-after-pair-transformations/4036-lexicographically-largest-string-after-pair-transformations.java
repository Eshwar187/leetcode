class Solution {
    public String[] largestString(int[] nums) {
       String[] ans=new String[nums.length];
        int Z=1<<25;
        for(int i=0;i<nums.length;i++){
            int x=nums[i];
            StringBuilder s=new StringBuilder();
            int z=x/Z;
            for(int j=0;j<z;j++) s.append('z');
            for(int b=24;b>=0;b--){
                if((x&(1<<b))!=0) s.append((char)('a'+b));
            }
            ans[i]=s.toString();
        }
        return ans;
    }
}