/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean helper(TreeNode node,int targetSum,int sum){
        if(node==null) return false;
        sum+=node.val;
        if(node.left==null && node.right==null){
            if(sum==targetSum) return true;
        }
        boolean resultL=helper(node.left,targetSum,sum);
        boolean resultR=helper(node.right,targetSum,sum);
        return resultL || resultR; 
    }
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return helper(root,targetSum,0);
    }
}