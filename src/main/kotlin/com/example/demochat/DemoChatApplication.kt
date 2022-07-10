package com.example.demochat

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class DemoChat : Application() {
    override fun start(stage: Stage) {
        stage.minWidth = 800.0
        stage.minHeight = 600.0

        val fxmlLoader = FXMLLoader(DemoChat::class.java.getResource("main-layout.fxml"))
        val scene = Scene(fxmlLoader.load())

        stage.title = "Demo chat"
        stage.scene = scene

        stage.show()
    }
}

fun main() {
    Application.launch(DemoChat::class.java)
}