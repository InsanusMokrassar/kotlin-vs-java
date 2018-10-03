package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.HelloWorldKotlin

import com.github.insanusmokrassar.KotlinVSJava.report_formatter.*

private const val testName: String = "HelloWorld.Kotlin"

fun main(args: Array<String>) {
    val points = ArrayList<LogPoint>()
    points.add(LogPoint(testName, START_TEST))
    println("Hello World!")
    points.add(LogPoint(testName, COMPLETE_TEST))
    printPoints(points)
}
