package de.kayteem.apps.tfmgamelogconverter.controller.converters

import TestDataFactory
import org.junit.Assert
import org.junit.Test

/**
 * Test Suite for PlaysToCorporationsConverter
 *
 * Author: Tobias Mielke
 */
class APlaysToCorporationsConverter {

    // unit under test
    private lateinit var converter: PlaysToCorporationsConverter


    // test cases
    @Test
    fun canExtractCorporationsFromPlays() {

        // setup
        converter = PlaysToCorporationsConverter("KayTeEm")

        // execution
        val corporations = converter.process(listOf(PLAY_1, PLAY_2, PLAY_3))

        // post-condition
        Assert.assertEquals(EXPECTED_CORPORATIONS, corporations)
    }


    // companion
    companion object {
        val PLAY_1 = TestDataFactory.buildPlay1()
        val PLAY_2 = TestDataFactory.buildPlay2()
        val PLAY_3 = TestDataFactory.buildPlay3()

        val EXPECTED_CORPORATIONS = listOf(
            TestDataFactory.buildCorp1(),
            TestDataFactory.buildCorp2(),
            TestDataFactory.buildCorp3(),
            TestDataFactory.buildCorp4(),
            TestDataFactory.buildCorp5(),
            TestDataFactory.buildCorp6(),
        )
    }

}