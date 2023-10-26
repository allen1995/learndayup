package com.allen.dayup.arithmetic;

import java.util.ArrayList;
import java.util.List;

public class BSTTree {
    Node root;

    public static void main(String[] args) {
        System.out.println("+++++++++++++++ADD TEST+++++++++++++");
        BSTTree bstTree = new BSTTree();
        bstTree.addList(new int[]{1, 2, 3, 4, 5, 6, 7});
        bstTree.print();
        System.out.println("\n+++++++++++++++REMOVE TEST+++++++++++++");
        bstTree.removeVal(1);//root 节点
        bstTree.print();

    }

    public void addList(int[] a) {
        for (int i = 0; i < a.length; i++) {
            addVal(a[i]);
        }
    }


    void print(List<Node> nodes) {
        if (nodes.size() == 0) return;
        List<Node> children = new ArrayList<>();
        System.out.println("\n");
        for (Node node : nodes) {
            if (node.left != null) children.add(node.left);
            if (node.right != null) children.add(node.right);
            System.out.print(node.data + "[parent=" + (node.parent == null ? null : node.parent.data) + "],");
        }
        print(children);
    }

    public void print() {
        List<Node> nodes = new ArrayList<>();
        nodes.add(root);
        print(nodes);
    }


    public void addVal(int val) {
        Node node = new Node(val);

        if (root != null) {
            add(node, root);
        } else {
            root = node;
        }
    }

    private void add(Node node, Node temp) {
        if (node.data == root.data) return;

        if (node.data < root.data) {
            if (temp.left == null) {
                temp.left = node;
                node.parent = temp;
            } else {
                add(node, temp.left);
            }
        } else {
            if (temp.right == null) {
                temp.right = node;
                node.parent = temp;
            } else {
                add(node, temp.right);
            }
        }
    }

    public boolean removeVal(int val) {
        Node target = findNode(val, root);

        if (target == null) {
            return false;
        }

        remove(target);
        return true;
    }

    private void remove(Node node) {
        if (node.left == null && node.right == null) {
            if (node == root) {
                root = null;
            } else {
                removeLeaf(node);
            }
        } else {

            if (node.right == null) {
                if (node == root) {
                    root = node.left;
                } else {
                    // 删除节点不为叶子节点且没有右子节点
                    if (node.parent.left == node) {
                        node.parent.left = node.left;
                    } else if (node.parent.right == node) {
                        node.parent.right = node.left;
                    }

                    node.left.parent = node.parent;
                }


            } else if (node.left == null) {
                if (node == root) {
                    root = node.right;
                } else {
                    if (node.parent.left == node) {
                        node.parent.left = node.right;
                    } else if (node.parent.right == node) {
                        node.parent.right = node.right;
                    }

                    node.right.parent = node.parent;
                }
            } else {
                // 找到替换节点（1.左子树中的最大节点 或者 右子树中的最小节点）
                Node replaceNode = findReplaceNode(node);
                // 把替换节点的值赋给当前节点
                node.data = replaceNode.data;
                // 删除替换节点
                removeLeaf(replaceNode);
            }
        }
    }

    private Node findReplaceNode(Node node) {
        if (node.right == null) {
            return node;
        }
        return findReplaceNode(node.right);
    }

    private void removeLeaf(Node node) {
        Node parent = node.parent;

        if (node == parent.left) {
            parent.left = null;
        } else {
            parent.right = null;
        }
    }

    private Node findNode(int val, Node node) {
        if (node == null) {
            return null;
        }

        if (node.data == val) {
            return node;
        }

        if (node.data > val) {
            return findNode(val, node.left);
        } else {
            return findNode(val, node.right);
        }


    }

    class Node {
        int data;
        Node left;
        Node right;
        Node parent;

        public Node(int data, Node left, Node right, Node parent) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public Node(int data) {
            this(data, null, null, null);
        }
    }
}
