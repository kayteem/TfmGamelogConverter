package de.kayteem.apps.tfmgamelogconverter.model.internal

/**
 * Data class required as internal model for a corporation.
 *
 * Author: Tobias Mielke
 */
data class Corporation(
    val name: String,
    val playedOnMapByYou: MutableMap<Boards, Int> = mutableMapOf(),
    val playedOnMapByOpponents: MutableMap<Boards, Int> = mutableMapOf()
) {

    fun playedOnMapTotal(): Map<Boards, Int> {
        val playedOnMapTotal = mutableMapOf<Boards, Int>()

        playedOnMapByYou.forEach { (board, playCount) ->
            playedOnMapTotal[board] = playedOnMapTotal.getOrDefault(board, 0) + playCount
        }

        playedOnMapByOpponents.forEach { (board, playCount) ->
            playedOnMapTotal[board] = playedOnMapTotal.getOrDefault(board, 0) + playCount
        }

        return playedOnMapTotal
    }

    fun playedByYou(): Int {
        return playedOnMapByYou
            .map { it.value }
            .sum()
    }

    fun playedByOpponents(): Int {
        return playedOnMapByOpponents
            .map { it.value }
            .sum()
    }

    fun playedTotal(): Int {
        return playedByYou() + playedByOpponents()
    }

}