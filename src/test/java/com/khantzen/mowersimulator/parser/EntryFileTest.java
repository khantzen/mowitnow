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
    public void validEntryFileWithOneMower() throws IOException, ParseException {
        EntryFile entryFile = new EntryFile();

        SimulatorEntry simulatorEntry = entryFile.parse("src/test/resources/validTestCase/oneMowerTestFile");

        Assertions.assertThat(simulatorEntry).isNotNull();

        // Check yards
        Coordinates yardRightTopCoordinates = simulatorEntry.getYardRightTopCorner();
        this.checkYardRightTopCoordinates(yardRightTopCoordinates, 5, 15);

        // Check mower
        Assertions.assertThat(simulatorEntry.getMowerCount()).isEqualTo(1);

        Mower mower = simulatorEntry.getMowerAtIndex(0);

        Assertions.assertThat(mower.getX()).isEqualTo(1);
        Assertions.assertThat(mower.getY()).isEqualTo(2);
        Assertions.assertThat(mower.getOrientation()).isEqualTo('N');

        // Check instructions
        Assertions.assertThat(mower.getInstructionSequence()).isEqualTo("GAGADAGAA");
    }

    @Test
    public void validEntryFileWithManyMowers() throws IOException, ParseException {
        EntryFile entryFile = new EntryFile();
        SimulatorEntry simulatorEntry = entryFile.parse("src/test/resources/validTestCase/threeMowerTestFile");

        // Check yards
        Coordinates yardRightTopCoordinates = simulatorEntry.getYardRightTopCorner();
        this.checkYardRightTopCoordinates(yardRightTopCoordinates, 7, 5);

        // Check mower
        Assertions.assertThat(simulatorEntry.getMowerCount()).isEqualTo(3);

        Mower mower = simulatorEntry.getMowerAtIndex(2);

        Assertions.assertThat(mower.getX()).isEqualTo(4);
        Assertions.assertThat(mower.getY()).isEqualTo(2);
        Assertions.assertThat(mower.getOrientation()).isEqualTo('S');
        Assertions.assertThat(mower.getInstructionSequence()).isEqualTo("ADGAAADDAA");
    }

    private void checkYardRightTopCoordinates(Coordinates yardRightTopCorner, int expectedX, int expectedY) {
        Assertions.assertThat(yardRightTopCorner.getX()).isEqualTo(expectedX);
        Assertions.assertThat(yardRightTopCorner.getY()).isEqualTo(expectedY);
    }

    @Test
    public void unknownFilePath() {
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
    public void invalidEntryFileIncompleteFile() throws IOException {
        EntryFile entryFile = new EntryFile();
        try {
            entryFile.parse("src/test/resources/invalidTestCase/incompleteTestFile");
            fail("Method parse is supposed to throw a ParseException");
        } catch (ParseException exception) {
            Assertions.assertThat(exception.getMessage()).contains("incomplete file, should contain an odd line count.");
        }
    }

    @Test
    public void invalidTopRightCornerInfo() throws IOException {
        EntryFile entryFile = new EntryFile();
        try {
            entryFile.parse("src/test/resources/invalidTestCase/invalidTopRightCornerTestFile");
            fail("Method parse is supposed to throw a ParseException");
        } catch (ParseException exception) {
            Assertions.assertThat(exception.getMessage()).contains("not parse '5 M' into valid yard right top corn");
        }
    }

    @Test
    public void invalidMowerInfo() throws IOException {
        EntryFile entryFile = new EntryFile();
        try {
            entryFile.parse("src/test/resources/invalidTestCase/invalidMowerInfoTestFile");
            fail("Method parse is supposed to throw a ParseException");
        } catch (ParseException exception) {
            Assertions.assertThat(exception.getMessage()).contains("arse '1 2 D' into valid Mower object");
        }
    }

    @Test
    public void invalidInstructionSequenceInfo() throws IOException {
        EntryFile entryFile = new EntryFile();
        try {
            entryFile.parse("src/test/resources/invalidTestCase/invalidInstructionSequenceTestFile");
            fail("Method parse is supposed to throw a ParseException");
        } catch (ParseException exception) {
            Assertions.assertThat(exception.getMessage()).contains("parse 'GAGWNDEDAGAA' into valid instructions se");
        }
    }
}