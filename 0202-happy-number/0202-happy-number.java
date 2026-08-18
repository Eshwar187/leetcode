class Solution {
    public int square(int n){
        int sum=0;
        while(n>0){
            int digit=n%10;
            sum+=(digit*digit);
            n/=10;
        }
        return sum;
    }
    public boolean isHappy(int n) {
        int fast=n;
        int slow=n;
        while(fast!=1){
            slow=square(slow);
            fast=square(square(fast));
            if(fast==1) return true;
            if(slow==fast) return false;
        }
        return true;
    }
}