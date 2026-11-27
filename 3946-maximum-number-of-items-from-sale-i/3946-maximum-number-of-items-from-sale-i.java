class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n=items.length;
        int[] freeItems=new int[n];
        for(int i=0;i<n;i++){
            int fi=items[i][0];
            for(int j=0;j<n;j++){
                if(i!=j && items[j][0]%fi==0){
                    freeItems[i]++;
                }
            }
        }
        int[] dp=new int[budget+1];
        for(int i=0;i<n;i++){
            int p=items[i][1];
            int bonus=freeItems[i];
            int[] ndp=dp.clone();
            for(int r=0;r<p && r<=budget;r++){
                int best=Integer.MIN_VALUE;
                for(int q=0,b=r;b<=budget ; q++,b+=p){
                    if(q>0){
                        best=Math.max(best,dp[b-p]-(q-1));
                    }
                    if(best!=Integer.MIN_VALUE){
                        ndp[b]=Math.max(ndp[b],best+q+bonus);
                    }
                }
            }
            dp=ndp;
        }
        int ans=0;
        for(int x:dp){
            ans=Math.max(ans,x);
        }
        return ans;
    }
}
