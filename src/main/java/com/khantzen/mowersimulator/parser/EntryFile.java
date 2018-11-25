package com.khantzen.mowersimulator.parser;

import com.khantzen.mowersimulator.model.Coordinates;
import com.khantzen.mowersimulator.model.Mower;
import com.khantzen.mowersimulator.model.SimulatorEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EntryFile {

    public SimulatorEntry parse(String path) throws IOException, ParseException {
        List<String> userEntries = getEntriesFromPath(path);

        String yardTopCornerInfoLine = userEntries.get(0);
        Coordinates yardRightTopCorner = getYardRightTopCornerCoordinates(yardTopCornerInfoLine);

        List<Mower> mowerList = getMowerList(userEntries);

        return new SimulatorEntry.Builder()
                .yardRightTopCorner(yardRightTopCorner)
                .mowerList(mowerList)
                .build();
    }

    private List<String> getEntriesFromPath(String path) throws IOException, ParseException {
        List<String> fileContent = getFileContent(path);

        if (fileContent.size() % 2 == 0) {
            throw new ParseException(
                    "Cannot parse " + path + " file. " +
                            "incomplete file, should contain an odd line count.", 0);
        }

        return fileContent;
    }

    private List<String> getFileContent(String path) throws IOException {
        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(path));
        return bufferedReader.lines().collect(Collectors.toList());
    }

    private List<Mower> getMowerList(List<String> fileContent) throws ParseException {
        List<Mower> mowerList = new ArrayList<>();

        for (int i = 1; i < fileContent.size() - 1; i += 2) {
            String mowerInfoLine = fileContent.get(i);
            String mowerInstructionsLine = fileContent.get(i + 1);
            Mower mower = getMower(mowerInfoLine, mowerInstructionsLine);
            mowerList.add(mower);
        }

        return mowerList;
    }

    private Mower getMower(String mowerInfo, String mowerInstructionSequence) throws ParseException {
        Pattern mowerInfoMatchingPattern = Pattern.compile("^(?<xCoord>\\d+) (?<yCoord>\\d+) (?<orientation>[NSEW])$");
        Matcher mowerInfoMatcher = mowerInfoMatchingPattern.matcher(mowerInfo);

        if (!mowerInfoMatcher.find()) {
            throw new ParseException(
                    "Cannot parse '" + mowerInfo + "'" +
                            " into valid Mower object.", 0);
        }

        int xMower = Integer.parseInt(mowerInfoMatcher.group("xCoord"));
        int yMower = Integer.parseInt(mowerInfoMatcher.group("yCoord"));

        Coordinates coordinates = new Coordinates(xMower, yMower);
        char orientation = mowerInfoMatcher.group("orientation").charAt(0);


        if (!mowerInstructionSequence.matches("^[GDA]+$")) {
            throw new ParseException(
                    "Cannot parse '" + mowerInstructionSequence + "'" +
                            " into valid instructions sequence.", 0);
        }

        return new Mower.Builder()
                .orientation(orientation)
                .coordinates(coordinates)
                .instructionSequence(mowerInstructionSequence)
                .build();
    }

    private Coordinates getYardRightTopCornerCoordinates(String yardTopCornerInfoLine) throws ParseException {
        Pattern rightTopCornerMatchingPattern = Pattern.compile("^(?<xCoord>\\d+) (?<yCoord>\\d+)$");
        Matcher rightTopCornerCoordMatcher = rightTopCornerMatchingPattern.matcher(yardTopCornerInfoLine);

        if (!rightTopCornerCoordMatcher.find()) {
            throw new ParseException(
                    "Cannot parse '" + yardTopCornerInfoLine + "'" +
                            " into valid yard right top corner coordinates.", 0);
        }

        int yardXRightTopCorner = Integer.parseInt(rightTopCornerCoordMatcher.group("xCoord"));
        int yardYRightTopCorner = Integer.parseInt(rightTopCornerCoordMatcher.group("yCoord"));

        return new Coordinates(yardXRightTopCorner, yardYRightTopCorner);
    }
}
