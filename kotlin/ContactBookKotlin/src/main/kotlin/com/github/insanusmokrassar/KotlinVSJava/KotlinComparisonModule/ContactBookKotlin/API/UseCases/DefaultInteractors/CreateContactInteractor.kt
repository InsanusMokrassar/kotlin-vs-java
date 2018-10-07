package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.UseCases.DefaultInteractors

import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.UseCases.CRUDUseCases.CreateContactUseCaseInput
import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.UseCases.CRUDUseCases.CreateContactUseCaseOutput
import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.entities.Contact

class CreateContactInteractor(
    private val createContactUseCaseOutput: CreateContactUseCaseOutput
) : CreateContactUseCaseInput {
    override fun createContact(contact: Contact): Contact {
        return createContactUseCaseOutput.addContact(contact)
    }
}