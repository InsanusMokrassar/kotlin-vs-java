package com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.external

import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.controllers.ContactsUIPresenterInput
import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.controllers.ContactsUIPresenterOutput
import com.github.insanusmokrassar.KotlinVSJava.KotlinComparisonModule.ContactBookKotlin.API.entities.*

private const val tabSymbol = "    "

private fun Contact.prepareToOutput(): String {
    return infoItems.joinToString {
        infoItem ->
        "$tabSymbol${infoItem.type.name}: ${infoItem.value}"
    }.also {
        infoItemsString ->
        "${tabSymbol}Id: $id\n$infoItemsString"
    }
}

class ContactsConsoleUIPresenter(
    private val contactsUIPresenterInput: ContactsUIPresenterInput
) : ContactsUIPresenterOutput {
    override fun contactCreated(contact: Contact) {
        println("Contact created")
    }

    override fun contactsRead(contacts: List<Contact>) {
        contacts.joinToString("\n\n") {
            it.prepareToOutput()
        }.also {
            println("Contacts:\n$it")
        }
    }

    override fun contactDataUpdated(contact: Contact) {
        println("Contact updated:\n${contact.prepareToOutput()}")
    }

    override fun contactRemoved(contact: Contact) {
        println("Contact removed")
    }

    fun applyCommand(consoleCommand: ConsoleCommand) {
        when (consoleCommand) {
            AddCommand -> contactsUIPresenterInput.createContact()
            ReadCommand -> contactsUIPresenterInput.getContacts()
            is UpdateCommand -> contactsUIPresenterInput.editContact(consoleCommand.contact, consoleCommand.infoItem)
            is DeleteCommand -> contactsUIPresenterInput.removeContact(consoleCommand.id)
        }
    }
}

sealed class ConsoleCommand

object AddCommand : ConsoleCommand()
object ReadCommand : ConsoleCommand()
class UpdateCommand(
    val contact: Contact,
    val infoItem: InfoItem?
) : ConsoleCommand()
class DeleteCommand(
    val id: ContactId
) : ConsoleCommand()
