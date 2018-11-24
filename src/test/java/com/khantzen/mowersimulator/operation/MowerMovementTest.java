package com.khantzen.mowersimulator.operation;

import com.khantzen.mowersimulator.model.Mower;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MowerMovementTest {

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
        Mower mower = new Mower.Builder()
                .orientation(initialOrientation)
                .build();

        MowerMovement mowerMovement = new MowerMovement.Builder()
                .mower(mower)
                .build();

        mowerMovement.rotate(rotation);

        Assertions.assertThat(mower.getOrientation()).isEqualTo(expectedOrientation);
    }


}
