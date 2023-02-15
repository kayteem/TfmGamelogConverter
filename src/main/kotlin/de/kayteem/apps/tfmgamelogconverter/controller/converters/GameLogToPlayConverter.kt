package de.kayteem.apps.tfmgamelogconverter.controller.converters

import de.kayteem.apps.tfmgamelogconverter.model.internal.Play
import de.kayteem.apps.tfmgamelogconverter.model.internal.Player
import de.kayteem.apps.tfmgamelogconverter.model.jsonImport.GameLog
import de.kayteem.apps.tfmgamelogconverter.model.jsonImport.LogPlayer
import kotlin.math.roundToInt

/**
 * Implementation for converting a GameLog into a play.
 *
 * Author: Tobias Mielke
 */
class GameLogToPlayConverter : Converter<GameLog, Play> {

    override fun process(input: GameLog): Play {

        // read log players and final scores
        val logPlayers = listOf(
            input.players.player1,
            input.players.player2,
            input.players.player3,
            input.players.player4,
            input.players.player5
        )
        val finalScores = input.finalScores()

        // convert to players
        val players = logPlayers.mapNotNull { logPlayerToPlayer(it, finalScores) }

        // convert game log to play
        return Play(
            input.start,
            input.board,
            input.generations(),
            players
        )
    }

    private fun logPlayerToPlayer(logPlayer: LogPlayer?, finalScores: Map<LogPlayer, Int>): Player? {
        if (logPlayer == null) {
            return null
        }

        return Player(
            logPlayer.name,
            logPlayer.corporation,
            logPlayer.elo.roundToInt(),
            finalScores[logPlayer]!!
        )
    }

}