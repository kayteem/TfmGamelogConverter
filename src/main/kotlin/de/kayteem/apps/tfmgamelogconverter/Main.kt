package de.kayteem.apps.tfmgamelogconverter

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.kayteem.apps.tfmgamelogconverter.controller.converters.GameLogToPlayConverter
import de.kayteem.apps.tfmgamelogconverter.controller.export.common.ExcelExporter
import de.kayteem.apps.tfmgamelogconverter.controller.jsonImport.GameLogJsonImporter
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Main class processing the transformation from JSON game logs to the Excel format.
 *
 * Author: Tobias Mielke
 */
fun main() {

    // import all jsons from the execution directory
    val executionPath = Paths.get("").toAbsolutePath()
    val gameLogs = GameLogJsonImporter(jacksonObjectMapper()).importAll(executionPath)
    println("Import of ${gameLogs.size} json files finished")

    // convert GameLogs to Plays
    val converter = GameLogToPlayConverter()
    val plays = gameLogs.map { converter.process(it) }

    // export all plays
    val excelPath: Path = executionPath.resolve("TfmGameData.xlsx")
    ExcelExporter(excelPath).export(plays)
    println("Export of ${plays.size} plays finished: $excelPath")
}
