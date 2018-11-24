package com.khantzen.mowersimulator.model;


public class Mower {
    private char orientation;
    private int x;
    private int y;

    private Mower(Builder builder) {
        this.orientation = builder.orientation;
        this.x = builder.x;
        this.y = builder.y;
    }

    public char getOrientation() {
        return orientation;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public static class Builder {
        private char orientation;
        private int y;
        private int x;

        public Builder orientation(char orientation) {
            this.orientation = orientation;
            return this;
        }

        public Builder x(int x) {
            this.x = x;
            return this;
        }

        public Builder y(int y) {
            this.y = y;
            return this;
        }

        public Mower build() {
            return new Mower(this);
        }
    }
}
