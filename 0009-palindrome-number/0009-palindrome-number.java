class Solution {
    public boolean isPalindrome(int x) {
        int rev=0;
        int temp=x;
        while(temp>0){
            int rem=temp%10;
            rev=(rev*10)+rem;
            temp=temp/10;
        }
        if(rev==x){
            return true;
        }
        return false;
    }
}
