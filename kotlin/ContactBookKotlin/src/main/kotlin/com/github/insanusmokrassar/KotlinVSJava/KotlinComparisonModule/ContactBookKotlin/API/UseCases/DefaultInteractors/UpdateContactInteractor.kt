package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.UseCases.DefaultInteractors

import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.UseCases.CRUDUseCases.UpdateContactUseCaseInput
import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.UseCases.CRUDUseCases.UpdateContactUseCaseOutput
import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.entities.Contact

class UpdateContactInteractor(
    private val updateContactUseCaseOutput: UpdateContactUseCaseOutput
) : UpdateContactUseCaseInput {
    override fun updateContact(contact: Contact): Contact? {
        return updateContactUseCaseOutput.changeContact(contact)
    }
}