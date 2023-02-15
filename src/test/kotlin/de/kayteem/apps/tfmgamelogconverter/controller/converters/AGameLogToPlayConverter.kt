package de.kayteem.apps.tfmgamelogconverter.controller.converters

import TestDataFactory
import org.junit.Assert
import org.junit.Test

/**
 * Test Suite for GameLogToPlayConverter
 *
 * Author: Tobias Mielke
 */
class AGameLogToPlayConverter {

    // unit under test
    private lateinit var converter: GameLogToPlayConverter


    // test cases
    @Test
    fun canConvertGameLogsToPlays() {

        // setup
        converter = GameLogToPlayConverter()

        // execution
        val play1 = converter.process(GAME_LOG_1)
        val play2 = converter.process(GAME_LOG_2)

        // post-condition
        Assert.assertEquals(EXPECTED_PLAY_1, play1)
        Assert.assertEquals(EXPECTED_PLAY_2, play2)
    }


    // companion
    companion object {
        val GAME_LOG_1 = TestDataFactory.buildGameLog1()
        val GAME_LOG_2 = TestDataFactory.buildGameLog2()

        val EXPECTED_PLAY_1 = TestDataFactory.buildPlay1()
        val EXPECTED_PLAY_2 = TestDataFactory.buildPlay2()
    }

}