class Solution {
    public int minInsertions(String s) {
        int ins=0;
        int op=0;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c=='(') op++;
            else{
                if(i+1<s.length() && s.charAt(i+1)==')') i++;
                else ins++;
                if(op>0) op--;
                else ins++;
            }
        }
        ins+=op*2;
        return ins;
    }
}