package com.github.insanusmokrassar.KotlinVSJava.report_formatter

const val START_TEST = "start"
const val COMPLETE_TEST = "complete"

data class LogPoint(
    val testName: String,
    val message: String,
    val time: Long = System.currentTimeMillis()
) {
    // For Java
    constructor(testName: String, message: String) : this(testName, message, System.currentTimeMillis())
}
