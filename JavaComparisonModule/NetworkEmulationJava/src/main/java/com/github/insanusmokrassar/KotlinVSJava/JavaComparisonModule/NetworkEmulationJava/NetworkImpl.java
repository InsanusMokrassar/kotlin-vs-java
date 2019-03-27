package com.github.insanusmokrassar.KotlinVSJava.JavaComparisonModule.NetworkEmulationJava;

public class NetworkImpl implements Network {
    private final long defaultDelay;

    public NetworkImpl() {
        defaultDelay = 1000L;
    }
    public NetworkImpl(long defaultDelay) {
        this.defaultDelay = defaultDelay;
    }

    @Override
    public int request(int data) {
        try {
            Thread.sleep(defaultDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return data;
    }
}
