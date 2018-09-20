package com.github.insanusmokrassar.KotlinVSJava.JavaComparisonModule.QuickSortJava;

import com.github.insanusmokrassar.KotlinVSJava.report_formatter.LogPoint;
import com.github.insanusmokrassar.KotlinVSJava.report_formatter.TestDataFormatterKt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.github.insanusmokrassar.KotlinVSJava.report_formatter.LogPointKt.COMPLETE_TEST;
import static com.github.insanusmokrassar.KotlinVSJava.report_formatter.LogPointKt.START_TEST;

public class QuickSort {
    private static final String testName = "QuickSort#Java";
    public static Random random = new Random();
    private static final int countOfElements = 1000;
    private Integer[] ascendRange;
    private Integer[] descendRange;

    public QuickSort(Integer countOfElements) {
        ascendRange = new Integer[countOfElements];
        descendRange = new Integer[countOfElements];

        for (int i = 0; i < countOfElements; i++) {
            ascendRange[i] = i;
            descendRange[i] = countOfElements - i - 1;
        }
    }

    public static void main(String[] args) {
        int countOfElements;
        try {
            countOfElements = Integer.parseInt(args[0]);
        } catch (Exception e) {
            countOfElements = QuickSort.countOfElements;
        }

        QuickSort test = new QuickSort(countOfElements);

        List<LogPoint> points = new ArrayList<>();
        points.add(new LogPoint(testName, START_TEST, System.currentTimeMillis()));
        QuickSorter.sort(test.ascendRange);
        points.add(new LogPoint(testName, "Ascend sorted", System.currentTimeMillis()));
        QuickSorter.sort(test.descendRange);
        points.add(new LogPoint(testName, "Descend sorted", System.currentTimeMillis()));
        points.add(new LogPoint(testName, COMPLETE_TEST, System.currentTimeMillis()));
        TestDataFormatterKt.printPoints(points);
    }
}
