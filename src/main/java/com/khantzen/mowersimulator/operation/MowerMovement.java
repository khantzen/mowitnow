package com.khantzen.mowersimulator.operation;

import com.khantzen.mowersimulator.model.Mower;

public class MowerMovement {
    private Mower mower;

    private MowerMovement(Builder builder) {
        this.mower = builder.mower;
    }

    public void rotate(char direction) {
        char mowerOrientation = this.mower.getOrientation();
        char nextOrientation = getNextOrientation(direction, mowerOrientation);
        this.mower.setOrientation(nextOrientation);
    }

    private char getNextOrientation(char direction, char mowerOrientation) {
        String sortedOrientation = direction == 'D' ? "NESW" : "NWSE";

        int mowerOrientationIndex = sortedOrientation.indexOf(mowerOrientation);
        // If we exceed the sortedOrientation length then next orientation is at index 0
        int nextOrientationIndex = (mowerOrientationIndex + 1) % 4;

        return sortedOrientation.charAt(nextOrientationIndex);
    }

    public static class Builder {
        private Mower mower;

        public Builder mower(Mower mower) {
            this.mower = mower;
            return this;
        }

        public MowerMovement build() {
            return new MowerMovement(this);
        }
    }
}
