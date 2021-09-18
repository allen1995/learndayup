package com.allen.dayup.arithmetic.leetcode;

import java.util.Deque;
import java.util.LinkedList;

class Solution_对称二叉树 {

    public static void main(String[] args) {
        Integer[] arr = {2,3,3,4,5,null,4};
        TreeNode root = createTree(arr);

        boolean result = isSymmetric(root);

        System.out.println(result);
    }

    public static boolean isSymmetric(TreeNode root) {
        if( root == null ) {
            return false;
        }

        return isSymmetric(root.left, root.right);
    }

    /**
     * 初始化二叉树
     * @param arr
     * @return
     */
    public static TreeNode createTree( Integer[] arr ) {
        if( arr == null || arr.length == 0 ) {
            return null;
        }

        int index = 0;
        int length = arr.length;

        TreeNode root = new TreeNode(arr[0]);
        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        TreeNode currentNode;
        while ( index < length ) {
            index++;
            if( index >= length ) {
                return root;
            }
            currentNode = queue.poll();

            Integer left = arr[index];

            if( left != null  ) {
                currentNode.left = new TreeNode(left);
                queue.offer(currentNode.left);
            }

            index++;
            if( index >= length ) {
                return root;
            }

            Integer right = arr[index];
            if( right != null ) {
                currentNode.right = new TreeNode(right);
                queue.offer(currentNode.right);
            }
        }

        return root;
    }

    private static boolean isSymmetric(TreeNode left, TreeNode right) {
        if( left == null && right == null)  {
            return true;
        }

        if( (left == null) ^ (right == null) ) {
            return false;
        }

        return isSymmetric(left.left, right.right)
                && isSymmetric(left.right, right.left)&& ( left.val == right.val );

    }
}