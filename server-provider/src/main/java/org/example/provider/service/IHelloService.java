package org.example.provider.service;

public interface IHelloService {

    void pause(String triggerName, String triggerGroup);

    void resume(String triggerName, String triggerGroup);
}
