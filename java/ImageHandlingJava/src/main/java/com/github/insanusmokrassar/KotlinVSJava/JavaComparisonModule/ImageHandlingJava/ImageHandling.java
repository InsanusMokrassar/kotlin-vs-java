package com.github.insanusmokrassar.KotlinVSJava.JavaComparisonModule.ImageHandlingJava;

import com.github.insanusmokrassar.KotlinVSJava.report_formatter.LogPoint;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.github.insanusmokrassar.KotlinVSJava.report_formatter.LogPointKt.*;
import static com.github.insanusmokrassar.KotlinVSJava.report_formatter.TestDataFormatterKt.printPoints;
import static com.github.insanusmokrassar.KotlinVSJava.report_formatter.utils.FileKt.getFolderFiles;

public class ImageHandling {
    private static final String testName = "ImageHandling.Java";

    public static void main(String[] args) throws IOException {
        List<LogPoint> points = new ArrayList<>();
        points.add(new LogPoint(testName, START_TEST));

        List<File> files = new ArrayList<>();

        for (String arg : args) {
            files.addAll(getFolderFiles(new File(arg)));
        }

        for (File file : files) {
            points.add(new LogPoint(testName, String.format(START_HANDLE_MESSAGE_TEMPLATE, file.getName())));
            ImageHandler.calculateSprites(file);
            points.add(new LogPoint(testName, String.format(COMPLETE_HANDLE_MESSAGE_TEMPLATE, file.getName())));
        }

        points.add(new LogPoint(testName, COMPLETE_TEST));
        printPoints(points);
    }
}
