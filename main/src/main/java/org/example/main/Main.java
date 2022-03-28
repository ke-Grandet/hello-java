package org.example.main;


import org.example.main.other.BinaryTree;
import org.example.main.other.Sorting;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BinaryTree binaryTree = BinaryTree.getDefaultBinaryTree();
        System.out.println(BinaryTree.midOrderRecursion(binaryTree));
        System.out.println(BinaryTree.midOrder(binaryTree));
    }

}
