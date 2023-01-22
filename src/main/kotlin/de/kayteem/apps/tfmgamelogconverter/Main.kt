package de.kayteem.apps.tfmgamelogconverter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.kayteem.apps.tfmgamelogconverter.controller.export.GameSummariesCsvExporter
import de.kayteem.apps.tfmgamelogconverter.controller.jsonImport.GameLogImporter
import de.kayteem.apps.tfmgamelogconverter.controller.jsonImport.GameLogJsonImporter
import de.kayteem.apps.tfmgamelogconverter.model.csvExport.GameSummary
import de.kayteem.apps.tfmgamelogconverter.model.jsonImport.GameLog
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.math.roundToInt

/**
 * Created by Tobias Mielke
 * Created on 21.01.2023
 * Changed on 21.01.2023
 */
fun main() {

    // build the importer
    val objectMapper: ObjectMapper = jacksonObjectMapper()
    val importer: GameLogImporter = GameLogJsonImporter(objectMapper)

    // import all jsons from the execution directory
    val executionPath: Path = Paths.get("").toAbsolutePath()
    val gameLogs: List<GameLog> = importer.importAll(executionPath)
    println("Import of ${gameLogs.size} json files finished")

    // convert GameLogs to GameSummaries
    val gameSummaries = gameLogs.map {
        val player1 = it.players.player1
        val player2 = it.players.player2
        val player3 = it.players.player3
        val player4 = it.players.player4
        val player5 = it.players.player5
        val finalScores = it.finalScores()

        GameSummary(
            timestamp = it.start,
            board = it.board,
            player1Name = player1.name,
            player1Elo = player1.elo.roundToInt(),
            player1Score = finalScores[player1]!!,
            player2Name = player2.name,
            player2Elo = player2.elo.roundToInt(),
            player2Score = finalScores[player2]!!,
            player3Name = player3?.name,
            player3Elo = player3?.elo?.roundToInt(),
            player3Score = finalScores[player3],
            player4Name = player4?.name,
            player4Elo = player4?.elo?.roundToInt(),
            player4Score = finalScores[player4],
            player5Name = player5?.name,
            player5Elo = player5?.elo?.roundToInt(),
            player5Score = finalScores[player5],
            generations = it.generations()
        )
    }

    // build the exporter
    val csvMapper = CsvMapper()
    val exporter = GameSummariesCsvExporter(csvMapper)

    // export all game summaries to CSV
    val csvPath: Path = executionPath.resolve("TfmGamesOverview.csv")
    exporter.export(csvPath, gameSummaries)
    println("Export of ${gameSummaries.size} game summaries finished: $csvPath")
}
