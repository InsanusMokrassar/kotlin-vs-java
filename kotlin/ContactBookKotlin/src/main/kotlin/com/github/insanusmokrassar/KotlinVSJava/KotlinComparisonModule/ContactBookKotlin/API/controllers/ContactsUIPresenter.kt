package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.controllers

import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.entities.*

interface ContactsUIPresenterInput {
    fun getContacts(): List<Contact>

    fun createContact(): Contact
    fun editContact(contact: Contact, contactInfoItem: InfoItem?): Contact?
    fun removeContact(id: ContactId): Boolean
}

interface ContactsUIPresenterOutput {

    fun contactCreated(contact: Contact)
    fun contactsRead(contacts: List<Contact>)
    fun contactDataUpdated(contact: Contact)
    fun contactRemoved(contact: Contact)

}