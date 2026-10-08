class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb=new StringBuilder();
        int ans=0;
        for(char c:s.toCharArray()){
            if(c=='('){
                if(ans>0) sb.append(c);
                ans++;
            }else{
                ans--;
                if(ans>0) sb.append(c);
            }
            
        }
        return sb.toString();
    }
}