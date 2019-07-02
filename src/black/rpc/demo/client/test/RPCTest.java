package black.rpc.demo.client.test;

import java.io.IOException;
import java.net.InetSocketAddress;

import black.rpc.demo.client.proxy.RPCClient;
import black.rpc.demo.inter.HelloService;
import black.rpc.demo.inter.Server;
import black.rpc.demo.service.HelloServiceImpl;
import black.rpc.demo.service.ServiceCenter;

public class RPCTest {
	 
    public static void main(String[] args) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Server serviceServer = new ServiceCenter(8088);
                    serviceServer.register(HelloService.class, HelloServiceImpl.class);
                    serviceServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        HelloService service = RPCClient.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
        System.out.println(service.sayHi("test"));
    }
}