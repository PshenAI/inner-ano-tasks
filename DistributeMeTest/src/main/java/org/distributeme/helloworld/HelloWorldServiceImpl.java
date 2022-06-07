package org.distributeme.helloworld;

public class HelloWorldServiceImpl implements HelloWorldService{
    @Override
    public String printMessage(String message) {
        System.out.println(message);
        return message;
    }
}
