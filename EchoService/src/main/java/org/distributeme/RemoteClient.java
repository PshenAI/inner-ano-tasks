package org.distributeme;

import org.distributeme.core.ServiceLocator;

public class RemoteClient {
    public static void main(String a[]) throws InterruptedException {
        EchoService service = ServiceLocator.getRemote(EchoService.class);
        Thread thread = new Thread(Thread.currentThread());
        while(true){
            synchronized (thread){
                thread.wait(5000);
            }
            System.out.println("Process took: " +
                    (service.sendTime(System.currentTimeMillis()) - System.currentTimeMillis()));
        }
    }
}
