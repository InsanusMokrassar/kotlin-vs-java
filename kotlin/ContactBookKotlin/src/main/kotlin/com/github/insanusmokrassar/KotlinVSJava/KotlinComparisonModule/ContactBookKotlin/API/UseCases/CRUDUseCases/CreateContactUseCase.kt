package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.UseCases.CRUDUseCases

import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.entities.Contact

interface CreateContactUseCaseInput {
    fun createContact(contact: Contact): Contact
}

interface CreateContactUseCaseOutput {
    fun addContact(contact: Contact): Contact
}
