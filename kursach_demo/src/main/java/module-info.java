module com.example.kursach_demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kursach_demo to javafx.fxml;
    exports com.example.kursach_demo;
}