package de.kayteem.apps.tfmgamelogconverter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.kayteem.apps.tfmgamelogconverter.controller.export.plays.PlaysExcelExporter
import de.kayteem.apps.tfmgamelogconverter.controller.jsonImport.GameLogImporter
import de.kayteem.apps.tfmgamelogconverter.controller.jsonImport.GameLogJsonImporter
import de.kayteem.apps.tfmgamelogconverter.model.xlsxExport.Play
import de.kayteem.apps.tfmgamelogconverter.model.jsonImport.GameLog
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.math.roundToInt

/**
 * Main class processing the transformation from JSON game logs to the Excel format.
 *
 * Author: Tobias Mielke
 */
fun main() {

    // build the importer
    val objectMapper: ObjectMapper = jacksonObjectMapper()
    val importer: GameLogImporter = GameLogJsonImporter(objectMapper)

    // import all jsons from the execution directory
    val executionPath: Path = Paths.get("").toAbsolutePath()
    val gameLogs: List<GameLog> = importer.importAll(executionPath)
    println("Import of ${gameLogs.size} json files finished")

    // convert GameLogs to Plays
    val plays = gameLogs.map {
        val player1 = it.players.player1
        val player2 = it.players.player2
        val player3 = it.players.player3
        val player4 = it.players.player4
        val player5 = it.players.player5
        val finalScores = it.finalScores()

        Play(
            timestamp = it.start,
            board = it.board,
            generations = it.generations(),
            player1Name = player1.name,
            player1Corp = player1.corporation,
            player1Elo = player1.elo.roundToInt(),
            player1Score = finalScores[player1]!!,
            player2Name = player2.name,
            player2Corp = player2.corporation,
            player2Elo = player2.elo.roundToInt(),
            player2Score = finalScores[player2]!!,
            player3Name = player3?.name,
            player3Corp = player3?.corporation,
            player3Elo = player3?.elo?.roundToInt(),
            player3Score = finalScores[player3],
            player4Name = player4?.name,
            player4Corp = player4?.corporation,
            player4Elo = player4?.elo?.roundToInt(),
            player4Score = finalScores[player4],
            player5Name = player5?.name,
            player5Corp = player5?.corporation,
            player5Elo = player5?.elo?.roundToInt(),
            player5Score = finalScores[player5]
        )
    }

    // build the exporter
    val exporter = PlaysExcelExporter()

    // export all plays
    val excelPath: Path = executionPath.resolve("TfmGameData.xlsx")
    exporter.export(excelPath, plays)
    println("Export of ${plays.size} plays finished: $excelPath")
}
