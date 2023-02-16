package de.kayteem.apps.tfmgamelogconverter.controller.converters

import de.kayteem.apps.tfmgamelogconverter.model.internal.Boards
import de.kayteem.apps.tfmgamelogconverter.model.internal.Corporation
import de.kayteem.apps.tfmgamelogconverter.model.internal.Play
import de.kayteem.apps.tfmgamelogconverter.model.internal.Player

/**
 * Implementation for extracting the username from a list of plays.
 * The username is considered to be the name of the most frequent player in all plays.
 *
 * Author: Tobias Mielke
 */
class PlaysToCorporationsConverter(private val username: String) : Converter<List<Play>, List<Corporation>> {

    // members
    private val corporations = mutableMapOf<String, Corporation>()


    // interface
    override fun process(input: List<Play>): List<Corporation> {
        input.forEach { processPlay(it) }

        return corporations
            .values
            .sortedBy { it.name }
            .toList()
    }

    
    // helpers
    private fun processPlay(play: Play) {
        val board = Boards.fromName(play.board)

        play.players.forEach { processPlayer(it, board) }
    }

    private fun processPlayer(player: Player, board: Boards) {
        val corpName = player.corporation
        val corporation = corporations.getOrPut(corpName) { Corporation(corpName) }

        val isUser = player.name == username

        if (isUser) {
            corporation.playedOnMapByYou[board] = corporation.playedOnMapByYou.getOrDefault(board, 0) + 1
        }
        else {
            corporation.playedOnMapByOpponents[board] = corporation.playedOnMapByOpponents.getOrDefault(board, 0) + 1
        }
    }

}