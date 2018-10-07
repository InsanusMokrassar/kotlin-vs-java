package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.UseCases.DefaultInteractors

import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.UseCases.CRUDUseCases.ReadContactsUseCaseInput
import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.UseCases.CRUDUseCases.ReadContactsUseCaseOutput
import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.entities.Contact

class ReadContactsInteractor(
    private val readContactsUseCaseOutput: ReadContactsUseCaseOutput
) : ReadContactsUseCaseInput {
    override fun readContacts(): List<Contact> {
        return readContactsUseCaseOutput.getContacts()
    }
}