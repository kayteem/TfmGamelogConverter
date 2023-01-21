import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.kayteem.apps.tfmgamelogconverter.controller.GameLogImporter
import de.kayteem.apps.tfmgamelogconverter.controller.GameLogImporterImpl
import de.kayteem.apps.tfmgamelogconverter.model.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.nio.file.Path
import kotlin.io.path.toPath

/**
 * Created by Tobias Mielke
 * Created on 20.01.2023
 * Changed on 20.01.2023
 */
class AGameLogImporter {

    // unit under test
    private lateinit var importer: GameLogImporter


    // test cases
    @Test
    fun canImportASingleJsonByFilename() {

        // setup
        val jsonPath = loadJsonResource(RES_LOG_GAME_1)

        // execution
        val gameLog = importer.import(jsonPath) !!

        // post-condition
        assertEquals(EXPECTED_LOG_GAME_1, keepOnlyLastTurn(gameLog))

        assertEquals(EXPECTED_GENERATIONS_GAME_1, gameLog.generations())
        
        assertEquals(
            mapOf(
                EXPECTED_PLAYER_1_GAME_1 to EXPECTED_SCORE_1_GAME_1,
                EXPECTED_PLAYER_2_GAME_1 to EXPECTED_SCORE_2_GAME_1
            ),
            gameLog.finalScores()
        )
    }

    @Test
    fun canImportMultipleJsonsFromDirectory() {

        // setup
        val path = loadResourceDirectory()

        // execution
        val gameLogs = importer.importAll(path)

        // post-condition
        assertEquals(2, gameLogs.size)

        // game 1
        assertEquals(EXPECTED_LOG_GAME_1, keepOnlyLastTurn(gameLogs[0]))

        assertEquals(EXPECTED_GENERATIONS_GAME_1, gameLogs[0].generations())

        assertEquals(
            mapOf(
                EXPECTED_PLAYER_1_GAME_1 to EXPECTED_SCORE_1_GAME_1,
                EXPECTED_PLAYER_2_GAME_1 to EXPECTED_SCORE_2_GAME_1
            ),
            gameLogs[0].finalScores()
        )

        // game 2
        assertEquals(EXPECTED_LOG_GAME_2, keepOnlyLastTurn(gameLogs[1]))

        assertEquals(EXPECTED_GENERATIONS_GAME_2, gameLogs[1].generations())

        assertEquals(
            mapOf(
                EXPECTED_PLAYER_1_GAME_2 to EXPECTED_SCORE_1_GAME_2,
                EXPECTED_PLAYER_2_GAME_2 to EXPECTED_SCORE_2_GAME_2
            ),
            gameLogs[1].finalScores()
        )
    }


    // helpers
    @Before
    fun setUp() {
        val objectMapper: ObjectMapper = jacksonObjectMapper()

        importer = GameLogImporterImpl(objectMapper)
    }


    // companion
    companion object {

        // constants - common
        private const val RES_DIRECTORY = "exampleJson/"


        // constants - game 1
        const val RES_LOG_GAME_1: String = "gamelog_2023-01-10"

        private val EXPECTED_PLAYER_1_GAME_1 = Player(
            name = "KayTeEm",
            color = "Green",
            corporation = "Thorgate",
            elo = 1601.0216806701019
        )

        private val EXPECTED_PLAYER_2_GAME_1 = Player(
            name = "Player2",
            color = "Blue",
            corporation = "UNMI",
            elo = 1617.7076549970068
        )

        private const val EXPECTED_GENERATIONS_GAME_1 = 11
        private const val EXPECTED_SCORE_1_GAME_1 = 102
        private const val EXPECTED_SCORE_2_GAME_1 = 98

        val EXPECTED_LOG_GAME_1 = GameLog(
            board = "Tharsis",
            isRanked = false,
            isOnline = true,
            start = "2023-01-10T18:03:55.4Z",
            players = Players(
                player1 = EXPECTED_PLAYER_1_GAME_1,
                player2 = EXPECTED_PLAYER_2_GAME_1
            ),
            turns = listOf(
                Turn(
                    generation = EXPECTED_GENERATIONS_GAME_1,
                    playerInfos = PlayerInfos(
                        playerInfo1 = PlayerInfo(
                            score = Score(EXPECTED_SCORE_1_GAME_1)
                        ),
                        playerInfo2 = PlayerInfo(
                            score = Score(EXPECTED_SCORE_2_GAME_1)
                        )
                    )
                )
            )
        )

        // constants - game 1
        private val EXPECTED_PLAYER_1_GAME_2 = Player(
            name = "KayTeEm",
            color = "Green",
            corporation = "InterplanetaryCinematics",
            elo = 1629.384708225632
        )

        private val EXPECTED_PLAYER_2_GAME_2 = Player(
            name = "Player 2",
            color = "Blue",
            corporation = "Inventrix",
            elo = 1696.4541812619316
        )

        private const val EXPECTED_GENERATIONS_GAME_2 = 12
        private const val EXPECTED_SCORE_1_GAME_2 = 130
        private const val EXPECTED_SCORE_2_GAME_2 = 69

        val EXPECTED_LOG_GAME_2 = GameLog(
            board = "Tharsis",
            isRanked = false,
            isOnline = true,
            start = "2023-01-15T10:28:43.683Z",
            players = Players(
                player1 = EXPECTED_PLAYER_1_GAME_2,
                player2 = EXPECTED_PLAYER_2_GAME_2
            ),
            turns = listOf(
                Turn(
                    generation = EXPECTED_GENERATIONS_GAME_2,
                    playerInfos = PlayerInfos(
                        playerInfo1 = PlayerInfo(
                            score = Score(EXPECTED_SCORE_1_GAME_2)
                        ),
                        playerInfo2 = PlayerInfo(
                            score = Score(EXPECTED_SCORE_2_GAME_2)
                        )
                    )
                )
            )
        )


        // utils
        fun loadJsonResource(resourceFilename: String): Path {
            return Companion::class.java.getResource("$RES_DIRECTORY$resourceFilename.json").toURI().toPath()
        }

        fun loadResourceDirectory(): Path {
            return Companion::class.java.getResource(RES_DIRECTORY).toURI().toPath()
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