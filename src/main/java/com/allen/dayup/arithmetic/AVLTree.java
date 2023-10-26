package com.allen.dayup.arithmetic;

import com.allen.dayup.thinkinjava.A;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {
    Node root;

    void printNodeVal(List<Node> nodes){
        List<Node> children =  new ArrayList<>();
        for(Node node : nodes ){
            System.out.print(node.data+",");
            if(node.left!=null)children.add(node.left);
            if(node.right!=null)children.add(node.right);
        }
        System.out.println("\n*******************************");
        if(children.isEmpty())return;
        printNodeVal(children);
    }


    public void print(){
        List<Node> nodes =  new ArrayList<>();
        nodes.add(root);
        printNodeVal(nodes);
    }

    
    public static void main(String[] args){
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i <10; i++) {
            avlTree.addVal(i);
        }
        System.out.println("+++++++++++++++++++++++++++TEST ADD+++++++++++++++++++++++++++");
        avlTree.print();

    }


    public Node LL_rotate(Node node) {
        Node parent = node.parent;
        Node left = node.left;
        Node lRight = left.right;

        // 父节点
        if (parent == null) {
            root = left;
        } else {
            if (parent.left == node) {
                parent.left = left;
            } else {
                parent.right = left;
            }
        }

        // 左节点
        left.parent = parent;
        left.right = node;

        // 原顶点
        node.parent = left;
        node.left = lRight;

        if (lRight != null) {
            lRight.parent = node;
        }

        // 更新高度
        updateH(node);
        updateH(left);

        return left;
    }

    /**
     * 节点右旋方法
     *
     * @param node
     * @return
     */
    public Node RR_rotate(Node node) {
        Node parent = node.parent;
        Node right = node.right;
        Node rLeft = right.left;

        // 父节点
        if (parent == null) {
            root = right;
        } else {
            if (parent.left == node) {
                parent.left = right;
            } else {
                parent.right = right;
            }
        }

        // 右节点
        right.parent = parent;
        right.left = node;

        // node
        node.parent = right;
        node.right = rLeft;

        if (rLeft != null) {
            rLeft.parent = node;
        }

        return right;
    }

    public Node LR_rotate(Node node) {
        RR_rotate(node.left);
        return LL_rotate(node);
    }

    public Node RL_rotate(Node node) {
        LL_rotate(node.right);
        return RR_rotate(node);
    }

    public boolean addVal(int val) {
        Node node = new Node(val);
        if (root == null) {
            root = node;
            return true;
        }
        boolean addSuccess = addNode(node, root);//添加节点
        if (!addSuccess) return false;
        insertBalance(node.parent);//调节平衡
        return true;
    }

    boolean addNode(Node node, Node cur) {

        if (cur.data == node.data) return false;
        else if (cur.data > node.data) {
            if (cur.left == null) {
                cur.left = node;
                node.parent = cur;
                return true;
            }
            return addNode(node, cur.left);
        } else {
            if (cur.right == null) {
                cur.right = node;
                node.parent = cur;
                return true;
            }
            return addNode(node, cur.right);
        }

    }

    void insertBalance(Node node) {
        if (node == null) return;
        boolean update = this.updateH(node);
        if (!update) return;//没有更新高度
        if (rotate(node)) return;//旋转一次失衡节点高度不变，不用向上更新高度；
        insertBalance(node.parent);//更新高度之后没有旋转，说明节点高度增加，要继续向上更新高度；
    }

    boolean rotate(Node node) {
        int hDif = getNodeH(node.left) - getNodeH(node.right);
        if (hDif == 0 || hDif == -1 || hDif == 1) return false;
        if (hDif == 2) {// h_left > h_right
            Node left = node.left;
            int dif = getNodeH(left.left) - getNodeH(left.right);
            if (dif >= 0) {//ll
                LL_rotate(node);
            } else {//lr
                LR_rotate(node);
            }

        } else {// h_left < h_right

            Node right = node.right;
            int dif = getNodeH(right.right) - getNodeH(right.left);
            if (dif >= 0) {//RR
                RR_rotate(node);
            } else {//RL
                RL_rotate(node);
            }

        }
        return true;
    }
    

    private boolean updateH(Node node) {
        int leftH = getNodeH(node.left);
        int rightH = getNodeH(node.right);
        int oldH = node.h;
        node.h = leftH > rightH ? leftH + 1 : rightH + 1;

        return oldH != node.h;
    }

    private int getNodeH(Node node) {
        if (node == null) {
            return -1;
        }

        return node.h;
    }

    class Node {
        int data;
        int h;
        Node left;
        Node right;
        Node parent;

        public Node(int data, int h, Node left, Node right, Node parent) {
            this.data = data;
            this.h = h;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public Node(int data) {
            this(data, 0, null, null, null);
        }
    }
}
