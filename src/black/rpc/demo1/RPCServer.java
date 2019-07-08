package black.rpc.demo1;

import java.io.IOException;

import black.rpc.demo1.centre.Server;
import black.rpc.demo1.centre.impl.ServiceCenter;
import black.rpc.demo1.server.DemoService;
import black.rpc.demo1.server.HelloService;
import black.rpc.demo1.server.impl.DemoServiceImpl;
import black.rpc.demo1.server.impl.HelloServiceImpl;


/**
 * 启动服务端
 *
 */
public class RPCServer {
	public static void main(String[] args) {
		 new Thread(new Runnable() {
	            public void run() {
	                try {
	                    Server serviceServer = new ServiceCenter(8080);
	                    serviceServer.register(HelloService.class, HelloServiceImpl.class);
	                    serviceServer.register(DemoService.class, DemoServiceImpl.class);
	                    serviceServer.start();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }).start();
	}
}
