package de.kayteem.apps.tfmgamelogconverter.controller.export

import TestDataFactory
import de.kayteem.apps.tfmgamelogconverter.controller.export.plays.PlaysExcelExporter
import org.junit.Assert
import org.junit.Test
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Test Suite for PlaysExcelExporter
 *
 * Author: Tobias Mielke
 */
class APlaysExcelExporter {

    // unit under test
    private lateinit var exporter: PlaysExcelExporter


    // test cases
    @Test
    fun canExportGameLogsToExcel() {

        // setup
        val plays = listOf(PLAY_1, PLAY_2)
        exporter = PlaysExcelExporter()

        // execution
        exporter.export(PATH_EXCEL, plays)

        // post-condition
        Assert.assertTrue(PATH_EXCEL.toFile().exists())
    }


    // companion
    companion object {

        // paths
        private val PATH_ARTIFACT: Path = Paths.get("out/artifacts/TfmGamelogConverter_jar/")
        val PATH_EXCEL: Path = PATH_ARTIFACT.resolve("TfmGameData.xlsx")

        // plays
        val PLAY_1 = TestDataFactory.buildPlay1()
        val PLAY_2 = TestDataFactory.buildPlay2()
    }

}