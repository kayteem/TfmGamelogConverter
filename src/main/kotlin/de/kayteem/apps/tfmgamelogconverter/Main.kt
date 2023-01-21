package de.kayteem.apps.tfmgamelogconverter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.kayteem.apps.tfmgamelogconverter.controller.jsonImport.GameLogImporter
import de.kayteem.apps.tfmgamelogconverter.controller.jsonImport.GameLogImporterImpl
import de.kayteem.apps.tfmgamelogconverter.model.jsonImport.GameLog
import java.nio.file.Path
import java.nio.file.Paths

fun main() {

    // build the importer
    val objectMapper: ObjectMapper = jacksonObjectMapper()
    val importer: GameLogImporter = GameLogImporterImpl(objectMapper)

    // import all jsons from the execution directory
    val executionPath: Path = Paths.get("").toAbsolutePath()
    val gameLogs: List<GameLog> = importer.importAll(executionPath)
    println("Imported ${gameLogs.size} json files")

    // print results
    gameLogs.forEach {
        val timestamp = it.start
        val board = it.board
        val player1 = it.players.player1
        val score1 = it.finalScores()[player1]
        val player2 = it.players.player2
        val score2 = it.finalScores()[player2]
        val generations = it.generations()

        println(
            timestamp + ": " +
            board + " - " +
            player1.name + "[" + score1 + "] vs. " +
            player2.name + "[" + score2 + "] - " +
            generations + " generations"
        )
    }
}