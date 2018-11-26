package com.khantzen.mowersimulator.operation;

import com.khantzen.mowersimulator.model.Coordinates;

class Movement {
    private final int yardXRightTopCorner;
    private final int yardYRightTopCorner;

    private Movement(Builder builder) {
        this.yardXRightTopCorner = builder.yardXTopCorner;
        this.yardYRightTopCorner = builder.yardYTopCorner;
    }

    Coordinates getCoordinatesAfterMoveForward(Coordinates coordinates, char orientation) {
        int nextX = this.getXAfterMoveForward(coordinates.getX(), orientation);
        int nextY = this.getYAfterMoveForward(coordinates.getY(), orientation);

        return new Coordinates(nextX, nextY);
    }

    private int getYAfterMoveForward(int y, char mowerOrientation) {
        if (mowerOrientation == 'E' || mowerOrientation == 'W') {
            return y;
        }

        int nextY = y + (mowerOrientation == 'N' ? +1 : -1);

        if (nextY < 0 || nextY > this.yardYRightTopCorner) {
            nextY = y;
        }

        return nextY;
    }

    private int getXAfterMoveForward(int x, char mowerOrientation) {
        if (mowerOrientation == 'N' || mowerOrientation == 'S') {
            return x;
        }

        int nextX = x + (mowerOrientation == 'E' ? +1 : -1);

        if (nextX < 0 || nextX > this.yardXRightTopCorner) {
            nextX = x;
        }

        return nextX;
    }

    char getOrientationAfterRotation(char mowerOrientation, char direction) {
        String sortedOrientation = direction == 'D' ? "NESW" : "NWSE";

        int mowerOrientationIndex = sortedOrientation.indexOf(mowerOrientation);
        // Add one to current mowerOrientationIndex to get next one
        // If we exceed the sortedOrientation length then next orientation is at index 0
        int nextOrientationIndex = (mowerOrientationIndex + 1) % 4;

        return sortedOrientation.charAt(nextOrientationIndex);
    }

    static class Builder {
        private int yardXTopCorner;
        private int yardYTopCorner;

        Builder yardXTopCorner(int yardXTopCorner) {
            this.yardXTopCorner = yardXTopCorner;
            return this;
        }

        Builder yardYTopCorner(int yardYTopCorner) {
            this.yardYTopCorner = yardYTopCorner;
            return this;
        }

        Movement build() {
            return new Movement(this);
        }
    }
}
