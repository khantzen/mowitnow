package com.khantzen.mowersimulator.model;

import java.util.List;

public class SimulatorEntry {
    private final Coordinates yardRightTopCorner;
    private List<Mower> mowerList;

    private SimulatorEntry(Builder builder) {
        this.yardRightTopCorner = builder.yardRightTopCorner;
        this.mowerList = builder.mowerList;
    }

    public Coordinates getYardRightTopCorner() {
        return this.yardRightTopCorner;
    }

    public int getYardXRightTopCorner() {
        return this.yardRightTopCorner.getX();
    }

    public int getYardYRightTopCorner() {
        return this.yardRightTopCorner.getY();
    }

    public Mower getMowerAtIndex(int index) {
        return this.mowerList.get(index);
    }

    public int getMowerCount() {
        return this.mowerList.size();
    }

    public static class Builder {
        Coordinates yardRightTopCorner;
        private List<Mower> mowerList;

        public Builder yardRightTopCorner(Coordinates yardRightTopCorner) {
            this.yardRightTopCorner = yardRightTopCorner;
            return this;
        }

        public Builder mowerList(List<Mower> mowerList) {
            this.mowerList = mowerList;
            return this;
        }

        public SimulatorEntry build() {
            return new SimulatorEntry(this);
        }
    }
}
