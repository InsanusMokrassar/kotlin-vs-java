package com.github.insanusmokrassar.KotlinVSJava.report_formatter

fun printPoints(points: List<LogPoint>) {
    val sortedPoints = points.sortedBy { it.time }
    var previousPoint: LogPoint? = null
    sortedPoints.forEach {
        point ->
        previousPoint ?.also {
            previousPoint ->
            println("${point.testName}:${point.time - previousPoint.time}:${point.message}")
        } ?: point.also {
            println("${point.testName}:0:${point.message}")
        }
        previousPoint = point
    }
}
