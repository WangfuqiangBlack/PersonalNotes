package black.rpc.demo.inter;

import java.io.IOException;

/**
 * 服务中心
 * @author LENOVO
 *
 */
public interface Server {

	public void stop();
	
	public void start() throws IOException;
	
	public void register(Class serviceInterface,Class impl);
	
	public boolean isRunning();
	
	public int getPort();
}
