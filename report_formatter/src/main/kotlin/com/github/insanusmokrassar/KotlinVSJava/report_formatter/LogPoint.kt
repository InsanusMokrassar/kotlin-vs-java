package com.github.insanusmokrassar.KotlinVSJava.report_formatter

import org.joda.time.DateTime

const val START_TEST = "start"
const val COMPLETE_TEST = "complete"

data class LogPoint(
    val testName: String,
    val message: String,
    val dateTime: DateTime = DateTime.now()
)
