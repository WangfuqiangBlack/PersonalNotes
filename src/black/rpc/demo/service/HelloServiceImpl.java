package black.rpc.demo.service;

import black.rpc.demo.inter.HelloService;

public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHi(String name) {
		return "Hi, "+name;
	}

}
