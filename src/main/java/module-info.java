module com.example.demochat {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.kordamp.ikonli.javafx;

    opens com.example.demochat to javafx.fxml;
    exports com.example.demochat;
}