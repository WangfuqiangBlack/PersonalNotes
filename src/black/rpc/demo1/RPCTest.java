package black.rpc.demo1;

import java.io.IOException;

import black.rpc.demo1.centre.Server;
import black.rpc.demo1.centre.impl.ServiceCenter;
import black.rpc.demo1.client.RPCClient;
import black.rpc.demo1.server.DemoService;
import black.rpc.demo1.server.HelloService;
import black.rpc.demo1.server.impl.DemoServiceImpl;
import black.rpc.demo1.server.impl.HelloServiceImpl;

/**
 * 
 *rpc客户端启动
 *
 */
public class RPCTest {
	
	public static void main(String[] args) throws IOException {
        Server serviceServer = new ServiceCenter(8080);
        serviceServer.register(HelloService.class, HelloServiceImpl.class);
        serviceServer.register(DemoService.class, DemoServiceImpl.class);
        serviceServer.start();
        HelloService service = RPCClient.getRemoteProxyObj(HelloService.class);
        DemoService demoService = RPCClient.getRemoteProxyObj(DemoService.class);
        System.out.println(service.sayHi("test"));
        System.out.println(service.sayHeeloWorld());
        System.out.println(demoService.demoSayHello("张三"));
    }
	
}
