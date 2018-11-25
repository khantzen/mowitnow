package com.khantzen.mowersimulator.model;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Coordinates can actually be edited only from model package to respect Demeter Law
    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }
}
