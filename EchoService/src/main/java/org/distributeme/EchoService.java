package org.distributeme;

import net.anotheria.anoprise.metafactory.Service;
import org.distributeme.annotation.DistributeMe;

@DistributeMe
public interface EchoService extends Service {
    Long sendTime(Long time);
}
