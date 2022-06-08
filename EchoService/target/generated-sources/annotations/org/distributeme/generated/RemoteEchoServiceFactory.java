package org.distributeme.generated;
//CHECKSTYLE:OFF

import net.anotheria.anoprise.metafactory.ServiceFactory;
import net.anotheria.moskito.core.dynamic.ProxyUtils;

public class RemoteEchoServiceFactory implements ServiceFactory<org.distributeme.EchoService>{

	public org.distributeme.EchoService create(){
		org.distributeme.EchoService instance = new RemoteEchoServiceStub();
		return ProxyUtils.createServiceInstance(instance, "EchoServiceDiMe", "remote-service", "default", org.distributeme.EchoService.class, net.anotheria.anoprise.metafactory.Service.class);
	} //...create
}
