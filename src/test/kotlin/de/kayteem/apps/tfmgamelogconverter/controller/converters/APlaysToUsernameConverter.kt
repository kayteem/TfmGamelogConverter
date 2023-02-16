package de.kayteem.apps.tfmgamelogconverter.controller.converters

import TestDataFactory
import org.junit.Assert
import org.junit.Test

/**
 * Test Suite for PlaysToUsernameConverter
 *
 * Author: Tobias Mielke
 */
class APlaysToUsernameConverter {

    // unit under test
    private lateinit var converter: PlaysToUsernameConverter


    // test cases
    @Test
    fun canExtractUsernameFromPlays() {

        // setup
        converter = PlaysToUsernameConverter()

        // execution
        val username = converter.process(listOf(PLAY_1, PLAY_2, PLAY_3))

        // post-condition
        Assert.assertEquals(EXPECTED_USERNAME, username)
    }


    // companion
    companion object {
        val PLAY_1 = TestDataFactory.buildPlay1()
        val PLAY_2 = TestDataFactory.buildPlay2()
        val PLAY_3 = TestDataFactory.buildPlay3()

        const val EXPECTED_USERNAME = "KayTeEm"
    }

}