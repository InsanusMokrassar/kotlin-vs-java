package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.UseCases.DefaultInteractors

import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.UseCases.CRUDUseCases.DeleteContactUseCaseInput
import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.UseCases.CRUDUseCases.DeleteContactUseCaseOutput
import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.entities.ContactId

class DeleteContactInteractor(
    private val deleteContactUseCaseOutput: DeleteContactUseCaseOutput
) : DeleteContactUseCaseInput {
    override fun deleteContact(id: ContactId): Boolean {
        return deleteContactUseCaseOutput.deleteContact(id)
    }
}