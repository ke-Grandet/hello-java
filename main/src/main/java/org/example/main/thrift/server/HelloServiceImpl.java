package org.example.main.thrift.server;

import org.apache.thrift.TException;
import org.example.main.thrift.lib.HelloService;

public class HelloServiceImpl implements HelloService.Iface {
    @Override
    public String sayHello(String name) throws TException {
        System.out.println("name: " + name);
        return String.format("hello %s!", name);
    }
}
