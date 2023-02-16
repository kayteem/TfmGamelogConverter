package de.kayteem.apps.tfmgamelogconverter.controller.jsonImport

import TestDataFactory
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.kayteem.apps.tfmgamelogconverter.model.jsonImport.GameLog
import de.kayteem.apps.tfmgamelogconverter.model.jsonImport.LogPlayer
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import java.nio.file.Path
import kotlin.io.path.toPath

/**
 * Test Suite for GameLogJsonImporter
 *
 * Author: Tobias Mielke
 */
class AGameLogJsonImporter {

    // unit under test
    private lateinit var importer: GameLogJsonImporter


    // test cases
    @Test
    fun canImportASingleJsonByFilename() {

        // setup
        val jsonPath = loadJsonResource(RES_LOG_GAME_1)
        if (jsonPath == null) {
            fail("JSON resource not found!")
        }

        // execution
        val gameLog = importer.import(jsonPath!!)
        if (gameLog == null) {
            fail("JSON import failed!")
        }

        // post-condition
        assertGameLog(
            gameLog!!,
            EXPECTED_GAME_LOG_1,
            EXPECTED_GENERATIONS_GAME_1,
            mapOf(
                EXPECTED_PLAYER_1_GAME_1 to EXPECTED_SCORE_1_GAME_1,
                EXPECTED_PLAYER_2_GAME_1 to EXPECTED_SCORE_2_GAME_1
            )
        )
    }

    @Test
    fun canImportMultipleJsonsFromDirectory() {

        // setup
        val dirPath = loadResourceDirectory()
        if (dirPath == null) {
            fail("path to JSON resources not found!")
        }

        // execution
        val gameLogs = importer.importAll(dirPath!!)

        // post-condition
        assertEquals(3, gameLogs.size)

        // game 1
        assertGameLog(
            keepOnlyLastTurn(gameLogs[0]),
            EXPECTED_GAME_LOG_1,
            EXPECTED_GENERATIONS_GAME_1,
            mapOf(
                EXPECTED_PLAYER_1_GAME_1 to EXPECTED_SCORE_1_GAME_1,
                EXPECTED_PLAYER_2_GAME_1 to EXPECTED_SCORE_2_GAME_1
            )
        )

        // game 2
        assertGameLog(
            keepOnlyLastTurn(gameLogs[1]),
            EXPECTED_GAME_LOG_2,
            EXPECTED_GENERATIONS_GAME_2,
            mapOf(
                EXPECTED_PLAYER_1_GAME_2 to EXPECTED_SCORE_1_GAME_2,
                EXPECTED_PLAYER_2_GAME_2 to EXPECTED_SCORE_2_GAME_2
            )
        )

        // game 3
        assertGameLog(
            keepOnlyLastTurn(gameLogs[2]),
            EXPECTED_GAME_LOG_3,
            EXPECTED_GENERATIONS_GAME_3,
            mapOf(
                EXPECTED_PLAYER_1_GAME_3 to EXPECTED_SCORE_1_GAME_3,
                EXPECTED_PLAYER_2_GAME_3 to EXPECTED_SCORE_2_GAME_3,
                EXPECTED_PLAYER_3_GAME_3 to EXPECTED_SCORE_3_GAME_3
            )
        )
    }


    // helpers
    @Before
    fun setUp() {
        val objectMapper: ObjectMapper = jacksonObjectMapper()

        importer = GameLogJsonImporter(objectMapper)
    }

    private fun assertGameLog(gameLog: GameLog, expectedGameLog: GameLog, expectedGenerations: Int, expectedScores: Map<LogPlayer, Int>) {
        assertEquals(expectedGameLog, keepOnlyLastTurn(gameLog))

        assertEquals(expectedGenerations, gameLog.generations())

        assertEquals(expectedScores, gameLog.finalScores())
    }


    // companion
    companion object {

        // constants - common
        private const val RES_DIRECTORY = "/exampleJson/"

        // constants - game 1
        const val RES_LOG_GAME_1: String = "gamelog_2023-01-10"

        private val EXPECTED_PLAYER_1_GAME_1 = TestDataFactory.buildLogPlayer1Game1()
        private val EXPECTED_PLAYER_2_GAME_1 = TestDataFactory.buildLogPlayer2Game1()
        private val EXPECTED_GENERATIONS_GAME_1 = TestDataFactory.buildGenerationsGame1()
        private val EXPECTED_SCORE_1_GAME_1 = TestDataFactory.buildScore1Game1()
        private val EXPECTED_SCORE_2_GAME_1 = TestDataFactory.buildScore2Game1()
        val EXPECTED_GAME_LOG_1 = TestDataFactory.buildGameLog1()

        // constants - game 2
        private val EXPECTED_PLAYER_1_GAME_2 = TestDataFactory.buildLogPlayer1Game2()
        private val EXPECTED_PLAYER_2_GAME_2 = TestDataFactory.buildLogPlayer2Game2()
        private val EXPECTED_GENERATIONS_GAME_2 = TestDataFactory.buildGenerationsGame2()
        private val EXPECTED_SCORE_1_GAME_2 = TestDataFactory.buildScore1Game2()
        private val EXPECTED_SCORE_2_GAME_2 = TestDataFactory.buildScore2Game2()
        val EXPECTED_GAME_LOG_2 = TestDataFactory.buildGameLog2()

        // constants - game 3
        private val EXPECTED_PLAYER_1_GAME_3 = TestDataFactory.buildLogPlayer1Game3()
        private val EXPECTED_PLAYER_2_GAME_3 = TestDataFactory.buildLogPlayer2Game3()
        private val EXPECTED_PLAYER_3_GAME_3 = TestDataFactory.buildLogPlayer3Game3()
        private val EXPECTED_GENERATIONS_GAME_3 = TestDataFactory.buildGenerationsGame3()
        private val EXPECTED_SCORE_1_GAME_3 = TestDataFactory.buildScore1Game3()
        private val EXPECTED_SCORE_2_GAME_3 = TestDataFactory.buildScore2Game3()
        private val EXPECTED_SCORE_3_GAME_3 = TestDataFactory.buildScore3Game3()
        val EXPECTED_GAME_LOG_3 = TestDataFactory.buildGameLog3()

        // utils
        fun loadJsonResource(resourceFilename: String): Path? {
            return Companion::class.java.getResource("$RES_DIRECTORY$resourceFilename.json")?.toURI()?.toPath()
        }

        fun loadResourceDirectory(): Path? {
            return Companion::class.java.getResource(RES_DIRECTORY)?.toURI()?.toPath()
        }

        fun keepOnlyLastTurn(gameLog: GameLog): GameLog {
            return GameLog(
                board = gameLog.board,
                isRanked = gameLog.isRanked,
                isOnline = gameLog.isOnline,
                start = gameLog.start,
                players = gameLog.players,
                turns = listOf(gameLog.turns.last())
            )
        }
    }

}