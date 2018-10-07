package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.UseCases.CRUDUseCases

import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.entities.ContactId

interface DeleteContactUseCaseInput {
    fun deleteContact(id: ContactId): Boolean
}

interface DeleteContactUseCaseOutput {
    fun deleteContact(id: ContactId): Boolean
}
