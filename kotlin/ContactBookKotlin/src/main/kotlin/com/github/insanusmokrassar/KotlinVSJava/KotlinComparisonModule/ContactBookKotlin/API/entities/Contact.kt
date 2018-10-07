package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.entities

typealias ContactId = Int

data class Contact(
    val infoItems: List<InfoItem> = emptyList(),
    val id: ContactId? = null
)
