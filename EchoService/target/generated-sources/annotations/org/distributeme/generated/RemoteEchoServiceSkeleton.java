package org.distributeme.generated;
//CHECKSTYLE:OFF

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.distributeme.core.Verbosity;
import org.distributeme.core.Defaults;
import org.distributeme.core.ServerSideCallContext;
import org.distributeme.core.interceptor.ServerSideRequestInterceptor;
import org.distributeme.core.interceptor.InterceptorResponse;
import org.distributeme.core.interceptor.InterceptionContext;
import org.distributeme.core.interceptor.InterceptorRegistry;
import org.distributeme.core.interceptor.InterceptionPhase;
import org.distributeme.core.concurrencycontrol.ConcurrencyControlStrategy;
import net.anotheria.moskito.core.dynamic.MoskitoInvokationProxy;
import net.anotheria.moskito.core.logging.DefaultStatsLogger;
import net.anotheria.moskito.core.logging.IntervalStatsLogger;
import net.anotheria.moskito.core.logging.SLF4JLogOutput;
import net.anotheria.moskito.core.stats.DefaultIntervals;
import net.anotheria.moskito.core.predefined.ServiceStatsCallHandler;
import net.anotheria.moskito.core.predefined.ServiceStatsFactory;
import net.anotheria.moskito.core.registry.IProducerRegistryAPI;
import net.anotheria.moskito.core.registry.ProducerRegistryAPIFactory;
import net.anotheria.moskito.core.producers.IStatsProducer;

// Generated by org.distributeme.generator.SkeletonGenerator
public class RemoteEchoServiceSkeleton implements RemoteEchoService {

	private static Logger log = LoggerFactory.getLogger(RemoteEchoServiceSkeleton.class);

	private org.distributeme.EchoService implementation;

	private long lastAccess;
	private long created;

	// CONCURRENCY CONTROL
	// Class wide concurrency control strategy 
	private ConcurrencyControlStrategy clazzWideCCStrategy = Defaults.getDefaultConcurrencyControlStrategy();

	private ConcurrencyControlStrategy sendTimeCCStrategy_javalangLongtime = clazzWideCCStrategy;
	// CONCURRENCY CONTROL end

	public RemoteEchoServiceSkeleton(){
		this(null);
	}

	public RemoteEchoServiceSkeleton(org.distributeme.EchoService anImplementation){
		created = System.currentTimeMillis();
		MoskitoInvokationProxy proxy = new MoskitoInvokationProxy(
			anImplementation,
			new ServiceStatsCallHandler(),
			new ServiceStatsFactory(),
			"EchoService", 
			"service",
			"default",
			org.distributeme.EchoService.class, net.anotheria.anoprise.metafactory.Service.class
		);
		implementation = (org.distributeme.EchoService) proxy.createProxy();
		// add moskito logger
		new DefaultStatsLogger(proxy.getProducer(), new SLF4JLogOutput(LoggerFactory.getLogger("moskito.custom.default")));
		new IntervalStatsLogger(proxy.getProducer(), DefaultIntervals.FIVE_MINUTES, new SLF4JLogOutput(LoggerFactory.getLogger("moskito.custom.5m")));
		new IntervalStatsLogger(proxy.getProducer(), DefaultIntervals.FIFTEEN_MINUTES, new SLF4JLogOutput(LoggerFactory.getLogger("moskito.custom.15m")));
		new IntervalStatsLogger(proxy.getProducer(), DefaultIntervals.ONE_HOUR, new SLF4JLogOutput(LoggerFactory.getLogger("moskito.custom.1h")));
		new IntervalStatsLogger(proxy.getProducer(), DefaultIntervals.ONE_DAY, new SLF4JLogOutput(LoggerFactory.getLogger("moskito.custom.1d")));
		//end moskito logger
		// //ADD LOGGING FOR ALL BUILTIN PRODUCERS
		IProducerRegistryAPI api = new ProducerRegistryAPIFactory().createProducerRegistryAPI();
		List<IStatsProducer> stats = api.getAllProducersBySubsystem("builtin");
		for (IStatsProducer producer : stats){
			new DefaultStatsLogger(producer, new SLF4JLogOutput(LoggerFactory.getLogger("moskito.bi.default")));
			new IntervalStatsLogger(producer, DefaultIntervals.FIVE_MINUTES, new SLF4JLogOutput(LoggerFactory.getLogger("moskito.bi.5m")));
			new IntervalStatsLogger(producer, DefaultIntervals.FIFTEEN_MINUTES, new SLF4JLogOutput(LoggerFactory.getLogger("moskito.bi.15m")));
			new IntervalStatsLogger(producer, DefaultIntervals.ONE_HOUR, new SLF4JLogOutput(LoggerFactory.getLogger("moskito.bi.1h")));
			new IntervalStatsLogger(producer, DefaultIntervals.ONE_DAY, new SLF4JLogOutput(LoggerFactory.getLogger("moskito.bi.1d")));
		}
	}

	public List sendTime(java.lang.Long time, Map<?,?> __transportableCallContext){
		lastAccess = System.currentTimeMillis();
		ServerSideCallContext diMeCallContext = new ServerSideCallContext("sendTime", __transportableCallContext);
		diMeCallContext.setServiceId(EchoServiceConstants.getServiceId());
		ArrayList<Object> diMeParameters = new ArrayList<Object>();
		diMeParameters.add(time);
		diMeCallContext.setParameters(diMeParameters);
		InterceptionContext diMeInterceptionContext = new InterceptionContext();
		// Initialize interceptors
		List<ServerSideRequestInterceptor> diMeInterceptors = InterceptorRegistry.getInstance().getServerSideRequestInterceptors();

		ArrayList __return = new ArrayList();

		diMeInterceptionContext.setCurrentPhase(InterceptionPhase.BEFORE_SERVANT_CALL);
		for (ServerSideRequestInterceptor interceptor : diMeInterceptors){
			InterceptorResponse interceptorResponse = interceptor.beforeServantCall(diMeCallContext, diMeInterceptionContext);
			switch(interceptorResponse.getCommand()){
			case ABORT:
				if (interceptorResponse.getException() instanceof RuntimeException)
					throw (RuntimeException) interceptorResponse.getException();
				throw new RuntimeException("Interceptor exception",interceptorResponse.getException());
			case RETURN:
				__return.set(0, interceptorResponse.getReturnValue());
				diMeInterceptionContext.setReturnValue(interceptorResponse.getReturnValue());
				break;
			case OVERWRITE_RETURN_AND_CONTINUE:
				__return.set(0, interceptorResponse.getReturnValue());
				diMeInterceptionContext.setReturnValue(interceptorResponse.getReturnValue());
				break;
			case CONTINUE:
				break;
			} //...switch
		} //...for
		// Concurrency control, server side - 
		sendTimeCCStrategy_javalangLongtime.notifyServerSideCallStarted(diMeCallContext);

		try{
			Object __result = implementation.sendTime(time);
			__return.add(__result);
			diMeInterceptionContext.setReturnValue(__result);
			__return.add(diMeCallContext.getTransportableCallContext());
			diMeInterceptionContext.setCurrentPhase(InterceptionPhase.AFTER_SERVANT_CALL);
			for (ServerSideRequestInterceptor interceptor : diMeInterceptors){
				InterceptorResponse interceptorResponse = interceptor.afterServantCall(diMeCallContext, diMeInterceptionContext);
				switch(interceptorResponse.getCommand()){
				case ABORT:
					if (interceptorResponse.getException() instanceof RuntimeException)
						throw (RuntimeException) interceptorResponse.getException();
					throw new RuntimeException("Interceptor exception",interceptorResponse.getException());
				case RETURN:
					__return.set(0, interceptorResponse.getReturnValue());
					diMeInterceptionContext.setReturnValue(interceptorResponse.getReturnValue());
					break;
				case OVERWRITE_RETURN_AND_CONTINUE:
					__return.set(0, interceptorResponse.getReturnValue());
					diMeInterceptionContext.setReturnValue(interceptorResponse.getReturnValue());
					break;
				case CONTINUE:
					break;
				} //...switch
			} //...for
			return __return;
		}finally{
			sendTimeCCStrategy_javalangLongtime.notifyServerSideCallFinished(diMeCallContext);
		}
	}

	// Service adapter methods
	public long getCreationTimestamp(){ return created; }
	public long getLastAccessTimestamp(){ return lastAccess; }

}
