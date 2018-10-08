package com.github.insanusmokrassar.KotlinVSJava.JavaComparisonModule.ContactBookJava;

import com.github.insanusmokrassar.KotlinVSJava.JavaComparisonModule.ContactBookJava.API.Contact;
import com.github.insanusmokrassar.KotlinVSJava.JavaComparisonModule.ContactBookJava.API.ContactsBook;
import com.github.insanusmokrassar.KotlinVSJava.report_formatter.LogPoint;

import java.util.ArrayList;
import java.util.List;

import static com.github.insanusmokrassar.KotlinVSJava.report_formatter.LogPointKt.*;
import static com.github.insanusmokrassar.KotlinVSJava.report_formatter.TestDataFormatterKt.printPoints;

public class ContactBook {
    private final static Integer defaultBooksCount = 10;

    private final static Integer defaultContactsPerBook = 5000;

    private final static String testName = "ContactsBook.Java";

    public static void main(String[] args) {
        Integer booksCount = defaultBooksCount;

        if (args.length > 0) {
            try {
                booksCount = Integer.valueOf(args[0]);
            } catch (NumberFormatException ignored) {}
        }

        Integer contactsPerBook = defaultContactsPerBook;

        if (args.length > 1) {
            try {
                contactsPerBook = Integer.valueOf(args[1]);
            } catch (NumberFormatException ignored) {}
        }

        List<LogPoint> points = new ArrayList<>();

        points.add(new LogPoint(testName, START_TEST));

        List<ContactsBook> books = new ArrayList<>();

        for (int i = 0; i < booksCount; i++) {
            ContactsBook book = new ContactsBook();

            for (int contactIndex = 0; contactIndex < contactsPerBook; contactIndex++) {
                final int index = (contactIndex * 2) - i;

                book.add(
                        new Contact(
                                "Name " + index,
                                "Last Name " + index,
                                (index + 100) + "",
                                index + "@main.hello"
                        )
                );
            }

            books.add(book);
        }

        points.add(new LogPoint(testName, CONTACTS_BOOKS_FILLED));

        if (!books.isEmpty()) {
            ContactsBook firstBook = books.get(0);

            for (int i = 0; i < firstBook.size(); i++) {
                Contact contact = firstBook.get(i);

                for (ContactsBook book : books) {

                    if (book != firstBook) {
                        if (book.indexOf(contact) > i) {
                            break;
                        }
                    }
                }
            }
        }

        points.add(new LogPoint(testName, CONTACTS_BOOKS_SEARCH_COMPLETED));
        points.add(new LogPoint(testName, COMPLETE_TEST));

        printPoints(points);
    }
}
