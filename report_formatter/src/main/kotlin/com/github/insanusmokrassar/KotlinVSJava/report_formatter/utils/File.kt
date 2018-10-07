package com.github.insanusmokrassar.KotlinVSJava.report_formatter.utils

import java.io.File

fun File.getFolderFiles(): List<File> {
    return if (isDirectory) {
        listFiles().flatMap { it.getFolderFiles() }
    } else {
        listOf(this)
    }
}
