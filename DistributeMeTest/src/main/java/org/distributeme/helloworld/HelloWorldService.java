package org.distributeme.helloworld;

import net.anotheria.anoprise.metafactory.Service;
import org.distributeme.annotation.DistributeMe;

@DistributeMe
public interface HelloWorldService extends Service {
    String printMessage(String message);
}
