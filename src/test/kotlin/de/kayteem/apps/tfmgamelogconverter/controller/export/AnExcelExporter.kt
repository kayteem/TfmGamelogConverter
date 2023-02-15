package de.kayteem.apps.tfmgamelogconverter.controller.export

import TestDataFactory
import de.kayteem.apps.tfmgamelogconverter.controller.export.common.ExcelExporter
import org.junit.Assert
import org.junit.Test
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Test Suite for PlaysExcelExporter
 *
 * Author: Tobias Mielke
 */
class AnExcelExporter {

    // unit under test
    private lateinit var exporter: ExcelExporter


    // test cases
    @Test
    fun canExportGameLogsToExcel() {

        // setup
        val plays = listOf(PLAY_1, PLAY_2)
        exporter = ExcelExporter()

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