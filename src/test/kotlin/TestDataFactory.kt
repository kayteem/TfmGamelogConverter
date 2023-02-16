import de.kayteem.apps.tfmgamelogconverter.model.internal.Boards
import de.kayteem.apps.tfmgamelogconverter.model.internal.Corporation
import de.kayteem.apps.tfmgamelogconverter.model.internal.Play
import de.kayteem.apps.tfmgamelogconverter.model.internal.Player
import de.kayteem.apps.tfmgamelogconverter.model.jsonImport.*
import kotlin.math.roundToInt

/**
 * Utility object for creating test data
 *
 * Author: Tobias Mielke
 */
object TestDataFactory {

    // play 1
    fun buildLogPlayer1Game1(): LogPlayer {
        return LogPlayer(
            name = "KayTeEm",
            color = "Green",
            corporation = "Thorgate",
            elo = 1601.0216806701019
        )
    }

    fun buildLogPlayer2Game1(): LogPlayer {
        return LogPlayer(
            name = "Player2",
            color = "Blue",
            corporation = "UNMI",
            elo = 1617.7076549970068
        )
    }

    fun buildGenerationsGame1(): Int = 11

    fun buildScore1Game1(): Int = 102

    fun buildScore2Game1(): Int = 98

    fun buildGameLog1(): GameLog {
        return GameLog(
            board = "Tharsis",
            isRanked = false,
            isOnline = true,
            start = "2023-01-10T18:03:55.4Z",
            players = Players(
                player1 = buildLogPlayer1Game1(),
                player2 = buildLogPlayer2Game1()
            ),
            turns = listOf(
                Turn(
                    generation = buildGenerationsGame1(),
                    playerInfos = PlayerInfos(
                        playerInfo1 = PlayerInfo(
                            score = Score(buildScore1Game1())
                        ),
                        playerInfo2 = PlayerInfo(
                            score = Score(buildScore2Game1())
                        )
                    )
                )
            )
        )
    }

    fun buildPlayer1Game1(): Player {
        val logPlayer1 = buildLogPlayer1Game1()

        return Player(
            name = logPlayer1.name,
            corporation = logPlayer1.corporation,
            elo = logPlayer1.elo.roundToInt(),
            finalScore = buildScore1Game1()
        )
    }

    fun buildPlayer2Game1(): Player {
        val logPlayer2 = buildLogPlayer2Game1()

        return Player(
            name = logPlayer2.name,
            corporation = logPlayer2.corporation,
            elo = logPlayer2.elo.roundToInt(),
            finalScore = buildScore2Game1()
        )
    }

    fun buildPlay1(): Play {
        val gameLog1 = buildGameLog1()
        val player1Game1 = buildPlayer1Game1()
        val player2Game1 = buildPlayer2Game1()

        return Play(
            timestamp = gameLog1.start,
            board = gameLog1.board,
            generations = gameLog1.generations(),
            players = listOf(player1Game1, player2Game1)
        )
    }


    // play 2
    fun buildLogPlayer1Game2(): LogPlayer {
        return LogPlayer(
            name = "KayTeEm",
            color = "Green",
            corporation = "InterplanetaryCinematics",
            elo = 1629.384708225632
        )
    }

    fun buildLogPlayer2Game2(): LogPlayer {
        return LogPlayer(
            name = "Player2",
            color = "Blue",
            corporation = "Inventrix",
            elo = 1696.4541812619316
        )
    }

    fun buildGenerationsGame2(): Int = 12

    fun buildScore1Game2(): Int = 130

    fun buildScore2Game2(): Int = 69

    fun buildGameLog2(): GameLog {
        return GameLog(
            board = "Tharsis",
            isRanked = false,
            isOnline = true,
            start = "2023-01-15T10:28:43.683Z",
            players = Players(
                player1 = buildLogPlayer1Game2(),
                player2 = buildLogPlayer2Game2()
            ),
            turns = listOf(
                Turn(
                    generation = buildGenerationsGame2(),
                    playerInfos = PlayerInfos(
                        playerInfo1 = PlayerInfo(
                            score = Score(buildScore1Game2())
                        ),
                        playerInfo2 = PlayerInfo(
                            score = Score(buildScore2Game2())
                        )
                    )
                )
            )
        )
    }

    fun buildPlayer1Game2(): Player {
        val logPlayer1 = buildLogPlayer1Game2()

        return Player(
            name = logPlayer1.name,
            corporation = logPlayer1.corporation,
            elo = logPlayer1.elo.roundToInt(),
            finalScore = buildScore1Game2()
        )
    }

    fun buildPlayer2Game2(): Player {
        val logPlayer2 = buildLogPlayer2Game2()

        return Player(
            name = logPlayer2.name,
            corporation = logPlayer2.corporation,
            elo = logPlayer2.elo.roundToInt(),
            finalScore = buildScore2Game2()
        )
    }

    fun buildPlay2(): Play {
        val gameLog2 = buildGameLog2()
        val player1Game2 = buildPlayer1Game2()
        val player2Game2 = buildPlayer2Game2()

        return Play(
            timestamp = gameLog2.start,
            board = gameLog2.board,
            generations = gameLog2.generations(),
            players = listOf(player1Game2, player2Game2)
        )
    }


    // corps
    fun buildCorp1(): Corporation {
        return Corporation(
            name = "Thorgate",
            playedOnMapByYou = mutableMapOf(
                Boards.THARSIS to 1
            ),
            playedOnMapByOpponents = mutableMapOf()
        )
    }

    fun buildCorp2(): Corporation {
        return Corporation(
            name = "UNMI",
            playedOnMapByYou = mutableMapOf(),
            playedOnMapByOpponents = mutableMapOf(
                Boards.THARSIS to 1
            )
        )
    }

    fun buildCorp3(): Corporation {
        return Corporation(
            name = "InterplanetaryCinematics",
            playedOnMapByYou = mutableMapOf(
                Boards.THARSIS to 1
            ),
            playedOnMapByOpponents = mutableMapOf()
        )
    }

    fun buildCorp4(): Corporation {
        return Corporation(
            name = "Inventrix",
            playedOnMapByYou = mutableMapOf(),
            playedOnMapByOpponents = mutableMapOf(
                Boards.THARSIS to 1
            )
        )
    }

}