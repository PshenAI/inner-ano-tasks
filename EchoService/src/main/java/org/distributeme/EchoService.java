package org.distributeme;

import net.anotheria.anoprise.metafactory.Service;
import org.distributeme.annotation.DistributeMe;
import org.distributeme.annotation.FailBy;
import org.distributeme.core.failing.Failover;

@DistributeMe
@FailBy(strategyClass = Failover.class)
public interface EchoService extends Service {
    Long sendTime(Long time);
}
