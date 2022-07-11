package com.example.demochat.cells

import com.example.demochat.controllers.AuthController
import com.example.demochat.models.views.ContactModel
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Label
import javafx.scene.control.ListCell
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox


class ContactCell : ListCell<ContactModel>() {
    private var cellContainer: VBox

    @FXML
    private lateinit var titleContainer: HBox

    @FXML
    private lateinit var  titleLabel: Label

    @FXML
    private lateinit var  titleCounterLabel: Label

    @FXML
    private lateinit var  subTitleLabel: Label

    init {
        val fxmlLoader = FXMLLoader(AuthController::class.java.getResource(fxmlPath))
        fxmlLoader.setController(this)

        cellContainer = fxmlLoader.load()
    }

    override fun updateItem(item: ContactModel?, empty: Boolean) {
        super.updateItem(item, empty)

        if (empty || item == null) {
            text = ""
            graphic = null
        } else {
            titleLabel.text = item.name
            subTitleLabel.text = item.lastMessageText

            graphic = cellContainer
        }
    }

    companion object {
        private const val fxmlPath = "/views/contact-cell.fxml"
    }
}