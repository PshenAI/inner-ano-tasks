package org.distributeme.helloworld.generated;
//CHECKSTYLE:OFF

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import org.distributeme.core.RegistryUtil;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import org.distributeme.core.ServiceDescriptor;
import org.distributeme.core.ServiceDescriptor.Protocol;
import org.distributeme.core.DiscoveryMode;
import org.distributeme.core.failing.FailingStrategy;
import org.distributeme.core.concurrencycontrol.ConcurrencyControlStrategy;
import org.distributeme.core.failing.FailDecision;
import org.distributeme.core.ClientSideCallContext;
import org.distributeme.core.exception.DistributemeRuntimeException;
import org.distributeme.core.exception.NoConnectionToServerException;
import org.distributeme.core.exception.ServiceUnavailableException;
import org.distributeme.core.Defaults;
import org.distributeme.core.interceptor.ClientSideRequestInterceptor;
import org.distributeme.core.interceptor.InterceptorRegistry;
import org.distributeme.core.interceptor.InterceptorResponse;
import org.distributeme.core.interceptor.InterceptionContext;
import org.distributeme.core.interceptor.InterceptionPhase;
import org.distributeme.core.interceptor.FailedByInterceptorException;

public class RemoteHelloWorldServiceStub implements org.distributeme.helloworld.HelloWorldService{


	private volatile ConcurrentMap<String,RemoteHelloWorldService> delegates = new ConcurrentHashMap<String,RemoteHelloWorldService>();

	private DiscoveryMode discoveryMode = DiscoveryMode.AUTO;

	// ROUTER DECL V2
	// No class-wide-router set, skipping.

	// Method wide routers if applicable 
	// Method wide routers END 

	// ROUTER DECL V2 end

	// Failing
	// Class wide failing strategy 
	private FailingStrategy clazzWideFailingStrategy = Defaults.getDefaultFailingStrategy();

	private FailingStrategy printMessageFailingStrategy_javalangStringmessage = clazzWideFailingStrategy;
	// Failing end

	// CONCURRENCY CONTROL
	// Class wide concurrency control strategy 
	private ConcurrencyControlStrategy clazzWideCCStrategy = Defaults.getDefaultConcurrencyControlStrategy();

	private ConcurrencyControlStrategy printMessageCCStrategy_javalangStringmessage = clazzWideCCStrategy;
	// CONCURRENCY CONTROL end

	public RemoteHelloWorldServiceStub(){
		discoveryMode = DiscoveryMode.AUTO;
	}

	private ServiceDescriptor manuallySetDescriptor;
	private RemoteHelloWorldService manuallySetTarget;

	public RemoteHelloWorldServiceStub(ServiceDescriptor target){
		discoveryMode = DiscoveryMode.MANUAL;
		manuallySetDescriptor = target;
		try{
			manuallySetTarget = lookup(manuallySetDescriptor);
		}catch(NoConnectionToServerException e){
			throw new IllegalStateException("Can not resolve manually set reference", e);
		}
	}

	public void printMessage(java.lang.String message){
		printMessage(message, (ClientSideCallContext)null);
	} //...public void printMessage(java.lang.String message)

	private void printMessage(java.lang.String message, org.distributeme.core.ClientSideCallContext diMeCallContext){
		List __fromServerSide = null;
		Exception exceptionInMethod = null;
		// This flag is used by the interceptor logic to mark a request es failed, even it is not.
		boolean diMeForceFailing = false;
		boolean abortAndFail = false;
		if (diMeCallContext == null)
			diMeCallContext = new ClientSideCallContext("printMessage");
		if (discoveryMode == DiscoveryMode.MANUAL) {
			diMeCallContext.setServiceId(manuallySetDescriptor.getServiceId());
		}
		if (discoveryMode==DiscoveryMode.AUTO && diMeCallContext.getServiceId()==null)
			diMeCallContext.setServiceId(HelloWorldServiceConstants.getServiceId());

		HashMap __transportableCallContext = diMeCallContext.getTransportableCallContext();
		// Initialize interceptors
		List<ClientSideRequestInterceptor> diMeInterceptors = InterceptorRegistry.getInstance().getClientSideRequestInterceptors();
		InterceptionContext diMeInterceptionContext = new InterceptionContext();

		// Concurrency control, client side - start
		printMessageCCStrategy_javalangStringmessage.notifyClientSideCallStarted(diMeCallContext);

		try{
			ArrayList<Object> diMeParameters = new ArrayList<Object>();
			diMeParameters.add(message);
			diMeCallContext.setParameters(diMeParameters);
			diMeInterceptionContext.setCurrentPhase(InterceptionPhase.BEFORE_SERVICE_CALL);
			for (ClientSideRequestInterceptor interceptor : diMeInterceptors){
				InterceptorResponse interceptorResponse = interceptor.beforeServiceCall(diMeCallContext, diMeInterceptionContext);
				switch(interceptorResponse.getCommand()){
				case ABORT:
					if (interceptorResponse.getException() instanceof RuntimeException)
						throw (RuntimeException) interceptorResponse.getException();
					throw new RuntimeException("Interceptor exception",interceptorResponse.getException());
				case RETURN:
					return;
				case CONTINUE:
					break;
				case ABORT_AND_FAIL:
					abortAndFail = true;
					exceptionInMethod = new FailedByInterceptorException();
					break;
				case RETURN_AND_FAIL:
				// Force failing logic to work.
					diMeForceFailing = true;
					exceptionInMethod = new FailedByInterceptorException();
					break;
				default:
					throw new IllegalStateException("Unsupported or unexpected command from interceptor " + interceptorResponse.getCommand()+ " in phase:"+diMeInterceptionContext.getCurrentPhase());
				} //...switch
			} //...for
			// parse parameters again in case an interceptor modified them
			message = (java.lang.String) diMeParameters.get(0);
			if (!abortAndFail){
				__fromServerSide = getDelegate(diMeCallContext.getServiceId()).printMessage(message,  __transportableCallContext);
				__transportableCallContext.putAll(((HashMap)__fromServerSide.get(1)));
				return;
			}
		}catch(RemoteException e){
			// handle exceptions properly
			e.printStackTrace();
			notifyDelegateFailed(diMeCallContext.getServiceId());
			exceptionInMethod = e;
		}catch(NoConnectionToServerException e){
			exceptionInMethod = e;
		}finally{
			// Concurrency control, client side - end
			printMessageCCStrategy_javalangStringmessage.notifyClientSideCallFinished(diMeCallContext);
			diMeInterceptionContext.setCurrentPhase(InterceptionPhase.AFTER_SERVICE_CALL);
			if (__fromServerSide!=null){
				diMeInterceptionContext.setReturnValue(__fromServerSide.get(0));
			}
			diMeInterceptionContext.setException(exceptionInMethod);
			boolean diMeReturnOverriden = false;
			for (ClientSideRequestInterceptor interceptor : diMeInterceptors){
				InterceptorResponse interceptorResponse = interceptor.afterServiceCall(diMeCallContext, diMeInterceptionContext);
				switch(interceptorResponse.getCommand()){
				case ABORT:
					if (interceptorResponse.getException() instanceof RuntimeException)
						throw (RuntimeException) interceptorResponse.getException();
					throw new RuntimeException("Interceptor exception",interceptorResponse.getException());
				case RETURN:
					return;
				case CONTINUE:
					break;
				case ABORT_AND_FAIL:
					abortAndFail = true;
					break;
				case RETURN_AND_FAIL:
				// Force failing logic to work.
				printMessageFailingStrategy_javalangStringmessage.callFailed(diMeCallContext);
					break;
				default:
					throw new IllegalStateException("Unsupported or unexpected command from interceptor " + interceptorResponse.getCommand()+ " in phase:"+diMeInterceptionContext.getCurrentPhase());
				} //...switch
			} //...for

		} //...finally
		// Failing
		if (exceptionInMethod!=null || diMeForceFailing || abortAndFail){
			FailDecision failDecision = printMessageFailingStrategy_javalangStringmessage.callFailed(diMeCallContext);
			if (failDecision.getTargetService()!=null)
				diMeCallContext.setServiceId(failDecision.getTargetService());
			switch(failDecision.getReaction()){
				case RETRY:
					printMessage(message, diMeCallContext.increaseCallCount());
					return;
				case RETRYONCE:
					// Only retry if its the first call
					if (!diMeCallContext.isFirstCall())
						break;
					printMessage(message, diMeCallContext.increaseCallCount());
					return;
				case FAIL:
				default:
				// Fail or default is to do nothing at all and let the request fail
			} //...switch(failDecision)
		}
		// fail through, if we are here, we must have had an exception before.
		if (exceptionInMethod == null)
			throw new AssertionError("Exception must have been thrown before, but it wasn't, framework error!");
		throw mapException(exceptionInMethod);
	}

	// according to findbugs this method is never used, so we remove it for now reduce warnings.

	//private void notifyDelegateFailed(){
		//notifyDelegateFailed(HelloWorldServiceConstants.getServiceId());
	//}

	private void notifyDelegateFailed(String serviceId){
		if (discoveryMode==DiscoveryMode.MANUAL){
			manuallySetTarget = null;
			return;
		}
		if (serviceId!=null)
			delegates.remove(serviceId);
	} //...notifyDelegateFailed

	private RemoteHelloWorldService getDelegate() throws NoConnectionToServerException{
		if (discoveryMode==DiscoveryMode.MANUAL){
			if (manuallySetTarget!=null)
				return manuallySetTarget;
			manuallySetTarget = lookup(manuallySetDescriptor);
			return manuallySetTarget;
		} //...if (mode==MANUAL)
		return getDelegate(HelloWorldServiceConstants.getServiceId());
	}

	private RemoteHelloWorldService getDelegate(String serviceId) throws NoConnectionToServerException{
		// if no serviceId is provided, fallback to default resolve with manual mode
		if (serviceId==null || discoveryMode==DiscoveryMode.MANUAL)
			return getDelegate();
		RemoteHelloWorldService delegate = delegates.get(serviceId);
		if (delegate==null){
			try{
				delegate = lookup(serviceId);
				RemoteHelloWorldService existingDelegate = delegates.putIfAbsent(serviceId, delegate);
				if (existingDelegate!=null)
					delegate = existingDelegate;
			}catch(Exception e){
			// //TODO - generate and throw typed exception.
				throw new NoConnectionToServerException("Couldn't lookup delegate because: "+e.getMessage()+" at "+RegistryUtil.describeRegistry(), e);
			}//try
		} //...first if (del==null) 
		return delegate;
	} //...fun

	private RemoteHelloWorldService lookup(String serviceId) throws NoConnectionToServerException{
		// //first we need to lookup target host.
		ServiceDescriptor toLookup = new ServiceDescriptor(Protocol.RMI, serviceId);
		ServiceDescriptor targetService = RegistryUtil.resolve(toLookup);
		if (targetService==null)
			throw new RuntimeException("Can't resolve host for an instance of "+HelloWorldServiceConstants.getServiceId());
		Registry registry = null;
		try{
			registry = LocateRegistry.getRegistry(targetService.getHost(), targetService.getPort());
		}catch(Exception e){
			System.err.println("lookup - couldn't obtain rmi registry on "+targetService+", aborting lookup"); e.printStackTrace();
			throw new NoConnectionToServerException("Can't resolve rmi registry for an instance of "+HelloWorldServiceConstants.getServiceId());
		}
		try{
			return (RemoteHelloWorldService) registry.lookup(serviceId);
		}catch(RemoteException e){
			throw new NoConnectionToServerException("Can't lookup service in the target rmi registry for an instance of "+serviceId, e);
		}catch(NotBoundException e){
			throw new NoConnectionToServerException("Can't lookup service in the target rmi registry for an instance of "+serviceId, e);
		}
	}

	private RemoteHelloWorldService lookup(ServiceDescriptor serviceDescriptor) throws NoConnectionToServerException{
		Registry registry = null;
		try{
			registry = LocateRegistry.getRegistry(serviceDescriptor.getHost(), serviceDescriptor.getPort());
		}catch(Exception e){
			System.err.println("lookup - couldn't obtain rmi registry on "+serviceDescriptor+", aborting lookup"); e.printStackTrace();
			throw new NoConnectionToServerException("Can't resolve rmi registry for "+serviceDescriptor);
		}
		try{
			RemoteHelloWorldService ret = (RemoteHelloWorldService) registry.lookup(serviceDescriptor.getServiceId());
			return ret;
		}catch(RemoteException e){
			throw new NoConnectionToServerException("Can't lookup service in the target rmi registry for an instance of "+serviceDescriptor, e);
		}catch(NotBoundException e){
			throw new NoConnectionToServerException("Can't lookup service in the target rmi registry for an instance of "+serviceDescriptor, e);
		}
	}

	private DistributemeRuntimeException mapException(Exception in){
		if (in instanceof DistributemeRuntimeException)
			return (DistributemeRuntimeException) in;
		if (in instanceof RemoteException)
			return new ServiceUnavailableException ("Service unavailable due to rmi failure: "+in.getMessage(), in);
		return new ServiceUnavailableException("Unexpected exception: "+in.getMessage()+" " + in.getClass().getName(), in);
	}
}
