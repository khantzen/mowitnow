package com.khantzen.mowersimulator.operation;

import com.khantzen.mowersimulator.model.Mower;
import com.khantzen.mowersimulator.model.SimulatorEntry;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final SimulatorEntry simulatorEntry;

    public Simulation(SimulatorEntry simulatorEntry) {
        this.simulatorEntry = simulatorEntry;
    }

    public List<String> run() {

        List<String> mowersFinalPosition = new ArrayList<>();

        for (int i = 0; i < simulatorEntry.mowerCount(); i++) {
            Mower mower = simulatorEntry.getMowerAtIndex(i);

            MowerMovement mowerMovement = buildMowerMovement(mower);

            String instructionSequence = mower.getInstructionSequence();

            for (char instruction: instructionSequence.toCharArray()) {
                if (instruction == 'A') {
                    mowerMovement.moveForward();
                } else {
                    mowerMovement.rotate(instruction);
                }
            }

            mowersFinalPosition.add(mower.toString());
        }

        return mowersFinalPosition;
    }

    private MowerMovement buildMowerMovement(Mower mower) {
        return new MowerMovement.Builder()
                        .mower(mower)
                        .yardXTopCorner(simulatorEntry.getYardXRightTopCorner())
                        .yardYTopCorner(simulatorEntry.getYardYRightTopCorner())
                        .build();
    }
}
