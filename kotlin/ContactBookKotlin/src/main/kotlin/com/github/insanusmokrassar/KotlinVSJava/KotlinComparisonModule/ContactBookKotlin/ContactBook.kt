package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin

import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.Contact
import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.ContactsBook
import com.github.insanusmokrassar.KotlinVSJava.report_formatter.*

private const val defaultBooksCount = 10

private const val defaultContactsPerBook = 5000

private const val testName = "ContactsBook.Kotlin"

fun main(args: Array<String>) {
    val booksCount = if (args.isNotEmpty()) {
        args[0].toIntOrNull() ?: defaultBooksCount
    } else {
        defaultBooksCount
    }
    val contactsPerBook = if (args.size > 1) {
        args[1].toIntOrNull() ?: defaultContactsPerBook
    } else {
        defaultContactsPerBook
    }

    val points = ArrayList<LogPoint>()

    points.add(LogPoint(testName, START_TEST))

    (0 until booksCount).map {
        val contactBook = ContactsBook()

        for (i in (0 until contactsPerBook)) {
            val index = (i * 2) - it
            contactBook.add(
                Contact(
                    "Name $index",
                    "Last Name $index",
                    "${index + 100}",
                    "$index@mail.hello"
                )
            )
        }

        contactBook
    }.let {
        books ->
        points.add(LogPoint(testName, CONTACTS_BOOKS_FILLED))
        books.firstOrNull() ?.let {
            mainBook ->
            mainBook.forEachIndexed {
                i, contact ->
                for (book in books) {
                    if (book != mainBook) {
                        if (book.indexOf(contact) > i) {
                            break
                        }
                    }
                }
            }
        }
    }
    points.add(LogPoint(testName, CONTACTS_BOOKS_SEARCH_COMPLETED))
    points.add(LogPoint(testName, COMPLETE_TEST))

    printPoints(points)
}
