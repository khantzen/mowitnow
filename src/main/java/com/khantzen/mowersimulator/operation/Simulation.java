package com.khantzen.mowersimulator.operation;

import com.khantzen.mowersimulator.model.Coordinates;
import com.khantzen.mowersimulator.model.Mower;
import com.khantzen.mowersimulator.model.SimulatorEntry;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final SimulatorEntry simulatorEntry;
    private final Movement movement;


    public Simulation(SimulatorEntry simulatorEntry) {
        this.simulatorEntry = simulatorEntry;
        this.movement = new Movement.Builder()
                .yardXTopCorner(simulatorEntry.getYardXRightTopCorner())
                .yardYTopCorner(simulatorEntry.getYardYRightTopCorner())
                .build();
    }

    public List<String> run() {

        List<String> mowersFinalPosition = new ArrayList<>();

        int deployedMowerCount = this.simulatorEntry.getMowerCount();

        for (int i = 0; i < deployedMowerCount; i++) {
            Mower mower = moveMower(i);
            mowersFinalPosition.add(mower.toString());
        }

        return mowersFinalPosition;
    }

    private Mower moveMower(int index) {
        Mower mower = this.simulatorEntry.getMowerAtIndex(index);

        String instructionSequence = mower.getInstructionSequence();

        for (char instruction : instructionSequence.toCharArray()) {
            char mowerOrientation = mower.getOrientation();

            if (instruction == 'A') {
                Coordinates mowerCoordinates = mower.getCoordinates();
                Coordinates mowerCoordinatesAfterMoveForward = this.movement.getCoordinatesAfterMoveForward(mowerCoordinates, mowerOrientation);
                mower.setCoordinates(mowerCoordinatesAfterMoveForward);
            } else {
                char newOrientation = this.movement.getOrientationAfterRotation(mowerOrientation, instruction);
                mower.setOrientation(newOrientation);
            }
        }

        return mower;
    }

}
