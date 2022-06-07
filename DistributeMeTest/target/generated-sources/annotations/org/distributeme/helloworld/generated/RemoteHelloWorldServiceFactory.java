package org.distributeme.helloworld.generated;
//CHECKSTYLE:OFF

import net.anotheria.anoprise.metafactory.ServiceFactory;
import net.anotheria.moskito.core.dynamic.ProxyUtils;

public class RemoteHelloWorldServiceFactory implements ServiceFactory<org.distributeme.helloworld.HelloWorldService>{

	public org.distributeme.helloworld.HelloWorldService create(){
		org.distributeme.helloworld.HelloWorldService instance = new RemoteHelloWorldServiceStub();
		return ProxyUtils.createServiceInstance(instance, "HelloWorldServiceDiMe", "remote-service", "default", org.distributeme.helloworld.HelloWorldService.class, net.anotheria.anoprise.metafactory.Service.class);
	} //...create
}
