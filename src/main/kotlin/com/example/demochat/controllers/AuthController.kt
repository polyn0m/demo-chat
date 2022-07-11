package com.example.demochat.controllers

import com.example.demochat.DemoChat
import com.example.demochat.models.views.CurrentUserModel
import com.example.demochat.services.ChatServer
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL
import java.util.*

class AuthController(
    private val application: DemoChat,
    private val chatServer: ChatServer
) : Initializable, CoroutineScope {

    override val coroutineContext = Dispatchers.Main

    lateinit var scene: Scene

    @FXML
    private lateinit var loginField: TextField
    @FXML
    private lateinit var passwordField: PasswordField
    @FXML
    private lateinit var buttonSubmit: Button

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        loginField.requestFocus()

        buttonSubmit.setOnAction {
            onSubmitButtonClick()
        }
    }

    fun load() {
        if (!this::scene.isInitialized) {
            val fxmlLoader = FXMLLoader(AuthController::class.java.getResource(fxmlPath))
            fxmlLoader.setController(this)

            scene = Scene(fxmlLoader.load())
        }
    }

    private fun onSubmitButtonClick() = launch {
        chatServer.login = loginField.text
        chatServer.password = passwordField.text

        try {
            val whoAmI = chatServer.httpApi.getWho()

            application.showMainScene(
                CurrentUserModel(whoAmI.id, whoAmI.name, whoAmI.surname, whoAmI.patronymic)
            )
        } catch (_: Exception) {

        }
    }

    companion object {
        private const val fxmlPath = "/layouts/auth-layout.fxml"
    }
}