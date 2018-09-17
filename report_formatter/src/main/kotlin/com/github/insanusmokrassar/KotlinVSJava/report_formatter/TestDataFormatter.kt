package com.github.insanusmokrassar.KotlinVSJava.report_formatter

fun printPoints(points: List<LogPoint>) {
    val sortedPoints = points.sortedBy { it.dateTime }
    var previousPoint: LogPoint? = null
    sortedPoints.forEach {
        point ->
        previousPoint ?.also {
            previousPoint ->
            println("${point.testName}:${point.dateTime.minus(previousPoint.dateTime.millis).millis} ms:${point.message}")
        } ?: point.also {
            println("first:${point.testName}:${point.dateTime}:${point.message}")
        }
        previousPoint = point
    }
}
