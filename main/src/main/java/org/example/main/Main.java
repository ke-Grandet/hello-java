package org.example.main;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.example.main.thrift.HelloServer;
import org.example.main.thrift.HelloServiceImpl;
import org.example.main.thrift.lib.HelloService;

public class Main {
    public static void main(String[] args) {
//        HelloServer helloServer = new HelloServer(args[0]);
//        helloServer.startServer();
        try (
                TSocket socket = new TSocket("192.168.188.169", 20120);
                TTransport transport = new TFramedTransport(socket)
        ) {
            TProtocol protocol = new TCompactProtocol(transport);
            HelloService.Client client = new HelloService.Client(protocol);
            transport.open();
            String result = client.sayHello("jack");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
