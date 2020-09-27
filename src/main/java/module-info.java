module com.teyson {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.teyson.presentation to javafx.fxml;
    exports com.teyson.presentation;

    opens com.teyson.domain to com.google.gson;
}