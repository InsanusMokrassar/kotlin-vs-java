package com.github.insanusmokrassar.KotlinVSJava.JavaComparisonModule.NetworkEmulationJava;

public class Request {
    private final int data;
    private final Network network;

    public Request(int data, Network network) {
        this.data = data;
        this.network = network;
    }

    public int request() {
        return network.request(data);
    }
}
