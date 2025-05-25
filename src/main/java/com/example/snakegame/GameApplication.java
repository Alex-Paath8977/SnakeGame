package com.example.snakegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class GameApplication extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("hello-view.fxml"));
        // Сначала загружаем FXML, чтобы создать контроллер
        Parent root = fxmlLoader.load();
        //Получаем конролер после загрузки FXML
        GameController controller = fxmlLoader.getController();

        // Инициализируем модель и передаём в контроллер
        GameModel gameModel = new GameModel(WIDTH, HEIGHT);
        controller.initialize(gameModel);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle("Snake!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        // Затем передаём модель в контроллер
        controller.startGame();
    }


    public static void main(String[] args) {
        launch();
    }

}