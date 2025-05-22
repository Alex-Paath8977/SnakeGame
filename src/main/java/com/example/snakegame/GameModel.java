package com.example.snakegame;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class GameModel {

    public static final int ROWS = 20;
    public static final int COLUMNS = ROWS;
    public static final int SQUARE_SIZE = 800/ROWS;

    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    public static final String[] FOODS_IMAGE = new String[]{
            "/resources/com/example/snakegame/image/ic_apple.png",
            "/resources/com/example/snakegame/image/ic_berry.png",
            "/resources/com/example/snakegame/image/ic_coconut.png",
            "/resources/com/example/snakegame/image/ic_orange.png",
            "/resources/com/example/snakegame/image/ic_peach.png",
            "/resources/com/example/snakegame/image/ic_pomegranate.ru",
            "/resources/com/example/snakegame/image/ic_tomato",
            "/resources/com/example/snakegame/image/ic_watermelon"};


    private List<Point> snakeBody = new ArrayList<>();
    private Point snakeHead;
    private Image foodImage;

    private int foodX;
    private int foodY;

    private boolean gameOver;

    public int currentDirection;

    private int score = 0;


    private void generateFood() {
        start:
        while (true) {
            foodX = (int) (Math.random() * ROWS);
            foodY = (int) (Math.random() * COLUMNS);

            for (Point snake : snakeBody) {
                if (snake.getX() == foodX && snake.getY() == foodY) {
                    continue start;
                }
            }
            foodImage = new Image(FOODS_IMAGE[(int) (Math.random() * FOODS_IMAGE.length)]);
            break;
        }
    }
}
