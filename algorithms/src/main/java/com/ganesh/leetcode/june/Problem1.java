package com.ganesh.leetcode.june;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Problem1 {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(4);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(7);
        treeNode.left.left = new TreeNode(1);
        treeNode.left.right = new TreeNode(3);
        treeNode.right.left = new TreeNode(6);
        treeNode.right.right = new TreeNode(9);
        inOrder(treeNode);
        invertTree(treeNode);
        System.out.println();
        inOrder(treeNode);
    }

    private static void inOrder(TreeNode treeNode) {
        if (treeNode == null)
            return;

        inOrder(treeNode.left);
        System.out.print(treeNode.val + " ");
        inOrder(treeNode.right);
    }

    //return the root of inverted binary tree
    public static TreeNode invertTree(TreeNode root) {
        invertTheGivenTree(root);
        return root;
    }

    //helper function for recursion calls
    private static void invertTheGivenTree(TreeNode root) {
        if (root == null)
            return;
        //swap
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        //recur
        invertTheGivenTree(root.left);
        invertTheGivenTree(root.right);
    }
}
