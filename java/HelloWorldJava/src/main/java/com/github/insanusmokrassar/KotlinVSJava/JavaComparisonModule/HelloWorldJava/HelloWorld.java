package com.github.insanusmokrassar.KotlinVSJava.JavaComparisonModule.HelloWorldJava;

import com.github.insanusmokrassar.KotlinVSJava.report_formatter.LogPoint;

import java.util.ArrayList;
import java.util.List;

import static com.github.insanusmokrassar.KotlinVSJava.report_formatter.LogPointKt.COMPLETE_TEST;
import static com.github.insanusmokrassar.KotlinVSJava.report_formatter.LogPointKt.START_TEST;
import static com.github.insanusmokrassar.KotlinVSJava.report_formatter.TestDataFormatterKt.printPoints;

public class HelloWorld {
    private static final String testName = "HelloWorld.Java";

    public static void main(String[] args) {
        List<LogPoint> points = new ArrayList<>();
        points.add(new LogPoint(testName, START_TEST));
        System.out.println("Hello world!");
        points.add(new LogPoint(testName, COMPLETE_TEST));
        printPoints(points);
    }
}
