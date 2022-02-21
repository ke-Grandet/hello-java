package org.example.main.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树
 */
public class BinaryTree {
    public int value;
    public BinaryTree left = null;
    public BinaryTree right = null;

    public BinaryTree(int value) {
        this.value = value;
    }

    /**
     * 先序遍历，递归实现
     *
     * @param binaryTree 目标二叉树
     * @return 遍历结果
     */
    public static List<Integer> preOrderRecursion(BinaryTree binaryTree) {
        if (binaryTree == null)
            return new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(binaryTree.value);
        if (binaryTree.left != null)
            list.addAll(preOrderRecursion(binaryTree.left));
        if (binaryTree.right != null)
            list.addAll(preOrderRecursion(binaryTree.right));
        return list;
    }

    /**
     * 先序遍历，非递归实现
     *
     * @param binaryTree 目标二叉树
     * @return 遍历结果
     */
    public static List<Integer> preOrder(BinaryTree binaryTree) {
        if (binaryTree == null)
            return new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Stack<BinaryTree> stack = new Stack<>();
        stack.push(binaryTree);
        while (stack.size() > 0) {
            BinaryTree tree = stack.pop();
            list.add(tree.value);
            if (tree.right != null) {
                stack.push(tree.right);
            }
            if (tree.left != null) {
                stack.push(tree.left);
            }
        }
        return list;
    }

    /**
     * 返回一个二叉树
     * @return 二叉树
     */
    public static BinaryTree getDefaultBinaryTree() {
        BinaryTree a0 = new BinaryTree(0);
        BinaryTree a1 = new BinaryTree(1);
        BinaryTree a2 = new BinaryTree(2);
        BinaryTree a3 = new BinaryTree(3);
        BinaryTree a4 = new BinaryTree(4);
        BinaryTree a5 = new BinaryTree(5);
        BinaryTree a6 = new BinaryTree(6);
        BinaryTree a7 = new BinaryTree(7);
        BinaryTree a8 = new BinaryTree(8);
        BinaryTree a9 = new BinaryTree(9);
        BinaryTree a10 = new BinaryTree(10);
        BinaryTree a11 = new BinaryTree(11);
        a0.left = a1;
        a0.right = a2;
        a1.left = a3;
        a1.right = a4;
        a4.left = a5;
        a4.right = a6;
        a5.left = a7;
        a2.left = a8;
        a8.right = a9;
        a9.left = a10;
        a9.right = a11;
        return a0;
    }

}
