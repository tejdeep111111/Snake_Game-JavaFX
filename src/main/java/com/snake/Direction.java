package com.snake;

public enum Direction {
    UP(0 ,-1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);

    private final int xOffset, yOffset;

    Direction(int x, int y) {
        this.xOffset = x;
        this.yOffset = y;
    }

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }
}
