package ru.ssau.vyashka.labs.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

public class CalcApp extends Application {

    private static final String APP_ICON_PATH = "/ru/ssau/vyashka/labs/javafx/icon.png";
    private static final String MAIN_PAGE_PATH = "/ru/ssau/vyashka/labs/javafx/views/CalcUi.fxml";
    private static final String APP_TITLE = "Vyashka Calc App";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL xmlUrl = getClass().getResource(MAIN_PAGE_PATH);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(xmlUrl);

        Parent root = loader.load();
        URL iconUrl = getClass().getResource(APP_ICON_PATH);
        if (iconUrl != null)
            primaryStage.getIcons().add(new Image(iconUrl.toExternalForm()));
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
