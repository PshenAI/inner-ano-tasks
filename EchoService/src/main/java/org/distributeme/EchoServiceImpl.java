package org.distributeme;

public class EchoServiceImpl implements EchoService{

    @Override
    public Long sendTime(Long time){
        System.out.println(time);
        return time;
    }
}
