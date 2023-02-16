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

    // play 1 - KayTeEm (Thorgate, 102) vs. TestDummy (UNMI, 98)
    fun buildLogPlayer1Game1(): LogPlayer {
        return LogPlayer(
            name = "KayTeEm",
            color = "Green",
            corporation = "Thorgate",
            elo = 1601.021680670102
        )
    }

    fun buildLogPlayer2Game1(): LogPlayer {
        return LogPlayer(
            name = "TestDummy",
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

    private fun buildPlayer1Game1(): Player {
        val logPlayer1 = buildLogPlayer1Game1()

        return Player(
            name = logPlayer1.name,
            corporation = logPlayer1.corporation,
            elo = logPlayer1.elo.roundToInt(),
            finalScore = buildScore1Game1()
        )
    }

    private fun buildPlayer2Game1(): Player {
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


    // play 2 - KayTeEm (InterplanetaryCinematics, 130) vs. TestDummy (Inventrix, 69)
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
            name = "TestDummy",
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

    private fun buildPlayer1Game2(): Player {
        val logPlayer1 = buildLogPlayer1Game2()

        return Player(
            name = logPlayer1.name,
            corporation = logPlayer1.corporation,
            elo = logPlayer1.elo.roundToInt(),
            finalScore = buildScore1Game2()
        )
    }

    private fun buildPlayer2Game2(): Player {
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


    // play 3 - Champion (MiningGuild, 78) vs. KayTeEm (PhoboLob, 67) vs. TestDummy (InterplanetaryCinematics, 67)
    fun buildLogPlayer1Game3(): LogPlayer {
        return LogPlayer(
            name = "Champion",
            color = "Green",
            corporation = "MiningGuild",
            elo = 1728.1534083286826
        )
    }

    fun buildLogPlayer2Game3(): LogPlayer {
        return LogPlayer(
            name = "KayTeEm",
            color = "Blue",
            corporation = "PhoboLob",
            elo = 1648.435568640261
        )
    }

    fun buildLogPlayer3Game3(): LogPlayer {
        return LogPlayer(
            name = "TestDummy",
            color = "Red",
            corporation = "InterplanetaryCinematics",
            elo = 1532.5213270800932
        )
    }

    fun buildGenerationsGame3(): Int = 9

    fun buildScore1Game3(): Int = 78

    fun buildScore2Game3(): Int = 67

    fun buildScore3Game3(): Int = 67

    fun buildGameLog3(): GameLog {
        return GameLog(
            board = "Elysium",
            isRanked = false,
            isOnline = true,
            start = "2023-01-16T21:54:09.028Z",
            players = Players(
                player1 = buildLogPlayer1Game3(),
                player2 = buildLogPlayer2Game3(),
                player3 = buildLogPlayer3Game3()
            ),
            turns = listOf(
                Turn(
                    generation = buildGenerationsGame3(),
                    playerInfos = PlayerInfos(
                        playerInfo1 = PlayerInfo(
                            score = Score(buildScore1Game3())
                        ),
                        playerInfo2 = PlayerInfo(
                            score = Score(buildScore2Game3())
                        ),
                        playerInfo3 = PlayerInfo(
                            score = Score(buildScore3Game3())
                        )
                    )
                )
            )
        )
    }

    private fun buildPlayer1Game3(): Player {
        val logPlayer1 = buildLogPlayer1Game3()

        return Player(
            name = logPlayer1.name,
            corporation = logPlayer1.corporation,
            elo = logPlayer1.elo.roundToInt(),
            finalScore = buildScore1Game3()
        )
    }

    private fun buildPlayer2Game3(): Player {
        val logPlayer2 = buildLogPlayer2Game3()

        return Player(
            name = logPlayer2.name,
            corporation = logPlayer2.corporation,
            elo = logPlayer2.elo.roundToInt(),
            finalScore = buildScore2Game3()
        )
    }

    private fun buildPlayer3Game3(): Player {
        val logPlayer3 = buildLogPlayer3Game3()

        return Player(
            name = logPlayer3.name,
            corporation = logPlayer3.corporation,
            elo = logPlayer3.elo.roundToInt(),
            finalScore = buildScore3Game3()
        )
    }

    fun buildPlay3(): Play {
        val gameLog3 = buildGameLog3()
        val player1Game3 = buildPlayer1Game3()
        val player2Game3 = buildPlayer2Game3()
        val player3Game3 = buildPlayer3Game3()

        return Play(
            timestamp = gameLog3.start,
            board = gameLog3.board,
            generations = gameLog3.generations(),
            players = listOf(player1Game3, player2Game3, player3Game3)
        )
    }


    // corps
    fun buildCorp1(): Corporation {
        return Corporation(
            name = "InterplanetaryCinematics",
            playedOnMapByYou = mutableMapOf(
                Boards.THARSIS to 1
            ),
            playedOnMapByOpponents = mutableMapOf(
                Boards.ELYSIUM to 1
            ),
            wonOnMapByYou = mutableMapOf(
                Boards.THARSIS to 1
            ),
            wonOnMapByOpponents = mutableMapOf(

            )
        )
    }

    fun buildCorp2(): Corporation {
        return Corporation(
            name = "Inventrix",
            playedOnMapByYou = mutableMapOf(

            ),
            playedOnMapByOpponents = mutableMapOf(
                Boards.THARSIS to 1
            ),
            wonOnMapByYou = mutableMapOf(

            ),
            wonOnMapByOpponents = mutableMapOf(

            )
        )
    }

    fun buildCorp3(): Corporation {
        return Corporation(
            name = "MiningGuild",
            playedOnMapByYou = mutableMapOf(

            ),
            playedOnMapByOpponents = mutableMapOf(
                Boards.ELYSIUM to 1
            ),
            wonOnMapByYou = mutableMapOf(

            ),
            wonOnMapByOpponents = mutableMapOf(
                Boards.ELYSIUM to 1
            )
        )
    }

    fun buildCorp4(): Corporation {
        return Corporation(
            name = "PhoboLob",
            playedOnMapByYou = mutableMapOf(
                Boards.ELYSIUM to 1
            ),
            playedOnMapByOpponents = mutableMapOf(

            ),
            wonOnMapByYou = mutableMapOf(

            ),
            wonOnMapByOpponents = mutableMapOf(

            )
        )
    }

    fun buildCorp5(): Corporation {
        return Corporation(
            name = "Thorgate",
            playedOnMapByYou = mutableMapOf(
                Boards.THARSIS to 1
            ),
            playedOnMapByOpponents = mutableMapOf(

            ),
            wonOnMapByYou = mutableMapOf(
                Boards.THARSIS to 1
            ),
            wonOnMapByOpponents = mutableMapOf(

            )
        )
    }

    fun buildCorp6(): Corporation {
        return Corporation(
            name = "UNMI",
            playedOnMapByYou = mutableMapOf(

            ),
            playedOnMapByOpponents = mutableMapOf(
                Boards.THARSIS to 1
            ),
            wonOnMapByYou = mutableMapOf(

            ),
            wonOnMapByOpponents = mutableMapOf(

            )
        )
    }

}