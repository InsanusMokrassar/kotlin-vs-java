package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.external

import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.controllers.ContactsRepositoryOutput
import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.entities.Contact
import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.entities.ContactId

class ContactsOutputRepository : ContactsRepositoryOutput {
    private val contacts = HashMap<ContactId, Contact>()

    override fun addContact(contact: Contact): Contact {
        val id = contacts.size
        return Contact(
            contact.infoItems,
            id
        ).also {
            contacts[id] = it
        }
    }

    override fun readContacts(): List<Contact> {
        return contacts.values.map { it.copy() }.toList()
    }

    override fun updateContact(contact: Contact): Contact? {
        return contact.id ?.let {
            id ->
            Contact(
                contact.infoItems,
                id
            ).also {
                contacts[id] = it
            }
        }
    }

    override fun deleteContact(id: ContactId): Boolean {
        return contacts.remove(id) != null
    }
}