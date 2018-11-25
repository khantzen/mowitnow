package com.khantzen.mowersimulator;

import com.khantzen.mowersimulator.model.SimulatorEntry;
import com.khantzen.mowersimulator.operation.Simulation;
import com.khantzen.mowersimulator.parser.EntryFile;

import java.util.List;

public class MowerSimulatorMain {

    public static void main(String[] args) {
        try {
            SimulatorEntry simulatorEntry = getSimulatorEntry(args);
            List<String> simulationResult = runMowerSimulation(simulatorEntry);
            printSimulationResult(simulationResult);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printSimulationResult(List<String> simulationResult) {
        simulationResult.forEach(System.out::println);
    }

    private static List<String> runMowerSimulation(SimulatorEntry simulatorEntry) {
        Simulation simulation = new Simulation(simulatorEntry);
        return simulation.run();
    }

    private static SimulatorEntry getSimulatorEntry(String[] args) throws Exception {
        if (args.length == 0) {
            throw new Exception("Program should take at least one argument in entry");
        }

        String filePath = args[0];
        EntryFile entryFile = new EntryFile();
        return entryFile.parse(filePath);
    }
}
