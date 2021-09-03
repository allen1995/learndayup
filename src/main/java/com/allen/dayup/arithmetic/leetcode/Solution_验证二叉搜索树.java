package com.allen.dayup.arithmetic.leetcode;

import java.util.Stack;

class Solution_验证二叉搜索树 {


    public boolean isValidBST_while( TreeNode root ) {
        if( root == null ) {
            return false;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev1 = null;

        while ( root != null && !stack.isEmpty() ) {
            while ( root != null ) {
                stack.push(root);
                root = root.left;
            }

            if( prev1 != null && prev1.val > root.val ) {
                return false;
            }

            prev1 = root;
            stack.push(root.right);
        }

        return true;
    }

    TreeNode prev;
    public boolean isValidBST_mid( TreeNode root ) {
        if( root == null ) {
            return true;
        }

        if( !isValidBST_mid(root.left)) {
            return false;
        }

        if( prev != null && prev.val >= root.val ) {
            return false;
        }

        prev = root;

        if( !isValidBST_mid(root.right)) {
            return false;
        }

        return true;
    }

    /***
     * 递归解法
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return  isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long min, long max) {
        if( root == null ) {
            return true;
        }

        if( root.val >= max || root.val <= min ) {
            return false;
        }

        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }
}



