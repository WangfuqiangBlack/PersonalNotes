package black.rpc.demo1.server.impl;

import black.rpc.demo1.server.DemoService;

public class DemoServiceImpl implements DemoService{

	@Override
	public String demoSayHello(String name) {
		return "hello:"+name;
	}

}
