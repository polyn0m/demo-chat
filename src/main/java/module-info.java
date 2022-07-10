module com.example.demochat {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.kordamp.ikonli.javafx;
    requires okhttp3;
    requires okhttp3.urlconnection;
    requires retrofit2;

    opens com.example.demochat to javafx.fxml;
    exports com.example.demochat;
}