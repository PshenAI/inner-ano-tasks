package org.distributeme;

import org.distributeme.core.ServiceLocator;

public class RemoteClient {
    public static void main(String a[]){
        EchoService service = ServiceLocator.getRemote(EchoService.class);
        while(true){
            System.out.println("Process took: " +
                    (service.sendTime(System.currentTimeMillis()) - System.currentTimeMillis()));
        }
    }
}
