module com.example.demochat {
    requires java.sql;

    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.kordamp.ikonli.javafx;
    requires okhttp3;
    requires retrofit2;
    requires kotlinx.coroutines.core.jvm;
    requires retrofit2.converter.gson;
    requires gson;

    opens com.example.demochat.controllers to javafx.fxml;
    opens com.example.demochat.cells to javafx.fxml;
    opens com.example.demochat.models.responses to gson;

    exports com.example.demochat;
}