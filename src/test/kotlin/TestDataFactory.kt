import de.kayteem.apps.tfmgamelogconverter.model.csvExport.GameSummary
import de.kayteem.apps.tfmgamelogconverter.model.jsonImport.*

/**
 * Created by Tobias Mielke
 * Created on 21.01.2023
 * Changed on 21.01.2023
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

    fun buildSummaryGame1(): GameSummary {
        return GameSummary(
            timestamp = "2023-01-10T18:03:55.4Z",
            board = "Tharsis",
            player1Name = "KayTeEm",
            player1Score = 102,
            player2Name = "Player2",
            player2Score = 98,
            generations = 11
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

    fun buildSummaryGame2(): GameSummary {
        return GameSummary(
            timestamp = "2023-01-15T10:28:43.683Z",
            board = "Tharsis",
            player1Name = "KayTeEm",
            player1Score = 130,
            player2Name = "Player2",
            player2Score = 69,
            generations = 12
        )
    }

}