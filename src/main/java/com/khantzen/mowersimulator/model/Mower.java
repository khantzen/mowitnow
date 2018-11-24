package com.khantzen.mowersimulator.model;


public class Mower {
    private char orientation;

    private Mower(Builder builder) {
        this.orientation = builder.orientation;
    }

    public char getOrientation() {
        return orientation;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }

    public static class Builder {
        private char orientation;

        public Builder orientation(char orientation) {
            this.orientation = orientation;
            return this;
        }

        public Mower build() {
            return new Mower(this);
        }
    }
}
