package com.khantzen.mowersimulator.model;


public class Mower {
    private char orientation;
    private Coordinates coordinates;

    private final String instructionSequence;

    private Mower(Builder builder) {
        this.orientation = builder.orientation;
        this.coordinates = builder.coordinates;
        this.instructionSequence = builder.instructionSequence;
    }

    public char getOrientation() {
        return this.orientation;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }

    public String getInstructionSequence() {
        return this.instructionSequence;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public int getX() {
        return this.coordinates.getX();
    }

    public int getY() {
        return this.coordinates.getY();
    }

    @Override
    public String toString() {
        return this.getX() + " " + this.getY() + " " + this.orientation;
    }

    public static class Builder {
        private char orientation;
        private Coordinates coordinates;
        private String instructionSequence;

        public Builder orientation(char orientation) {
            this.orientation = orientation;
            return this;
        }

        public Builder coordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public Builder instructionSequence(String instructionSequence) {
            this.instructionSequence = instructionSequence;
            return this;
        }

        public Mower build() {
            return new Mower(this);
        }
    }
}
