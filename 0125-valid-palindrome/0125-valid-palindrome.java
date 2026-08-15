class Solution {
    public boolean isPalindrome(String s) {
        int i=0;
        int j=s.length()-1;
        while(i<j){
            Character left=s.charAt(i);
            Character right=s.charAt(j);
            if(!Character.isLetterOrDigit(left)){
                i++;
                continue;
            }
            if(!Character.isLetterOrDigit(right)){
                j--;
                continue;
            }
            if(Character.toLowerCase(left)!=Character.toLowerCase(right)) return false;
            i++;
            j--;
        }
        return true;
    }
}