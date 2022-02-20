package by.it.academy.homework_1.controllers.web.listeners;

import by.it.academy.homework_1.service.CounterSession;
import by.it.academy.homework_1.service.api.ICounter;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    private final ICounter counter;

    public SessionListener() {
        this.counter = CounterSession.getInstance();
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        counter.increment();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        counter.decrement();
    }
}
