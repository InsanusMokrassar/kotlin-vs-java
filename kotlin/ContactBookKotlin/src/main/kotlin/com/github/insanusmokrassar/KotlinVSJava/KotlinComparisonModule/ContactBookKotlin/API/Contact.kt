package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API

private val nameRegex = Regex("^.+$")
private val lastNameRegex = Regex("^.*$")
private val phoneRegex = Regex("^\\+?\\d{2,}$")
private val emailRegex = Regex("^[^@]+@[^.]+\\..+$")

data class Contact(
    val name: String,
    val lastName: String,
    val phone: String? = null,
    val email: String? = null
) {
    val isValid: Boolean by lazy {
        nameRegex.matches(name).and(
            lastNameRegex.matches(lastName)
        ).and(
            phone ?.let {
                phoneRegex.matches(phone)
            } ?: true
        ).and(
            email ?.let {
                emailRegex.matches(it)
            } ?: true
        )
    }
}
