module com.example.demochat {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.kordamp.ikonli.javafx;
    requires okhttp3;
    requires retrofit2;
    requires kotlinx.coroutines.core.jvm;

    opens com.example.demochat to javafx.fxml;
    exports com.example.demochat;
}