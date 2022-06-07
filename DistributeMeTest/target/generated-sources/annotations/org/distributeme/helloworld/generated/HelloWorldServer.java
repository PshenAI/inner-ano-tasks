package org.distributeme.helloworld.generated;
//CHECKSTYLE:OFF

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import java.rmi.server.UnicastRemoteObject;
import java.security.Permission;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import org.distributeme.core.RegistryUtil;
import org.distributeme.core.RegistryLocation;
import java.rmi.server.ExportException;
import org.distributeme.core.ServiceDescriptor;
import org.distributeme.core.util.LocalServiceDescriptorStore;
import org.distributeme.core.ServiceDescriptor.Protocol;
import net.anotheria.anoprise.metafactory.MetaFactory;
import net.anotheria.anoprise.metafactory.FactoryNotFoundException;
import net.anotheria.anoprise.metafactory.Extension;
import net.anotheria.util.PidTools;
import net.anotheria.util.IdCodeGenerator;
import org.distributeme.core.RMIRegistryUtil;
import java.rmi.RemoteException;
import net.anotheria.anoprise.metafactory.ServiceFactory;
import org.distributeme.core.lifecycle.LifecycleComponentImpl;
import org.distributeme.core.Verbosity;
import org.distributeme.core.SystemPropertyNames;
import org.distributeme.core.ServerShutdownHook;
import org.distributeme.core.conventions.SystemProperties;
import java.util.List;
import org.distributeme.core.routing.RoutingAware;

import org.distributeme.support.lifecycle.generated.LifecycleSupportServer;
import org.distributeme.support.eventservice.generated.EventServiceRMIBridgeServer;
import org.distributeme.agents.transporter.generated.TransporterServer;
public class HelloWorldServer{

	private static Logger log;
	private static Marker FATAL = MarkerFactory.getMarker("FATAL");

	public static void main(String a[]) throws Exception{
		if (System.getSecurityManager()==null)
			// We allow all operations.
			System.setSecurityManager(new SecurityManager(){
				public void checkPermission(Permission perm) { }
			});
		try {
			init();
			// Log current server PID (Process Id)
			PidTools.logPid();
			// force verbosity to configure itself
			Verbosity.logServerSideExceptions();
			createSupportServicesAndRegisterLocally();
			createServiceAndRegisterLocally();
			startService();
			notifyListenersAboutStart();
		} catch (Throwable e) {
			log.error(FATAL, "Unhandled exception caught", e);
			System.err.println(e.getMessage());
			System.exit(-4);
		}
	} //...main

	private static final List<org.distributeme.core.listener.ServerLifecycleListener> serverListeners = new java.util.ArrayList<org.distributeme.core.listener.ServerLifecycleListener>(0);
	private static void notifyListenersAboutStart(){
		// configured listeners
		List<org.distributeme.core.listener.ServerLifecycleListener> configuredListeners = org.distributeme.core.listener.ListenerRegistry.getInstance().getServerLifecycleListeners();
		if (configuredListeners!=null && configuredListeners.size()>0){
			for (org.distributeme.core.listener.ServerLifecycleListener listener : configuredListeners){
				try{
					listener.afterStart();
				}catch(Exception e){
					log.error("Couldn't call afterStart on  listener " + listener, e);
				}
			} //...for
		} //...if
	} //...notifyListenersAboutStart

	public static void init() throws Exception{
		log = LoggerFactory.getLogger(HelloWorldServer.class);
		// // CUSTOM CODE STARTED
		// // CUSTOM CODE ENDED
	} //...init

	// Have to keep local reference to the rmiServant and skeleton to prevent gc removal
	private static RemoteHelloWorldService skeleton = null;
	private static RemoteHelloWorldService rmiServant = null;
	private static String serviceId = null;

	public static void createServiceAndRegisterLocally() throws Exception{
		// Use default port, which is -1
		createServiceAndRegisterLocally(-1);
	}

	public static void createServiceAndRegisterLocally(int customRegistryPort) throws Exception{
		// creating impl
		// No factory specified
		try{
			Class<ServiceFactory<org.distributeme.helloworld.HelloWorldService>> factoryClazz = (Class<ServiceFactory<org.distributeme.helloworld.HelloWorldService>>)Class.forName("org.distributeme.helloworld.HelloWorldServiceFactory");
			MetaFactory.addFactoryClass(org.distributeme.helloworld.HelloWorldService.class, Extension.LOCAL, factoryClazz);
		}catch(ClassNotFoundException factoryNotFound){
			try{
				// Even more convenient - try to instantiate the implementation directly
				Class<? extends org.distributeme.helloworld.HelloWorldService> implClazz = (Class<? extends org.distributeme.helloworld.HelloWorldService>)Class.forName("org.distributeme.helloworld.HelloWorldServiceImpl");
				MetaFactory.createOnTheFlyFactory(org.distributeme.helloworld.HelloWorldService.class, Extension.LOCAL, implClazz.newInstance());
			}catch(ClassNotFoundException implNotFound){
				log.info("Giving up trying to find an impl instance, tried org.distributeme.helloworld.HelloWorldServiceFactory and org.distributeme.helloworld.HelloWorldServiceImpl, expect start to fail since init code were empty too and no factory has been supplied explicitely");
			} //...inner catch
		} //...outer catch
		org.distributeme.helloworld.HelloWorldService impl = null;
		try{
			impl = MetaFactory.get(org.distributeme.helloworld.HelloWorldService.class, Extension.LOCAL);
		}catch (FactoryNotFoundException factoryNotFound){
			throw new AssertionError("Un- or mis-configured, can't instantiate service instance for org.distributeme.helloworld.HelloWorldService tried initcode, submitted factory, autoguessed factory (org.distributeme.helloworld.HelloWorldServiceFactory) and impl class (org.distributeme.helloworld.HelloWorldServiceImpl)");
		}
		skeleton = new RemoteHelloWorldServiceSkeleton(impl);
		rmiServant = (RemoteHelloWorldService) UnicastRemoteObject.exportObject(skeleton, SystemProperties.SERVICE_BINDING_PORT.getAsInt());
		serviceId = HelloWorldServiceConstants.getServiceId();
		// Save original serviceId for later RoutingAware call
		String definedServiceId = serviceId;

		String regNameProviderClass = System.getProperty(SystemPropertyNames.REGISTRATION_NAME_PROVIDER);
		if (regNameProviderClass!=null){
			org.distributeme.core.routing.RegistrationNameProvider suppliedNameProvider = (org.distributeme.core.routing.RegistrationNameProvider)Class.forName(regNameProviderClass).newInstance();
			serviceId = suppliedNameProvider.getRegistrationName(serviceId);
		} //...if (regNameProviderClass!=null)

		log.info("Getting local registry");
		Registry registry = null;
		try{
			registry = RMIRegistryUtil.findOrCreateRegistry(customRegistryPort);
		}catch(RemoteException e){
			log.error(FATAL, "Couldn't obtain free port for a local rmi registry", e);
			System.err.println("Couldn't obtain a free port for local rmi registry");
			System.exit(-1);
		}

		log.info("Registering "+serviceId+" locally.");

		try{
			registry.rebind(serviceId, rmiServant);
		}catch(Exception e){;
			log.error(FATAL, "Couldn't rebind myself at the local registry", e);
			System.err.println("Couldn't rebind myself at the local registry");
			e.printStackTrace();
			System.exit(-2);
		} //...local registry bind.

		if (impl instanceof RoutingAware){
			((RoutingAware)impl).notifyServiceId(definedServiceId, serviceId, null, null) ;
		} //.../if impl RoutingAware
		LifecycleComponentImpl.INSTANCE.registerPublicService(serviceId, skeleton);
	}

	public static ServiceDescriptor createDescriptor(String instanceId) throws Exception{
		return RegistryUtil.createLocalServiceDescription(Protocol.RMI,  serviceId, instanceId, RMIRegistryUtil.getRmiRegistryPort());
	}
	public static void startService() throws Exception{
		String instanceId = IdCodeGenerator.generateCode(10);
		boolean registerCentrally = !SystemProperties.SKIP_CENTRAL_REGISTRY.getAsBoolean();
		if (registerCentrally){
			ServiceDescriptor descriptor = createDescriptor(instanceId);
			LocalServiceDescriptorStore.getInstance().addServiceDescriptor(descriptor);

			if (!RegistryUtil.bind(descriptor)){
				log.error(FATAL, "Couldn't bind myself to the central registry at "+RegistryUtil.describeRegistry());
				System.err.println("Couldn't bind myself at the central registry at "+RegistryUtil.describeRegistry());
				System.exit(-3);
			} //...central registry bind
			Runtime.getRuntime().addShutdownHook(new ServerShutdownHook(descriptor));

		}else{
			System.out.println("skipping registration for "+serviceId);
		}
		System.out.println("Server "+serviceId+" is up and ready.");
		Runtime.getRuntime().addShutdownHook(new org.distributeme.core.listener.ServerLifecycleListenerShutdownHook(serverListeners));
	} //...startService

	public static void createSupportServicesAndRegisterLocally() throws Exception{
		org.distributeme.support.lifecycle.generated.LifecycleSupportServer.init();
		org.distributeme.support.lifecycle.generated.LifecycleSupportServer.createServiceAndRegisterLocally();
		org.distributeme.support.eventservice.generated.EventServiceRMIBridgeServer.init();
		org.distributeme.support.eventservice.generated.EventServiceRMIBridgeServer.createServiceAndRegisterLocally();
		org.distributeme.agents.transporter.generated.TransporterServer.init();
		org.distributeme.agents.transporter.generated.TransporterServer.createServiceAndRegisterLocally();
	} //...createSupportServicesAndRegisterLocally

}
