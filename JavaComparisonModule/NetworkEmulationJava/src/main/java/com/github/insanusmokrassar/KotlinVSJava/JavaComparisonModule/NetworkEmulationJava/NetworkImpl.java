package com.github.insanusmokrassar.KotlinVSJava.JavaComparisonModule.NetworkEmulationJava;

public class NetworkImpl implements Network {
    private final long defaultDelay;

    public NetworkImpl(long defaultDelay) {
        this.defaultDelay = defaultDelay;
    }

    public NetworkImpl() {
        this(1000L);
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
