module calculator_app {

    requires javafx.controls;
    requires javafx.fxml;

    exports ru.ssau.vyashka.labs.javafx;

    opens ru.ssau.vyashka.labs.javafx to javafx.fxml;
    opens ru.ssau.vyashka.labs.javafx.controller to javafx.fxml;

}