package com.khantzen.mowersimulator.operation;

import com.khantzen.mowersimulator.model.Mower;

public class MowerMovement {
    private Mower mower;
    private final int yardXTopCorner;
    private final int yardYTopCorner;

    private MowerMovement(Builder builder) {
        this.mower = builder.mower;
        this.yardXTopCorner = builder.yardXTopCorner;
        this.yardYTopCorner = builder.yardYTopCorner;
    }

    public void moveForward() {
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

        if (nextY < 0 || nextY > yardYTopCorner) {
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

        if (nextX < 0 || nextX > yardXTopCorner) {
            nextX = x;
        }

        return nextX;
    }


    public void rotate(char direction) {
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

    public static class Builder {
        private Mower mower;
        private int yardXTopCorner;
        private int yardYTopCorner;

        public Builder mower(Mower mower) {
            this.mower = mower;
            return this;
        }

        public Builder yardXTopCorner(int yardXTopCorner) {
            this.yardXTopCorner = yardXTopCorner;
            return this;
        }

        public Builder yardYTopCorner(int yardYTopCorner) {
            this.yardYTopCorner = yardYTopCorner;
            return this;
        }

        public MowerMovement build() {
            return new MowerMovement(this);
        }
    }
}
