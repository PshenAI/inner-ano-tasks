package org.distributeme.helloworld;

import org.distributeme.core.ServiceLocator;
import java.util.Date;

public class RemoteClient {
    public static void main(String a[]){
        HelloWorldService service = ServiceLocator.getRemote(HelloWorldService.class);
        String message = "Hello world at: "+new Date(System.currentTimeMillis());
        System.out.println("Server should print out following message now: " + message);
        System.out.println(service.printMessage(message));
    }
}
