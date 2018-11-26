package com.khantzen.mowersimulator.operation;

import com.khantzen.mowersimulator.model.Coordinates;
import com.khantzen.mowersimulator.model.Mower;
import com.khantzen.mowersimulator.model.SimulatorEntry;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SimulationTest {

    @Test
    public void oneMowerSimulationTest() {
        Mower mower = new Mower.Builder()
                .coordinates(new Coordinates(1, 2))
                .orientation('N')
                .instructionSequence("GAGAGAGAA")
                .build();

        List<Mower> mowerList = Collections.singletonList(mower);

        SimulatorEntry simulatorEntry = new SimulatorEntry.Builder()
                .yardRightTopCorner(new Coordinates(5, 5))
                .mowerList(mowerList)
                .build();

        Simulation simulation = new Simulation(simulatorEntry);

        List<String> simulationResult = simulation.run();

        Assertions.assertThat(simulationResult)
                .isNotNull()
                .isNotEmpty()
                .size().isEqualTo(1);

        String result = simulationResult.get(0);
        Assertions.assertThat(result).isEqualTo("1 3 N");
    }

    @Test
    public void twoMowerSimulationTest() {
        Mower firstMower = new Mower.Builder()
                .coordinates(new Coordinates(1, 2))
                .orientation('N')
                .instructionSequence("GAGAGAGAA")
                .build();

        Mower secondMower = new Mower.Builder()
                .coordinates(new Coordinates(3, 3))
                .orientation('E')
                .instructionSequence("AADAADADDA")
                .build();

        List<Mower> mowerList = Arrays.asList(firstMower, secondMower);

        SimulatorEntry simulatorEntry = new SimulatorEntry.Builder()
                .yardRightTopCorner(new Coordinates(5, 5))
                .mowerList(mowerList)
                .build();

        Simulation simulation = new Simulation(simulatorEntry);

        List<String> simulationResult = simulation.run();

        Assertions.assertThat(simulationResult)
                .isNotNull()
                .isNotEmpty()
                .size().isEqualTo(2);

        String firstMowerResult = simulationResult.get(0);
        Assertions.assertThat(firstMowerResult).isEqualTo("1 3 N");

        String secondMowerResult = simulationResult.get(1);
        Assertions.assertThat(secondMowerResult).isEqualTo("5 1 E");
    }

}
