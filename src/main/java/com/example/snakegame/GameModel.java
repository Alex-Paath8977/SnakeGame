package com.example.snakegame;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

public class GameModel {

    private final int width;
    private final int height;

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
            "/resources/com/example/snakegame/image/ic_pomegranate.png",
            "/resources/com/example/snakegame/image/ic_tomato.png",
            "/resources/com/example/snakegame/image/ic_watermelon.png"};


    private List<Point2D> snakeBody = new ArrayList<>();
    private Point2D snakeHead;
    private Image foodImage;

    private int foodX;
    private int foodY;

    private boolean gameOver;
    public int currentDirection;
    private int score = 0;

    public GameModel(int width, int height) {
        this.width = width;
        this.height = height;
        this.currentDirection = RIGHT; // Добавьте эту строку

        initSnake();
        generateFood();
    }

    public void initSnake() {
        for (int i = 0; i < 3; i++) {
            snakeBody.add(new Point2D(5, ROWS / 2));
        }
        snakeHead = snakeBody.get(0);
    }

    private void generateFood() {
        start:
        while (true) {
            foodX = (int) (Math.random() * ROWS);
            foodY = (int) (Math.random() * COLUMNS);

            for (Point2D snake : snakeBody) {
                if (snake.getX() == foodX && snake.getY() == foodY) {
                    continue start;
                }
            }
            foodImage = new Image(FOODS_IMAGE[(int) (Math.random() * FOODS_IMAGE.length)]);
            break;
        }
    }

    public void moveSnake() {
        for (int i = snakeBody.size() - 1; i >= 1; i--) {
            Point2D prev = snakeBody.get(i - 1);
            snakeBody.set(i, new Point2D(prev.getX(), prev.getY()));
        }

        switch (currentDirection) {
            case 0: // RIGHT
                snakeHead = new Point2D(snakeHead.getX() + 1, snakeHead.getY());
                break;
            case 1: // LEFT
                snakeHead = new Point2D(snakeHead.getX() - 1, snakeHead.getY());
                break;
            case 2: // UP
                snakeHead = new Point2D(snakeHead.getX(), snakeHead.getY() - 1);
                break;
            case 3: // DOWN
                snakeHead = new Point2D(snakeHead.getX(), snakeHead.getY() + 1);
                break;
        }
        snakeBody.set(0, snakeHead); // Обновляем голову в списке
    }

    public void checkGameOver() {
        // Проверка выхода за границы
        if (snakeHead.getX() < 0 || snakeHead.getY() < 0 ||
                snakeHead.getX() >= ROWS || snakeHead.getY() >= COLUMNS) {
            gameOver = true;
        }

        // Проверка столкновения с собой
        for (int i = 1; i < snakeBody.size(); i++) {
            if (snakeHead.equals(snakeBody.get(i))) {
                gameOver = true;
                break;
            }
        }
    }

    public void setDirectionIfNotOpposite(Direction newDirection) {
        if (!Direction.values()[currentDirection].isOpposite(newDirection)) {
            currentDirection = newDirection.ordinal();
        }
    }

    public void checkFood() {
        if (snakeHead.getX() == foodX && snakeHead.getY() == foodY) {
            // Добавляем новый сегмент в конец змейки
            Point2D lastSegment = snakeBody.get(snakeBody.size() - 1);
            snakeBody.add(new Point2D(lastSegment.getX(), lastSegment.getY()));
            generateFood();
            score += 5;
        }
    }

    public int getSquareSize() {
        return SQUARE_SIZE;
    }

    public int getRows() {
        return ROWS;
    }

    public int getColumns() {
        return COLUMNS;
    }

    public List<Point2D> getSnakeBody() {
        return snakeBody;
    }

    public Point2D getSnakeHead() {
        return snakeHead;
    }

    public Image getFoodImage() {
        return foodImage;
    }

    public int getFoodX() {
        return foodX;
    }

    public int getFoodY() {
        return foodY;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(int direction) {
        this.currentDirection = direction;
    }

    public int getScore() {
        return score;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void update() {
        moveSnake();
        checkFood();
        checkGameOver();
    }

}
