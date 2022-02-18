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

}
