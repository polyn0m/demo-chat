package com.example.demochat.controllers

import com.example.demochat.DemoChat
import com.example.demochat.services.ChatServer
import com.example.demochat.cells.ContactCell
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.control.TextArea
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainController(
    private val application: DemoChat,
    private val chatServer: ChatServer
) : CoroutineScope {
    override val coroutineContext = Dispatchers.Main

    lateinit var scene: Scene

    @FXML
    private lateinit var currentUser: Label

    @FXML
    private lateinit var listContacts: ListView<ContactCell>

    @FXML
    private lateinit var listMessages: ListView<Any>

    @FXML
    private lateinit var textForSend: TextArea

    @FXML
    fun initialize() {
        loadChatList()
    }

    fun load() {
        if (!this::scene.isInitialized) {
            val fxmlLoader = FXMLLoader(AuthController::class.java.getResource(fxmlPath))
            fxmlLoader.setController(this)

            scene = Scene(fxmlLoader.load())
        }
    }

    @FXML
    private fun onSendMessageButtonClick() {

    }

    private fun loadChatList() = launch {

    }

    companion object {
        private const val fxmlPath = "/layouts/main-layout.fxml"
    }
}