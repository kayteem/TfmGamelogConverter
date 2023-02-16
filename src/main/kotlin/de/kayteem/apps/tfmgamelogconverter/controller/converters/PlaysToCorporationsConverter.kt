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
        val winner = play.winner()
        val board = Boards.fromName(play.board)

        play.players.forEach { processPlayer(it, winner, board) }
    }

    private fun processPlayer(player: Player, winner: Player?, board: Boards) {
        val corpName = player.corporation
        val corporation = corporations.getOrPut(corpName) { Corporation(corpName) }
        val isUser = player.name == username

        with(corporation) {
            // count plays and wins of user
            if (isUser) {
                incMapEntry(playedOnMapByYou, board)

                if (player == winner) {
                    incMapEntry(wonOnMapByYou, board)
                }
            }

            // count plays and wins of opponents
            else {
                incMapEntry(playedOnMapByOpponents, board)

                if (player == winner) {
                    incMapEntry(wonOnMapByOpponents, board)
                }
            }
        }
    }

    private fun incMapEntry(map: MutableMap<Boards, Int>, board: Boards) {
        map[board] = map.getOrDefault(board, 0) + 1
    }

}