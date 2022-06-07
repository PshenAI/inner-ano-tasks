package org.distributeme.generated;
//CHECKSTYLE:OFF

import java.rmi.Remote;
import java.rmi.RemoteException;
import org.distributeme.core.lifecycle.ServiceAdapter;
import java.util.List;
import java.util.Map;

public interface RemoteEchoService extends Remote, ServiceAdapter{
	List sendTime(java.lang.Long time, Map<?,?> __transportableCallContext) throws RemoteException;

}
