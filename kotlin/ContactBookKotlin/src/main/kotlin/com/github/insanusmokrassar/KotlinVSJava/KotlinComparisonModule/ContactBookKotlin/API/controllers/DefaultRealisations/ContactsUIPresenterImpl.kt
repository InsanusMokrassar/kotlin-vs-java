package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.controllers.DefaultRealisations

import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.UseCases.CRUDUseCases.*
import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.controllers.ContactsUIPresenterInput
import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.controllers.ContactsUIPresenterOutput
import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.entities.*

class ContactsUIPresenterImpl(
    private val createContactUseCaseInput: CreateContactUseCaseInput,
    private val readContactUseCaseInput: ReadContactsUseCaseInput,
    private val updateContactUseCaseInput: UpdateContactUseCaseInput,
    private val deleteContactUseCaseInput: DeleteContactUseCaseInput,
    private val contactsUIPresenterOutput: ContactsUIPresenterOutput
) : ContactsUIPresenterInput {
    override fun getContacts(): List<Contact> {
        return readContactUseCaseInput.readContacts().also {
            contactsUIPresenterOutput.contactsRead(it)
        }
    }

    override fun createContact(): Contact {
        return createContactUseCaseInput.createContact(Contact()).also {
            contactsUIPresenterOutput.contactCreated(it)
        }
    }

    override fun editContact(contact: Contact, contactInfoItem: InfoItem?): Contact? {
        return updateContactUseCaseInput.updateContact(
            Contact(
                contactInfoItem ?.let { contact.infoItems + it } ?: contact.infoItems,
                contact.id
            )
        ) ?.also {
            contactsUIPresenterOutput.contactDataUpdated(it)
        }
    }

    override fun removeContact(id: ContactId): Boolean {
        return deleteContactUseCaseInput.deleteContact(id)
    }
}