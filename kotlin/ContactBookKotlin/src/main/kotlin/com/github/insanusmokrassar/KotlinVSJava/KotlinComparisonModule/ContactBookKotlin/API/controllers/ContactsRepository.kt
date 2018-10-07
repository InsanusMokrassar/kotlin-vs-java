package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.controllers

import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.UseCases.CRUDUseCases.*
import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.entities.Contact
import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.entities.ContactId

interface ContactsRepositoryInput : CreateContactUseCaseOutput,
    ReadContactsUseCaseOutput,
    UpdateContactUseCaseOutput,
    DeleteContactUseCaseOutput

interface ContactsRepositoryOutput {
    fun addContact(contact: Contact): Contact
    fun readContacts(): List<Contact>
    fun updateContact(contact: Contact): Contact?
    fun deleteContact(id: ContactId): Boolean
}