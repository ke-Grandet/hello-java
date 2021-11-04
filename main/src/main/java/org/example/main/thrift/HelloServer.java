package org.example.main.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.example.main.thrift.lib.HelloService;

public class HelloServer {

    public int PORT;

    public HelloServer(String port) {
        PORT = port == null ? 8912 : Integer.parseInt(port);
    }

    public void startServer() {
        try {
            System.out.println("启动HelloServer中……");
            TServerSocket serverSocket = new TServerSocket(PORT);
            TSimpleServer.Args args = new TSimpleServer.Args(serverSocket);
            HelloService.Processor<HelloServiceImpl> processor = new HelloService.Processor<>(new HelloServiceImpl());
            args.transportFactory(new TFramedTransport.Factory());
            args.protocolFactory(new TCompactProtocol.Factory());
            args.processorFactory(new TProcessorFactory(processor));
            TServer server = new TSimpleServer(args);
            System.out.println("HelloServer已启动，端口：" + PORT);
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
