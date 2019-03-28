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

    val books = ArrayList<ContactsBook>()

    for (bookIndex in (0 until booksCount)) {
        ContactsBook().apply {
            for (contactIndex in (0 until contactsPerBook)) {
                val index = (contactIndex * 2) - bookIndex

                add(
                    Contact(
                        "Name $index",
                        "Last Name $index",
                        "${index + 100}",
                        "$index@mail.hello"
                    )
                )
            }
        }.also {
            books.add(it)
        }
    }

    points.add(LogPoint(testName, CONTACTS_BOOKS_FILLED))
    for (mainBook in books) {
        for ((i, contact) in mainBook.withIndex()) {
            val searchIndex = i * 2
            for (book in books) {

                if (book != mainBook) {
                    if (book.indexOf(contact) > searchIndex) {
                        break
                    }
                }

            }
        }
    }
    points.add(LogPoint(testName, CONTACTS_BOOKS_SEARCH_COMPLETED))
    points.add(LogPoint(testName, COMPLETE_TEST))

    printPoints(points)
}
