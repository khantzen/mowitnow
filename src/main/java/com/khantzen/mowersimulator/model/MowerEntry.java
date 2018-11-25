package com.khantzen.mowersimulator.model;

import java.util.List;

public class MowerEntry {
    private final Coordinates yardRightTopCorner;
    private List<Mower> mowerList;

    public MowerEntry(Builder builder) {
        this.yardRightTopCorner = builder.yardRightTopCorner;
        this.mowerList = builder.mowerList;
    }

    public Coordinates getYardRightTopCorner() {
        return yardRightTopCorner;
    }

    public int mowerCount() {
        return this.mowerList.size();
    }

    public Mower getMowerAtIndex(int index) {
        return this.mowerList.get(index);
    }

    public static class Builder {
        public Coordinates yardRightTopCorner;
        private List<Mower> mowerList;

        public Builder yardRightTopCorner(Coordinates yardRightTopCorner) {
            this.yardRightTopCorner = yardRightTopCorner;
            return this;
        }

        public Builder mowerList(List<Mower> mowerList) {
            this.mowerList = mowerList;
            return this;
        }

        public MowerEntry build() {
            return new MowerEntry(this);
        }
    }
}
