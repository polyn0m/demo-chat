package com.example.demochat

import com.example.demochat.controllers.AuthController
import com.example.demochat.controllers.MainController
import com.example.demochat.services.ChatServer
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class DemoChat : Application() {
    private val chatServer = ChatServer()

    private var currentStage: Stage? = null

    private val authController = AuthController(this, chatServer)
    private val mainController = MainController(this, chatServer)

    override fun start(stage: Stage) {
        currentStage = stage

        stage.title = "Demo chat"

        showAuthScene()

        stage.show()
    }

    fun showAuthScene() {
        authController.load()

        currentStage?.scene = authController.scene

        currentStage?.minWidth = 580.0
        currentStage?.minHeight = 500.0
    }

    fun showMainScene() {
        mainController.load()

        currentStage?.scene = authController.scene
    }
}

fun main() {
    Application.launch(DemoChat::class.java)
}