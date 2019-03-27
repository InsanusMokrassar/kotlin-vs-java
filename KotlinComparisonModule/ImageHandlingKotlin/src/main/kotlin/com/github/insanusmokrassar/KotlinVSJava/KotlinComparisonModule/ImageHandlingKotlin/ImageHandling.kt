package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ImageHandlingKotlin

import com.github.insanusmokrassar.KotlinVSJava.report_formatter.*
import com.github.insanusmokrassar.KotlinVSJava.report_formatter.utils.getFolderFiles
import java.io.File

private const val testName = "ImageHandling.Kotlin"

fun main(args: Array<String>) {
    val points = arrayListOf<LogPoint>()
    points.add(LogPoint(testName, START_TEST))
    args.flatMap {
        File(it).getFolderFiles()
    }.forEach {
        points.add(LogPoint(testName, START_HANDLE_MESSAGE_TEMPLATE.format(it.name)))
        calculateSprites(it)
        points.add(LogPoint(testName, COMPLETE_HANDLE_MESSAGE_TEMPLATE.format(it.name)))
    }
    points.add(LogPoint(testName, COMPLETE_TEST))
    printPoints(points)
}
