package com.example.snakegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class GameApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("Snake!");
        stage.setScene(scene);
        stage.show();


     //   GameController gameController = new GameController();
     //   gameController.setupKeyControls(scene);
    }


    public static void main(String[] args) {
        launch();
    }

}