import de.kayteem.apps.tfmgamelogconverter.model.xlsxExport.Play
import de.kayteem.apps.tfmgamelogconverter.model.jsonImport.*
import kotlin.math.roundToInt

/**
 * Utility object for creating test data
 *
 * Author: Tobias Mielke
 */
object TestDataFactory {

    // game 1
    fun buildPlayer1Game1(): Player {
        return Player(
            name = "KayTeEm",
            color = "Green",
            corporation = "Thorgate",
            elo = 1601.0216806701019
        )
    }

    fun buildPlayer2Game1(): Player {
        return Player(
            name = "Player2",
            color = "Blue",
            corporation = "UNMI",
            elo = 1617.7076549970068
        )
    }

    fun buildLogGame1(): GameLog {
        val player1 = buildPlayer1Game1()
        val player2 = buildPlayer2Game1()
        val generations = 11
        val score1 = 102
        val score2 = 98

        return GameLog(
            board = "Tharsis",
            isRanked = false,
            isOnline = true,
            start = "2023-01-10T18:03:55.4Z",
            players = Players(
                player1 = player1,
                player2 = player2
            ),
            turns = listOf(
                Turn(
                    generation = generations,
                    playerInfos = PlayerInfos(
                        playerInfo1 = PlayerInfo(
                            score = Score(score1)
                        ),
                        playerInfo2 = PlayerInfo(
                            score = Score(score2)
                        )
                    )
                )
            )
        )
    }

    fun buildPlay1(): Play {
        val logGame1 = buildLogGame1()
        val player1 = logGame1.players.player1
        val player2 = logGame1.players.player2

        return Play(
            timestamp = logGame1.start,
            board = logGame1.board,
            generations = logGame1.generations(),
            player1Name = player1.name,
            player1Corp = player1.corporation,
            player1Elo = player1.elo.roundToInt(),
            player1Score = logGame1.finalScores()[player1] ?: 0,
            player2Name = player2.name,
            player2Corp = player2.corporation,
            player2Elo = player2.elo.roundToInt(),
            player2Score = logGame1.finalScores()[player2] ?: 0
        )
    }


    // game 2
    fun buildPlayer1Game2(): Player {
        return Player(
            name = "KayTeEm",
            color = "Green",
            corporation = "InterplanetaryCinematics",
            elo = 1629.384708225632
        )
    }

    fun buildPlayer2Game2(): Player {
        return Player(
            name = "Player2",
            color = "Blue",
            corporation = "Inventrix",
            elo = 1696.4541812619316
        )
    }

    fun buildLogGame2(): GameLog {
        val player1 = buildPlayer1Game2()
        val player2 = buildPlayer2Game2()
        val generations = 12
        val score1 = 130
        val score2 = 69

        return GameLog(
            board = "Tharsis",
            isRanked = false,
            isOnline = true,
            start = "2023-01-15T10:28:43.683Z",
            players = Players(
                player1 = player1,
                player2 = player2
            ),
            turns = listOf(
                Turn(
                    generation = generations,
                    playerInfos = PlayerInfos(
                        playerInfo1 = PlayerInfo(
                            score = Score(score1)
                        ),
                        playerInfo2 = PlayerInfo(
                            score = Score(score2)
                        )
                    )
                )
            )
        )
    }

    fun buildPlay2(): Play {
        val logGame2 = buildLogGame2()
        val player1 = logGame2.players.player1
        val player2 = logGame2.players.player2

        return Play(
            timestamp = logGame2.start,
            board = logGame2.board,
            generations = logGame2.generations(),
            player1Name = player1.name,
            player1Corp = player1.corporation,
            player1Elo = player1.elo.roundToInt(),
            player1Score = logGame2.finalScores()[player1] ?: 0,
            player2Name = player2.name,
            player2Corp = player2.corporation,
            player2Elo = player2.elo.roundToInt(),
            player2Score = logGame2.finalScores()[player2] ?: 0
        )
    }

}