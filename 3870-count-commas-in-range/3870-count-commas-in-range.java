class Solution {
    public int countCommas(int n) {
        int s=1000;
        int ans=0;
        int c=1;
        while(s<=n){
            long next=(long) s*1000;
            int e=(int) Math.min((long) n,next-1);
            ans+=(e-s+1)*c;
            s*=1000;
            c++;
        }
        return ans;
    }
}