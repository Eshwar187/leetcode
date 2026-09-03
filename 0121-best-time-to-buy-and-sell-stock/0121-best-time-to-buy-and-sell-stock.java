class Solution {
    public int maxProfit(int[] prices) {
        int n=prices.length;
        int max=0;
        int buyp=prices[0];
        for(int i=0;i<n;i++){
            int currp=prices[i]-buyp;
            if(currp>max){
                max=currp;
            }
            if(prices[i]<buyp){
                buyp=prices[i];
            }
        }
        return max;

    }
}