class Solution {
    public int maxArea(int[] height) {
        int left=0;
        int right=height.length-1;
        int max=0;
        while(left<right){
            int currArea=Math.min(height[left],height[right]);
            int w= right-left;
            max=Math.max(max,currArea*w);
            if(height[left]<=height[right]){
                left++;
            }else{
                right--;
            }
        }
        return max;
    }
}