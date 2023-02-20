package de.kayteem.apps.tfmgamelogconverter.model.internal

/**
 * Data class required as internal model for a corporation.
 *
 * Author: Tobias Mielke
 */
data class Corporation(
    val name: String,
    val playedOnMapByYou: MutableMap<Boards, Int> = mutableMapOf(),
    val playedOnMapByOpponents: MutableMap<Boards, Int> = mutableMapOf(),
    val wonOnMapByYou: MutableMap<Boards, Int> = mutableMapOf(),
    val wonOnMapByOpponents: MutableMap<Boards, Int> = mutableMapOf()
) {

    // play counts
    fun playedOnMap(): Map<Boards, Int> {
        return mergeCounts(playedOnMapByYou, playedOnMapByOpponents)
    }

    fun playedByYou(): Int {
        return sumOverAllMaps(playedOnMapByYou)
    }

    fun playedByOpponents(): Int {
        return sumOverAllMaps(playedOnMapByOpponents)
    }

    fun playedTotal(): Int {
        return playedByYou() + playedByOpponents()
    }


    // wins
    fun wonOnMap(): Map<Boards, Int> {
        return mergeCounts(wonOnMapByYou, wonOnMapByOpponents)
    }

    fun wonByYou(): Int {
        return sumOverAllMaps(wonOnMapByYou)
    }

    fun wonByOpponents(): Int {
        return sumOverAllMaps(wonOnMapByOpponents)
    }

    fun wonTotal(): Int {
        return wonByYou() + wonByOpponents()
    }


    // win rates
    fun winRateOnMapByYou(): Map<Boards, Double?> {
        return winRateOnMap(playedOnMapByYou, wonOnMapByYou)
    }

    fun winRateOnMapByOpponents(): Map<Boards, Double?> {
        return winRateOnMap(playedOnMapByOpponents, wonOnMapByOpponents)
    }

    fun winRateOnMap(): Map<Boards, Double?> {
        return winRateOnMap(playedOnMap(), wonOnMap())
    }

    fun winRateByYou(): Double? {
        return winRate(playedByYou(), wonByYou())
    }

    fun winRateByOpponents(): Double? {
        return winRate(playedByOpponents(), wonByOpponents())
    }

    fun winRateTotal(): Double? {
        return winRate(playedTotal(), wonTotal())
    }


    // helpers
    private fun mergeCounts(map1: Map<Boards, Int>, map2: Map<Boards, Int>): Map<Boards, Int> {
        val merged = mutableMapOf<Boards, Int>()

        map1.forEach { (board, count) ->
            merged[board] = merged.getOrDefault(board, 0) + count
        }

        map2.forEach { (board, count) ->
            merged[board] = merged.getOrDefault(board, 0) + count
        }

        return merged
    }

    private fun sumOverAllMaps(map: Map<Boards, Int>): Int {
        return map
            .map { it.value }
            .sum()
    }

    private fun winRateOnMap(playedOnMap: Map<Boards, Int>, wonOnMap: Map<Boards, Int>): Map<Boards, Double?> {
        val winRateOnMap = mutableMapOf<Boards, Double?>()

        Boards.values().forEach { board ->
            val playCount = playedOnMap.getOrDefault(board, 0)
            if (playCount == 0) {
                return@forEach
            }

            val winCount = wonOnMap.getOrDefault(board, 0)
            val winRate = winCount / playCount.toDouble()

            winRateOnMap[board] = winRate
        }

        return winRateOnMap
    }

    private fun winRate(playCount: Int, winCount: Int): Double? {
        if (playCount == 0) {
            return null
        }

        return winCount / playCount.toDouble()
    }

}