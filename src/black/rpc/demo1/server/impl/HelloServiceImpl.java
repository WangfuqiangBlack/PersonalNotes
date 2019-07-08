package black.rpc.demo1.server.impl;

import black.rpc.demo1.server.HelloService;

public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHi(String name) {
		return  "Hi, " + name;
	}

	@Override
	public String sayHeeloWorld() {
		return "heeloWorld";
	}

}
