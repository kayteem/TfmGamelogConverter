package de.kayteem.apps.tfmgamelogconverter.controller.converters

import TestDataFactory
import org.junit.Assert
import org.junit.Test

/**
 * Test Suite for PlayToExcelPlayConverter
 *
 * Author: Tobias Mielke
 */
class APlayToExcelPlayConverter {

    // unit under test
    private lateinit var converter: PlayToExcelPlayConverter


    // test cases
    @Test
    fun canConvertPlaysToExcelPlays() {

        // setup
        converter = PlayToExcelPlayConverter()

        // execution
        val excelPlay1 = converter.process(PLAY_1)
        val excelPlay2 = converter.process(PLAY_2)

        // post-condition
        Assert.assertEquals(EXPECTED_EXCEL_PLAY_1, excelPlay1)
        Assert.assertEquals(EXPECTED_EXCEL_PLAY_2, excelPlay2)
    }


    // companion
    companion object {
        val PLAY_1 = TestDataFactory.buildPlay1()
        val PLAY_2 = TestDataFactory.buildPlay2()

        val EXPECTED_EXCEL_PLAY_1 = TestDataFactory.buildExcelPlay1()
        val EXPECTED_EXCEL_PLAY_2 = TestDataFactory.buildExcelPlay2()
    }

}