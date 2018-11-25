package com.khantzen.mowersimulator.operation;

import com.khantzen.mowersimulator.model.Mower;

class MowerMovement {
    private Mower mower;
    private final int yardXRightTopCorner;
    private final int yardYRightTopCorner;

    private MowerMovement(Builder builder) {
        this.mower = builder.mower;
        this.yardXRightTopCorner = builder.yardXTopCorner;
        this.yardYRightTopCorner = builder.yardYTopCorner;
    }

    void moveForward() {
        int nextX = getXAfterMoveForward();
        int nextY = getYAfterMoveForward();

        this.mower.setX(nextX);
        this.mower.setY(nextY);
    }

    private int getYAfterMoveForward() {
        char mowerOrientation = this.mower.getOrientation();
        int y = this.mower.getY();

        if (mowerOrientation == 'E' || mowerOrientation == 'W') {
            return y;
        }

        int nextY = y + (mowerOrientation == 'N' ? +1 : -1);

        if (nextY < 0 || nextY > yardYRightTopCorner) {
            nextY = y;
        }

        return nextY;
    }

    private int getXAfterMoveForward() {
        char mowerOrientation = this.mower.getOrientation();
        int x = this.mower.getX();

        if (mowerOrientation == 'N' || mowerOrientation == 'S') {
            return x;
        }

        int nextX = x + (mowerOrientation == 'E' ? +1 : -1);

        if (nextX < 0 || nextX > yardXRightTopCorner) {
            nextX = x;
        }

        return nextX;
    }

    void rotate(char direction) {
        char mowerOrientation = this.mower.getOrientation();
        char nextOrientation = getNextOrientation(direction, mowerOrientation);
        this.mower.setOrientation(nextOrientation);
    }

    private char getNextOrientation(char direction, char mowerOrientation) {
        String sortedOrientation = direction == 'D' ? "NESW" : "NWSE";

        int mowerOrientationIndex = sortedOrientation.indexOf(mowerOrientation);
        // Add one to current mowerOrientationIndex to get next one
        // If we exceed the sortedOrientation length then next orientation is at index 0
        int nextOrientationIndex = (mowerOrientationIndex + 1) % 4;

        return sortedOrientation.charAt(nextOrientationIndex);
    }

    static class Builder {
        private Mower mower;
        private int yardXTopCorner;
        private int yardYTopCorner;

        Builder mower(Mower mower) {
            this.mower = mower;
            return this;
        }

        Builder yardXTopCorner(int yardXTopCorner) {
            this.yardXTopCorner = yardXTopCorner;
            return this;
        }

        Builder yardYTopCorner(int yardYTopCorner) {
            this.yardYTopCorner = yardYTopCorner;
            return this;
        }

        MowerMovement build() {
            return new MowerMovement(this);
        }
    }
}
