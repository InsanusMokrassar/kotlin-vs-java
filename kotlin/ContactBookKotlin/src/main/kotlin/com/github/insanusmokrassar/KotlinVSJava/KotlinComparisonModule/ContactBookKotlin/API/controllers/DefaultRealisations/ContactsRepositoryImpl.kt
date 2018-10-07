package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.controllers.DefaultRealisations

import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.controllers.ContactsRepositoryInput
import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.controllers.ContactsRepositoryOutput
import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.entities.Contact
import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.entities.ContactId

class ContactsRepositoryImpl(
    private val contactsRepositoryOutput: ContactsRepositoryOutput
) : ContactsRepositoryInput {
    override fun addContact(contact: Contact): Contact {
        return contactsRepositoryOutput.addContact(contact)
    }

    override fun getContacts(): List<Contact> {
        return contactsRepositoryOutput.readContacts()
    }

    override fun changeContact(contact: Contact): Contact? {
        return contactsRepositoryOutput.updateContact(contact)
    }

    override fun deleteContact(id: ContactId): Boolean {
        return contactsRepositoryOutput.deleteContact(id)
    }
}