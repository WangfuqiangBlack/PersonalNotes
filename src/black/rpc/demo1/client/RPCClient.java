package black.rpc.demo1.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

import black.rpc.demo1.util.ZookeeperUtil;

@SuppressWarnings("all")
public class RPCClient {
	private static final String nginxFlag = "/nginxFlag";
	
	public static <T> T getRemoteProxyObj(final Class<?> serviceInterface) {
		ZookeeperUtil zk = new ZookeeperUtil();
		List<String>  list = zk.getIps();
		if(list != null && list.size()>0){
			Integer obj = (Integer) zk.getNode(nginxFlag,true);
			if(obj == list.size()){
				zk.setNode(nginxFlag, 0);
				obj = (Integer) zk.getNode(nginxFlag,true);
			}
			String [] ipPort = list.get(obj).split(":");
			System.out.println(list.get(obj));
			zk.setNode(nginxFlag,++obj);
			final InetSocketAddress addr = new InetSocketAddress(ipPort[0],Integer.parseInt(ipPort[1]));
			// 1.将本地的接口调用转换成JDK的动态代理，在动态代理中实现接口的远程调用
			return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(),
					new Class<?>[] { serviceInterface }, new InvocationHandler() {
						public Object invoke(Object proxy, Method method,
								Object[] args) throws Throwable {
							Socket socket = null;
							ObjectOutputStream output = null;
							ObjectInputStream input = null;
							try {
								// 2.创建Socket客户端，根据指定地址连接远程服务提供者
								socket = new Socket();
								socket.connect(addr);

								// 3.将远程服务调用所需的接口类、方法名、参数列表等编码后发送给服务提供者
								output = new ObjectOutputStream(socket
										.getOutputStream());
								output.writeUTF(serviceInterface.getName());
								output.writeUTF(method.getName());
								output.writeObject(method.getParameterTypes());
								output.writeObject(args);

								// 4.同步阻塞等待服务器返回应答，获取应答后返回
								input = new ObjectInputStream(socket
										.getInputStream());
								return input.readObject();
							} finally {
								if (socket != null)
									socket.close();
								if (output != null)
									output.close();
								if (input != null)
									input.close();
							}
						}
					});
		}
		return null;
	}
}
