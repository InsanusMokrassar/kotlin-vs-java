package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.QuickSort

import com.github.insanusmokrassar.KotlinVSJava.report_formatter.*

import java.util.ArrayList
import java.util.Random

var random = Random()

private val testName = "QuickSort#Kotlin"
private const val countOfElements = 1000
private val ascendRange = Array(countOfElements) { it }
private val descendRange = Array(countOfElements) { countOfElements - it - 1 }

fun main(args: Array<String>) {
    val points = ArrayList<LogPoint>()
    points.add(LogPoint(testName, START_TEST))
    sort(ascendRange)
    points.add(LogPoint(testName, "Ascend sorted"))
    sort(descendRange)
    points.add(LogPoint(testName, "Descend sorted"))
    points.add(LogPoint(testName, COMPLETE_TEST))
    printPoints(points)
}
