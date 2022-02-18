package org.example.main;


import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.example.main.other.BinaryTree;
import org.example.main.other.Sorting;
import org.example.main.thrift.lib.deletelater.StruVSAPSimpleReq;
import org.example.main.thrift.lib.deletelater.User;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
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
        List<Integer> listRecursion = BinaryTree.preOrderRecursion(a0);
        System.out.println(listRecursion);
        List<Integer> list = BinaryTree.preOrder(a0);
        System.out.println(list);
    }

    static class Temp {

        public void run() {
            // 客户端发送请求
            try (
                    TSocket socket = new TSocket("192.168.188.169", 1234);
                    TTransport transport = new TFramedTransport(socket)
            ) {
                TProtocol protocol = new TCompactProtocol(transport);
                User.Client client = new User.Client(protocol);
                transport.open();
                StruVSAPSimpleReq req = new StruVSAPSimpleReq();
                Object obj = client.SimpleRequest(req);
                System.out.println(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
