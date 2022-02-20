package by.it.academy.homework_1.service;

import by.it.academy.homework_1.service.api.ICounter;

public class CounterSession implements ICounter {

    private static final CounterSession instance = new CounterSession();
    private int count;

    private CounterSession() {
        this.count = 0;
    }

    @Override
    public void increment() {
        this.count++;
    }

    @Override
    public void decrement() {
        this.count--;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    public static CounterSession getInstance() {
        return instance;
    }
}
