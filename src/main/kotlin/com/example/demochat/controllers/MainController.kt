package com.example.demochat.controllers

import com.example.demochat.DemoChat
import com.example.demochat.services.ChatServer
import com.example.demochat.cells.ContactCell
import com.example.demochat.models.views.ContactModel
import com.example.demochat.models.views.UserModel
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.control.TextArea
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import java.net.URL
import java.util.*

class MainController(
    private val application: DemoChat,
    private val chatServer: ChatServer
) : Initializable, CoroutineScope {
    override val coroutineContext = Dispatchers.Main

    lateinit var scene: Scene

    private val contacts = FXCollections.observableArrayList<ContactModel>()

    @FXML
    private lateinit var currentUser: Label

    @FXML
    private lateinit var listContacts: ListView<ContactModel>

    @FXML
    private lateinit var listMessages: ListView<Any>

    @FXML
    private lateinit var textForSend: TextArea

    @FXML
    private lateinit var submitSendButton: Button

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        listContacts.items = contacts
        listContacts.setCellFactory {
            ContactCell()
        }

        submitSendButton.setOnAction {
            onSendMessageButtonClick()
        }

        loadChatList()
    }

    fun load(user: UserModel) {
        if (!this::scene.isInitialized) {
            val fxmlLoader = FXMLLoader(AuthController::class.java.getResource(fxmlPath))
            fxmlLoader.setController(this)

            scene = Scene(fxmlLoader.load())
        }

        currentUser.text = user.fullName
    }

    private fun onSendMessageButtonClick() {

    }

    private fun loadChatList() = launch {
        val contactsResponse = chatServer.httpApi.getContactsList()

        val loadedContacts = arrayListOf<ContactModel>()
        contactsResponse.chats.asFlow().collect {
            loadedContacts.add(
                ContactModel(
                    it.user.id,
                    "${it.user.surname} ${it.user.name} ${it.user.patronymic}".trim(),
                    it.lastMessage?.message ?: "",
                    unreadCount = it.unreadCount
                )
            )
        }
        contactsResponse.groupChats.asFlow().collect {
            loadedContacts.add(
                ContactModel(
                    it.id,
                    it.name.trim(),
                    it.lastMessage?.message ?: "",
                    true,
                    it.unreadCount
                )
            )
        }

        contacts.addAll(loadedContacts)
    }

    companion object {
        private const val fxmlPath = "/layouts/main-layout.fxml"
    }
}