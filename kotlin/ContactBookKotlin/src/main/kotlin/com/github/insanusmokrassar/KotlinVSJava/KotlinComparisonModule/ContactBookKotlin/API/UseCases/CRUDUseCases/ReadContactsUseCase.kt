package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.UseCases.CRUDUseCases

import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.entities.Contact

interface ReadContactsUseCaseInput {
    fun readContacts(): List<Contact>
}

interface ReadContactsUseCaseOutput {
    fun getContacts(): List<Contact>
}
