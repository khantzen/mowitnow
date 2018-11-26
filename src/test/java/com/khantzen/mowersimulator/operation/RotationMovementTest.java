package com.khantzen.mowersimulator.operation;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class RotationMovementTest {

    @Test
    public void mowerRightRotationFromNorthToEastTest() {
        rightRotationAssertion('N', 'E');
    }

    @Test
    public void moveRightRotationFromEastToSouthTest() {
        rightRotationAssertion('E', 'S');
    }

    @Test
    public void moveRightRotationFromSouthToWestTest() {
        rightRotationAssertion('S', 'W');
    }

    @Test
    public void moveRightRotationFromWestToNorthTest() {
        rightRotationAssertion('W', 'N');
    }

    private void rightRotationAssertion(char initialOrientation, char expectedOrientation) {
        validRotationAssertion(initialOrientation, expectedOrientation, 'D');
    }

    @Test
    public void moveLeftRotationFromNorthToWestTest() {
        leftRotationAssertion('N', 'W');
    }

    @Test
    public void moveLeftRotationFromWestToSouthTest() {
        leftRotationAssertion('W', 'S');
    }

    @Test
    public void moveLeftRotationFromSouthToEastTest() {
        leftRotationAssertion('S', 'E');
    }

    @Test
    public void moveLeftRotationFromEastToNorthTest() {
        leftRotationAssertion('E', 'N');
    }

    private void leftRotationAssertion(char initialOrientation, char expectedOrientation) {
        validRotationAssertion(initialOrientation, expectedOrientation, 'G');
    }

    private void validRotationAssertion(char initialOrientation, char expectedOrientation, char rotation) {
        Movement movement = new Movement.Builder().build();

        char orientationAfterRotation = movement.getOrientationAfterRotation(initialOrientation, rotation);

        Assertions.assertThat(orientationAfterRotation).isEqualTo(expectedOrientation);
    }
}
