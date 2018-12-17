package com.github.insanusmokrassar.KotlinVSJava.report_formatter

const val START_TEST = "start"
const val COMPLETE_TEST = "complete"

const val ASCEND_CONST = "ascend"
const val DESCEND_CONST = "descend"

const val START_HANDLE_MESSAGE_TEMPLATE = "start_handle_%s"
const val COMPLETE_HANDLE_MESSAGE_TEMPLATE = "complete_handle_%s"

const val CONTACTS_BOOKS_FILLED = "contacts_books_filled"
const val CONTACTS_BOOKS_SEARCH_COMPLETED = "contacts_books_search_completed"

const val REQUESTS_FILLED = "requests_filled"
const val ALL_TASKS_CREATED = "all_tasks_created"
const val ALL_TASKS_COMPLETED = "all_tasks_completed"

data class LogPoint(
    val testName: String,
    val message: String,
    val time: Long = System.currentTimeMillis()
) {
    // For Java
    constructor(testName: String, message: String) : this(testName, message, System.currentTimeMillis())
}
