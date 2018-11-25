package com.khantzen.mowersimulator.operation;

import com.khantzen.mowersimulator.model.Coordinates;
import com.khantzen.mowersimulator.model.Mower;
import com.khantzen.mowersimulator.model.SimulatorEntry;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final SimulatorEntry simulatorEntry;
    private final MowerMovement mowerMovement;


    public Simulation(SimulatorEntry simulatorEntry) {
        this.simulatorEntry = simulatorEntry;
        this.mowerMovement = new MowerMovement.Builder()
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
            if (instruction == 'A') {
                Coordinates mowerCoordinates = this.mowerMovement.getCoordinatesAfterMoveForward(mower);
                mower.setCoordinates(mowerCoordinates);
            } else {
                char mowerOrientation = mower.getOrientation();
                char newOrientation = this.mowerMovement.getOrientationAfterRotation(mowerOrientation, instruction);
                mower.setOrientation(newOrientation);
            }
        }
        return mower;
    }

}
