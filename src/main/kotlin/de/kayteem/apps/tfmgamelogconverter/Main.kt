package de.kayteem.apps.tfmgamelogconverter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.kayteem.apps.tfmgamelogconverter.controller.converters.GameLogToPlayConverter
import de.kayteem.apps.tfmgamelogconverter.controller.export.common.ExcelExporter
import de.kayteem.apps.tfmgamelogconverter.controller.jsonImport.GameLogImporter
import de.kayteem.apps.tfmgamelogconverter.controller.jsonImport.GameLogJsonImporter
import de.kayteem.apps.tfmgamelogconverter.model.jsonImport.GameLog
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Main class processing the transformation from JSON game logs to the Excel format.
 *
 * Author: Tobias Mielke
 */
fun main() {

    // import all jsons from the execution directory
    val objectMapper: ObjectMapper = jacksonObjectMapper()
    val importer: GameLogImporter = GameLogJsonImporter(objectMapper)
    val executionPath: Path = Paths.get("").toAbsolutePath()
    val gameLogs: List<GameLog> = importer.importAll(executionPath)
    println("Import of ${gameLogs.size} json files finished")

    // convert GameLogs to Plays
    val converter = GameLogToPlayConverter()
    val plays = gameLogs.map { converter.process(it) }

    // export all plays
    val exporter = ExcelExporter()
    val excelPath: Path = executionPath.resolve("TfmGameData.xlsx")
    exporter.export(excelPath, plays)
    println("Export of ${plays.size} plays finished: $excelPath")
}
