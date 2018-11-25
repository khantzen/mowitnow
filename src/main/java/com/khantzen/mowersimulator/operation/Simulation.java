package com.khantzen.mowersimulator.operation;

import com.khantzen.mowersimulator.model.Mower;
import com.khantzen.mowersimulator.model.SimulatorEntry;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final SimulatorEntry entry;

    public Simulation(SimulatorEntry simulatorEntry) {
        this.entry = simulatorEntry;
    }

    public List<String> run() {

        List<String> mowersFinalPosition = new ArrayList<>();

        for (int i = 0; i < entry.mowerCount(); i++) {
            Mower mower = entry.getMowerAtIndex(i);

            MowerMovement mowerMovement = new MowerMovement.Builder()
                    .mower(mower)
                    .yardXTopCorner(entry.getYardXRightTopCorner())
                    .yardYTopCorner(entry.getYardYRightTopCorner())
                    .build();

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
}
