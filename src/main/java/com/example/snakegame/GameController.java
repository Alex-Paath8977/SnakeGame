package com.example.snakegame;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;


public class GameController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        //welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private Canvas gameCanvas;
    private GraphicsContext gc;
    private GameModel gameModel;

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public GameController(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    //Графический контекст для рисования
    @FXML
    public void initialize() {
        gc = gameCanvas.getGraphicsContext2D();
        drawBackground();
    }

    public void startGame() {
        gameModel.initGame();
    }

    //Отрисовка шахмантного фона
    private void drawBackground() {
        for (int i = 0; i < GameModel.ROWS; i++) {
            for (int j = 0; j < GameModel.COLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(javafx.scene.paint.Color.web("AAD751"));
                }
                else {
                    gc.setFill(Color.web("A2D149"));
                }
                gc.fillRect(i * GameModel.SQUARE_SIZE, j * GameModel.SQUARE_SIZE, GameModel.SQUARE_SIZE, GameModel.SQUARE_SIZE);
            }
        }
    }

    /*public void setupKeyControls(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode code = keyEvent.getCode();

                if(code == KeyCode.RIGHT || code == KeyCode.D) {
                    if (GameModel.currentDirection != LEFT) {
                        currentDirection = RIGHT;
                    }
                }
                    else if (code == KeyCode.LEFT || code == KeyCode.A) {
                        if (GameModel.currentDirection != RIGHT) {
                            currentDirection = LEFT;
                        }
                    }
                    else if (code == KeyCode.DOWN || KeyCode.S) {
                        if (GameModel.currentDirection != UP) {
                            currentDirection = DOWN;
                        }
                    }
                    else if (code == KeyCode.UP || code == KeyCode.W) {
                        if (GameModel.currentDirection != DOWN) {
                            currentDirection = UP;
                        }
                    }
                }
            });
    } **/
}