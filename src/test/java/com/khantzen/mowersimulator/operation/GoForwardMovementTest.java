package com.khantzen.mowersimulator.operation;

import com.khantzen.mowersimulator.model.Coordinates;
import com.khantzen.mowersimulator.model.Mower;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class GoForwardMovementTest {

    @Test
    public void northOrientedMowerGoForwardTest() {
        yTranslationTest('N', 2);
    }

    @Test
    public void southOrientedMowerGoForwardTest() {
        yTranslationTest('S', 0);
    }

    private void yTranslationTest(char orientation, int expectedY) {
        simpleTranslationAssertion(orientation, 1, expectedY);
    }

    @Test
    public void eastOrientedMowerGoForwardTest() {
        xTranslationTest('E', 2);
    }

    @Test
    public void weastOrientedMowerGoForwardTest() {
        xTranslationTest('W', 0);
    }

    private void xTranslationTest(char orientation, int expectedX) {
        simpleTranslationAssertion(orientation, expectedX, 1);
    }

    @Test
    public void xUnder0TranslationTest() {
        Mower mower = new Mower.Builder()
                .orientation('W')
                .coordinates(new Coordinates(0, 1))
                .build();

        simpleTranslationAssertion(mower, 0, 1);
    }

    @Test
    public void yUnder0TranslationTest() {
        Mower mower = new Mower.Builder()
                .orientation('S')
                .coordinates(new Coordinates(7, 0))
                .build();

        simpleTranslationAssertion(mower, 7, 0);
    }

    @Test
    public void xOverYardTranslationTest() {
        Mower mower = new Mower.Builder()
                .orientation('E')
                .coordinates(new Coordinates(15, 3))
                .build();

        simpleTranslationAssertion(mower, 15, 3);
    }

    @Test
    public void yOverYardTranslationTest() {
        Mower mower = new Mower.Builder()
                .orientation('N')
                .coordinates(new Coordinates(5, 18))
                .build();

        simpleTranslationAssertion(mower, 5, 18);
    }

    private void simpleTranslationAssertion(char orientation, int expectedX, int expectedY) {
        Mower mower = new Mower.Builder()
                .orientation(orientation)
                .coordinates(new Coordinates(1, 1))
                .build();

        simpleTranslationAssertion(mower, expectedX, expectedY);
    }

    private void simpleTranslationAssertion(Mower mower, int expectedX, int expectedY) {
        Movement movement =
                new Movement.Builder()
                        .yardXTopCorner(15)
                        .yardYTopCorner(18)
                        .build();

        Coordinates coord = mower.getCoordinates();
        char mowerOrientation = mower.getOrientation();

        Coordinates coordAfterMoveForward = movement.getCoordinatesAfterMoveForward(coord, mowerOrientation);

        Assertions.assertThat(coordAfterMoveForward.getX()).isEqualTo(expectedX);
        Assertions.assertThat(coordAfterMoveForward.getY()).isEqualTo(expectedY);
    }


}
