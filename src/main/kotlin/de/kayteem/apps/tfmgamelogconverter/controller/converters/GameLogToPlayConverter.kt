package de.kayteem.apps.tfmgamelogconverter.controller.converters

import de.kayteem.apps.tfmgamelogconverter.model.jsonImport.GameLog
import de.kayteem.apps.tfmgamelogconverter.model.xlsxExport.Play
import kotlin.math.roundToInt

/**
 * Implementation for converting a GameLog into a play.
 *
 * Author: Tobias Mielke
 */
class GameLogToPlayConverter : Converter<GameLog, Play> {

    override fun process(gameLog: GameLog): Play {
        val player1 = gameLog.players.player1
        val player2 = gameLog.players.player2
        val player3 = gameLog.players.player3
        val player4 = gameLog.players.player4
        val player5 = gameLog.players.player5
        val finalScores = gameLog.finalScores()

        return Play(
            timestamp = gameLog.start,
            board = gameLog.board,
            generations = gameLog.generations(),
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

}