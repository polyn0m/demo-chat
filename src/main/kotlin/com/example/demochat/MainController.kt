package com.example.demochat

import com.example.demochat.cells.ContactCell
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.control.TextArea
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainController : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val serverApi = ServerApi()

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
        loadCurrentUser()
    }

    @FXML
    private fun onSendMessageButtonClick() {

    }

    private fun loadCurrentUser() = launch {
        serverApi.api.getWho()
    }
}