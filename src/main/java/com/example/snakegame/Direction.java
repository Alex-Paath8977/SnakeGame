package com.example.snakegame;

public enum Direction {
    RIGHT, LEFT, UP, DOWN;

    public boolean isOpposite(Direction other) {
        return (this == RIGHT && other == LEFT) ||
                (this == LEFT && other == RIGHT) ||
                (this == UP && other == DOWN) ||
                (this == DOWN && other == UP);
    }
}
