package de.kayteem.apps.tfmgamelogconverter.model.internal

import TestDataFactory
import org.junit.Assert
import org.junit.Test

/**
 * Test Suite for Play
 *
 * Author: Tobias Mielke
 */
class APlay {

    // test cases
    @Test
    fun canDetermineItsWinner() {

        // setup
        val plays = listOf(PLAY_1, PLAY_2, PLAY_3)

        // execution
        val winners = plays.map { it.winner() }

        // post-condition
        Assert.assertEquals(EXPECTED_WINNER_PLAY_1.name, winners[0]?.name)
        Assert.assertEquals(EXPECTED_WINNER_PLAY_2.name, winners[1]?.name)
        Assert.assertEquals(EXPECTED_WINNER_PLAY_3.name, winners[2]?.name)
    }


    // companion
    companion object {

        // plays
        val PLAY_1 = TestDataFactory.buildPlay1()
        val PLAY_2 = TestDataFactory.buildPlay2()
        val PLAY_3 = TestDataFactory.buildPlay3()

        // winners
        val EXPECTED_WINNER_PLAY_1 = TestDataFactory.buildPlayer1Game1()
        val EXPECTED_WINNER_PLAY_2 = TestDataFactory.buildPlayer1Game2()
        val EXPECTED_WINNER_PLAY_3 = TestDataFactory.buildPlayer1Game3()
    }

}