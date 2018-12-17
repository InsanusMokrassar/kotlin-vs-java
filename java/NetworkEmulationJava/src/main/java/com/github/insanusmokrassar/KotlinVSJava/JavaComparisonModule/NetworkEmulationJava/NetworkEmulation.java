package com.github.insanusmokrassar.KotlinVSJava.JavaComparisonModule.NetworkEmulationJava;

import com.github.insanusmokrassar.KotlinVSJava.report_formatter.LogPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static com.github.insanusmokrassar.KotlinVSJava.report_formatter.LogPointKt.*;

public class NetworkEmulation {
    private static final String testName = "NetworkEmulation.Java";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int countOfRequests = 1000000;
        try {
            countOfRequests = Integer.valueOf(args[0]);
        } catch (Exception ignored) {}

        ExecutorService executor = Executors.newFixedThreadPool(8);
        Network network = new NetworkImpl();

        List<LogPoint> points = new ArrayList<>();
        points.add(new LogPoint(testName, START_TEST));

        List<Request> requests = new ArrayList<>();

        for (int i = 0; i < countOfRequests; i++) {
            requests.add(
                new Request(i, network)
            );
        }
        points.add(new LogPoint(testName, REQUESTS_FILLED));

        List<Future<Integer>> futures = new ArrayList<>();

        for (final Request request : requests) {
            Future<Integer> future = executor.submit(
                    new Callable<Integer>() {
                        @Override
                        public Integer call() throws Exception {
                            return request.request();
                        }
                    }
            );

            futures.add(future);
        }
        points.add(new LogPoint(testName, ALL_TASKS_CREATED));

        for (Future<Integer> future : futures) {
            future.get();
        }
        points.add(new LogPoint(testName, ALL_TASKS_COMPLETED));

        points.add(new LogPoint(testName, COMPLETE_TEST));
    }
}
