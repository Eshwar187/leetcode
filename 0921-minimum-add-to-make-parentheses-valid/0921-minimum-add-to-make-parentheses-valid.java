class Solution {
    public int minAddToMakeValid(String s) {
        int count=0;
        int ans=0;
        for(char c:s.toCharArray()){
            if(c=='(') count++;
            else {
                if(count>0) count--;
                else ans++;
            }
        }
        ans+=count;
        return ans;
    }
}