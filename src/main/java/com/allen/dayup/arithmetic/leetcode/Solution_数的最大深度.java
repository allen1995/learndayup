package com.allen.dayup.arithmetic.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Solution_数的最大深度 {

    public static void main(String[] args) {
        Integer[] arr = {1,2,3,4,null,null,5};

        TreeNode root = createTree(arr);
        int result = maxDepth(root);
        System.out.println(result);

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

    public static int maxDepth(TreeNode root) {
        if( root == null ) {
            return 0;
        }

        Deque<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        int depth = 0;

        while( !queue.isEmpty()) {
            int size = queue.size();
            while( size-- > 0) {
                 TreeNode node = queue.poll();

                if(node.left != null ) {
                    queue.offer(node.left);
                }

                if( node.right != null ) {
                    queue.offer(node.right);
                }
            }
           
            depth++;
        }

        return depth;
    }
}
