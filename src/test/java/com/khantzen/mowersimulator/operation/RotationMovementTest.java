package com.khantzen.mowersimulator.operation;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class RotationMovementTest {
    @Test
    public void mowerRightRotationFromNorthToEast() {
        testRightRotation('N', 'E');
    }

    @Test
    public void moveRightRotationFromEastToSouth() {
        testRightRotation('E', 'S');
    }

    @Test
    public void moveRightRotationFromSouthToWest() {
        testRightRotation('S', 'W');
    }

    @Test
    public void moveRightRotationFromWestToNorth() {
        testRightRotation('W', 'N');
    }

    @Test
    public void moveLeftRotationFromNorthToWest() {
        testLeftRotation('N', 'W');
    }

    @Test
    public void moveLeftRotationFromWestToSouth() {
        testLeftRotation('W', 'S');
    }

    @Test
    public void moveLeftRotationFromSouthToEast() {
        testLeftRotation('S', 'E');
    }

    @Test
    public void moveLeftRotationFromEastToNorth() {
        testLeftRotation('E', 'N');
    }

    private void testLeftRotation(char initialOrientation, char expectedOrientation) {
        testValidRotation(initialOrientation, expectedOrientation, 'G');
    }

    private void testRightRotation(char initialOrientation, char expectedOrientation) {
        testValidRotation(initialOrientation, expectedOrientation, 'D');
    }

    private void testValidRotation(char initialOrientation, char expectedOrientation, char rotation) {
        Movement movement = new Movement.Builder()
                .build();

        char orientationAfterRotation = movement.getOrientationAfterRotation(initialOrientation, rotation);

        Assertions.assertThat(orientationAfterRotation).isEqualTo(expectedOrientation);
    }
}
