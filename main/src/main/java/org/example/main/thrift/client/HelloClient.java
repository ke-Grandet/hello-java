package org.example.main.thrift.client;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.example.main.thrift.lib.HelloService;

public class HelloClient {

    public void run(int port) {
        // 客户端发送请求
        try (
                TSocket socket = new TSocket("192.168.188.169", 1234);
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
