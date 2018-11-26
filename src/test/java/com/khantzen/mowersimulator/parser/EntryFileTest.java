package com.khantzen.mowersimulator.parser;

import com.khantzen.mowersimulator.model.Coordinates;
import com.khantzen.mowersimulator.model.Mower;
import com.khantzen.mowersimulator.model.SimulatorEntry;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

import static org.junit.Assert.fail;

public class EntryFileTest {

    @Test
    public void extractYardInformationTest() throws IOException, ParseException {
        SimulatorEntry simulatorEntry = parseFile("src/test/resources/validTestCase/oneMowerTestFile");

        Assertions.assertThat(simulatorEntry).isNotNull();

        Coordinates yardRightTopCoordinates = simulatorEntry.getYardRightTopCorner();
        Assertions.assertThat(yardRightTopCoordinates.getX()).isEqualTo(5);
        Assertions.assertThat(yardRightTopCoordinates.getY()).isEqualTo(15);
    }

    @Test
    public void extractMowerInformationTest() throws IOException, ParseException {
        SimulatorEntry simulatorEntry = parseFile("src/test/resources/validTestCase/oneMowerTestFile");
        Assertions.assertThat(simulatorEntry).isNotNull();

        Assertions.assertThat(simulatorEntry.getMowerCount()).isEqualTo(1);

        Mower mower = simulatorEntry.getMowerAtIndex(0);

        Assertions.assertThat(mower.getX()).isEqualTo(1);
        Assertions.assertThat(mower.getY()).isEqualTo(2);
        Assertions.assertThat(mower.getOrientation()).isEqualTo('N');

        // Check instructions
        Assertions.assertThat(mower.getInstructionSequence()).isEqualTo("GAGADAGAA");
    }

    @Test
    public void extractMultipleMowerInformationTest() throws IOException, ParseException {
        SimulatorEntry simulatorEntry = this.parseFile("src/test/resources/validTestCase/threeMowerTestFile");

        // Check mower
        Assertions.assertThat(simulatorEntry.getMowerCount()).isEqualTo(3);

        Mower mower = simulatorEntry.getMowerAtIndex(2);

        Assertions.assertThat(mower.getX()).isEqualTo(4);
        Assertions.assertThat(mower.getY()).isEqualTo(2);
        Assertions.assertThat(mower.getOrientation()).isEqualTo('S');
        Assertions.assertThat(mower.getInstructionSequence()).isEqualTo("ADGAAADDAA");
    }

    private SimulatorEntry parseFile(String path) throws IOException, ParseException {
        EntryFile entryFile = new EntryFile();
        return entryFile.parse(path);
    }

    @Test
    public void unknownFilePathTest() {
        EntryFile entryFile = new EntryFile();
        try { // random file path so developer cannot make this test fail accidentally
            entryFile.parse("file/not/found/" + UUID.randomUUID().toString());
            fail("Method parse is supposed to throw an IOException");
        } catch (IOException ignored) {
        } catch (Exception ignored) {
            fail("Method parse is supposed to throw an IOException");
        }
    }

    @Test
    public void incompleteFileTest() throws IOException {
        EntryFile entryFile = new EntryFile();
        try {
            entryFile.parse("src/test/resources/invalidTestCase/incompleteTestFile");
            fail("Method parse is supposed to throw a ParseException");
        } catch (ParseException exception) {
            Assertions.assertThat(exception.getMessage()).contains("incomplete file, should have an odd line count greater than 1.");
        }
    }

    @Test
    public void invalidTopRightCornerInfoTest() throws IOException {
        EntryFile entryFile = new EntryFile();
        try {
            entryFile.parse("src/test/resources/invalidTestCase/invalidTopRightCornerTestFile");
            fail("Method parse is supposed to throw a ParseException");
        } catch (ParseException exception) {
            Assertions.assertThat(exception.getMessage()).contains("not parse '5 M' into valid yard right top corn");
        }
    }

    @Test
    public void invalidMowerInfoTest() throws IOException {
        EntryFile entryFile = new EntryFile();
        try {
            entryFile.parse("src/test/resources/invalidTestCase/invalidMowerInfoTestFile");
            fail("Method parse is supposed to throw a ParseException");
        } catch (ParseException exception) {
            Assertions.assertThat(exception.getMessage()).contains("arse '1 2 D' into valid Mower object");
        }
    }

    @Test
    public void invalidInstructionSequenceInfoTest() throws IOException {
        EntryFile entryFile = new EntryFile();
        try {
            entryFile.parse("src/test/resources/invalidTestCase/invalidInstructionSequenceTestFile");
            fail("Method parse is supposed to throw a ParseException");
        } catch (ParseException exception) {
            Assertions.assertThat(exception.getMessage()).contains("parse 'GAGWNDEDAGAA' into valid instructions se");
        }
    }

}