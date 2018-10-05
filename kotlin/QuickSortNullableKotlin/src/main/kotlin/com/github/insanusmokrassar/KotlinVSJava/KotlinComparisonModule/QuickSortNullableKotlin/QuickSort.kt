package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.QuickSortNullableKotlin

import com.github.insanusmokrassar.KotlinVSJava.report_formatter.*
import java.lang.Exception

import java.util.ArrayList
import java.util.Random

var random = Random()

private val testName = "QuickSort.Kotlin.Nullable"
private const val countOfElements = 1000


fun main(args: Array<String>) {
    val countOfElements = try {
        args[0].toInt()
    } catch (e: Exception) {
        countOfElements
    }

    val ascendRange = Array<Int?>(countOfElements) { it }
    val descendRange = Array<Int?>(countOfElements) { countOfElements - it - 1 }

    val points = ArrayList<LogPoint>()
    points.add(LogPoint(testName, START_TEST))
    sort(ascendRange)
    points.add(LogPoint(testName, ASCEND_CONST))
    sort(descendRange)
    points.add(LogPoint(testName, DESCEND_CONST))
    points.add(LogPoint(testName, COMPLETE_TEST))
    printPoints(points)
}
