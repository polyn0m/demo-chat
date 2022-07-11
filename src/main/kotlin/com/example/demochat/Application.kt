package com.example.demochat

import com.example.demochat.controllers.AuthController
import com.example.demochat.controllers.MainController
import com.example.demochat.models.views.UserModel
import com.example.demochat.services.ChatServer
import javafx.application.Application
import javafx.stage.Stage
import kotlin.system.exitProcess

class DemoChat : Application() {
    private val chatServer = ChatServer()

    private var currentStage: Stage? = null

    private val authController = AuthController(this, chatServer)
    private val mainController = MainController(this, chatServer)

    override fun start(stage: Stage) {
        showAuthScene()
    }

    fun showAuthScene() {
        currentStage?.close()

        currentStage = Stage()

        currentStage?.title = "Study\\chat"
        currentStage?.minWidth = 580.0
        currentStage?.minHeight = 500.0
        currentStage?.setOnCloseRequest {
            exitProcess(0)
        }

        authController.load()

        currentStage?.scene = authController.scene

        currentStage?.show()
    }

    fun showMainScene(user: UserModel) {
        currentStage?.close()

        currentStage = Stage()

        currentStage?.title = "Study\\chat"
        currentStage?.minWidth = 1024.0
        currentStage?.minHeight = 600.0
        currentStage?.setOnCloseRequest {
            chatServer.disconnect()

            showAuthScene()
        }

        mainController.load(user)

        currentStage?.scene = mainController.scene

        currentStage?.show()
    }
}

fun main() {
    Application.launch(DemoChat::class.java)
}