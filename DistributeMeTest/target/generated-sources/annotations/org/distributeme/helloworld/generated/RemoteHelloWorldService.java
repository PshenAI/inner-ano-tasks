package org.distributeme.helloworld.generated;
//CHECKSTYLE:OFF

import java.rmi.Remote;
import java.rmi.RemoteException;
import org.distributeme.core.lifecycle.ServiceAdapter;
import java.util.List;
import java.util.Map;

public interface RemoteHelloWorldService extends Remote, ServiceAdapter{
	List printMessage(java.lang.String message, Map<?,?> __transportableCallContext) throws RemoteException;

}
