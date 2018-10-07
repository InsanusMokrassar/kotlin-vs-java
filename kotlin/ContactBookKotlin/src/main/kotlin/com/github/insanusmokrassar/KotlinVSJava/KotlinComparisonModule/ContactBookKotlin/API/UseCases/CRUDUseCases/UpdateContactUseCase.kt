package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.UseCases.CRUDUseCases

import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.entities.Contact

interface UpdateContactUseCaseInput {
    fun updateContact(contact: Contact): Contact?
}

interface UpdateContactUseCaseOutput {
    fun changeContact(contact: Contact): Contact?
}
