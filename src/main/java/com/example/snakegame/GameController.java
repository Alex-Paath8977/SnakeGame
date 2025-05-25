package com.example.snakegame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.geometry.Point2D;

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
    private Timeline timeline;

    //Графический контекст для рисования
    @FXML
    public void initialize(GameModel gameModel) {
        this.gameModel = gameModel;
        this.gc = gameCanvas.getGraphicsContext2D();
        setupKeyListeners();
    }

    private void setupKeyListeners() {
        gameCanvas.setFocusTraversable(true);
        gameCanvas.setOnKeyPressed(this::handleKeyPress);
    }

    @FXML
    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case RIGHT, D -> gameModel.setDirectionIfNotOpposite(Direction.RIGHT);
            case LEFT, A -> gameModel.setDirectionIfNotOpposite(Direction.LEFT);
            case UP, W -> gameModel.setDirectionIfNotOpposite(Direction.UP);
            case DOWN, S -> gameModel.setDirectionIfNotOpposite(Direction.DOWN);
        }
    }

    public void startGame() {
        timeline = new Timeline(new KeyFrame(Duration.millis(130), e -> update()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void update() {
        if (gameModel.isGameOver()) {
            drawGameOver();
            timeline.stop();
            return;
        }
        gameModel.update(); // Обновляем состояние игры
        drawGame();     // Перерисовываем
    }

    private void drawGame() {
        drawBackground();
        drawFood();
        drawSnake();
        drawScore();
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

    private void drawFood() {
        gc.drawImage(gameModel.getFoodImage(),
                gameModel.getFoodX() * gameModel.getSquareSize(),
                gameModel.getFoodY() * gameModel.getSquareSize(),
                gameModel.getSquareSize(), gameModel.getSquareSize());
    }

    private void drawSnake() {
        gc.setFill(Color.web("4674E9"));
        Point2D head = gameModel.getSnakeHead();
        gc.fillRoundRect(head.getX() * gameModel.getSquareSize(),
                head.getY() * gameModel.getSquareSize(),
                gameModel.getSquareSize() - 1,
                gameModel.getSquareSize() - 1, 35, 35);

        for (int i = 1; i < gameModel.getSnakeBody().size(); i++) {
            Point2D body = gameModel.getSnakeBody().get(i);
            gc.fillRoundRect(body.getX() * gameModel.getSquareSize(),
                    body.getY() * gameModel.getSquareSize(),
                    gameModel.getSquareSize() - 1,
                    gameModel.getSquareSize() - 1, 20, 20);
        }
    }

    private void drawScore() {
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Digital-7", 35));
        gc.fillText("Счет: " + gameModel.getScore(), 10, 35);
    }

    private void drawGameOver() {
        gc.setFill(Color.RED);
        gc.setFont(new Font("Digital-7", 70));
        gc.fillText("Игра Окончена", gameModel.getWidth() / 3.5, gameModel.getHeight() / 2);
    }

}