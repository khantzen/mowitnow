package com.khantzen.mowersimulator.operation;

import com.khantzen.mowersimulator.model.Coordinates;
import com.khantzen.mowersimulator.model.Mower;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MowerGoForwardMovementTest {

    @Test
    public void northOrientedMowerGoForward() {
        yTranslationTest('N', 2);
    }

    @Test
    public void southOrientedMowerGoForward() {
        yTranslationTest('S', 0);
    }

    private void yTranslationTest(char orientation, int expectedY) {
        simpleTranslationTest(orientation, 1, expectedY);
    }


    @Test
    public void eastOrientedMowerGoForward() {
        xTranslationTest('E', 2);
    }

    @Test
    public void weastOrientedMowerGoForward() {
        xTranslationTest('W', 0);
    }

    private void xTranslationTest(char orientation, int expectedX) {
        simpleTranslationTest(orientation, expectedX, 1);
    }


    @Test
    public void testXUnder0Translation() {
        Mower mower = new Mower.Builder()
                .orientation('W')
                .coordinates(new Coordinates(0, 1))
                .build();

        simpleTranslationTest(mower, 0, 1);
    }

    @Test
    public void testYUnder0Translation() {
        Mower mower = new Mower.Builder()
                .orientation('S')
                .coordinates(new Coordinates(7, 0))
                .build();

        simpleTranslationTest(mower, 7, 0);
    }

    @Test
    public void testXOverYardTranslation() {
        Mower mower = new Mower.Builder()
                .orientation('E')
                .coordinates(new Coordinates(15, 3))
                .build();

        simpleTranslationTest(mower, 15, 3);
    }

    @Test
    public void testYOverYardTranslation() {
        Mower mower = new Mower.Builder()
                .orientation('N')
                .coordinates(new Coordinates(5, 18))
                .build();

        simpleTranslationTest(mower, 5, 18);
    }

    private void simpleTranslationTest(char orientation, int expectedX, int expectedY) {
        Mower mower = new Mower.Builder()
                .orientation(orientation)
                .coordinates(new Coordinates(1, 1))
                .build();

        simpleTranslationTest(mower, expectedX, expectedY);
    }

    private void simpleTranslationTest(Mower mower, int expectedX, int expectedY) {
        MowerMovement mowerMovement =
                new MowerMovement.Builder()
                        .mower(mower)
                        .yardXTopCorner(15)
                        .yardYTopCorner(18)
                        .build();

        mowerMovement.moveForward();

        Assertions.assertThat(mower.getX()).isEqualTo(expectedX);
        Assertions.assertThat(mower.getY()).isEqualTo(expectedY);
    }


}
