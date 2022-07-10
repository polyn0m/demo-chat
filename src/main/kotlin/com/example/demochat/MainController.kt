package com.example.demochat

import com.example.demochat.cells.ContactCell
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.ListView

class MainController {
    @FXML
    private lateinit var currentUser: Label

    @FXML
    private lateinit var listContacts: ListView<ContactCell>

    @FXML
    fun initialize() {
        currentUser.text = "543"
    }

    @FXML
    private fun onSendMessageButtonClick() {

    }
}