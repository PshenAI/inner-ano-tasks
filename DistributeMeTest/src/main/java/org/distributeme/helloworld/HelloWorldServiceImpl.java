package org.distributeme.helloworld;

public class HelloWorldServiceImpl implements HelloWorldService{
    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }
}
