package com.github.insanusmokrassar.KotlinVSJava.JavaComparisonModule.QuickSortJava;

import com.github.insanusmokrassar.KotlinVSJava.report_formatter.LogPoint;
import com.github.insanusmokrassar.KotlinVSJava.report_formatter.TestDataFormatterKt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.github.insanusmokrassar.KotlinVSJava.report_formatter.LogPointKt.*;

public class QuickSort {
    private static final String testName = "QuickSort.Java";
    public final static Random random = new Random();
    private static final int countOfElements = 1000;
    private final int[] ascendRange;
    private final int[] descendRange;

    public QuickSort(Integer countOfElements) {
        ascendRange = new int[countOfElements];
        descendRange = new int[countOfElements];

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
        points.add(new LogPoint(testName, ASCEND_CONST, System.currentTimeMillis()));
        QuickSorter.sort(test.descendRange);
        points.add(new LogPoint(testName, DESCEND_CONST, System.currentTimeMillis()));
        points.add(new LogPoint(testName, COMPLETE_TEST, System.currentTimeMillis()));
        TestDataFormatterKt.printPoints(points);
    }
}