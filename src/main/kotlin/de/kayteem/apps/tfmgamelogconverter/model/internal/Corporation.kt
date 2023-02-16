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
//    fun winRateOnMapYou(): Map<Boards, Double?> {
//        val winRateOnMapYou = mutableMapOf<Boards, Double?>()
//
//        playedOnMapByYou.forEach { (board, playCountOnMapByYou) ->
//            if (playCountOnMapByYou == 0) {
//                return@forEach
//            }
//
//            val winCountOnMapByYou = wonOnMapByYou.getOrDefault(board, 0)
//
//            winRateOnMapYou
//        }
//
//        return mapOf()
////        return winsOnMapByYou.getOrDefault(board, 0) / playedOnMapByYou.toDouble()
//    }
//
//    fun winRateOnMapOpponents(board: Boards): Double? {
//        val playedOnMapByOpponents = playedOnMapByOpponents.getOrElse(board) {
//            return null
//        }
//
//        return wonOnMapByOpponents.getOrDefault(board, 0) / playedOnMapByOpponents.toDouble()
//    }
//
//    fun winRateOnMapTotal(): Map<Boards, Double> {
//        return mapOf() // mergePercentages()
//    }


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

//    private fun mergePercentages(map1: Map<Boards, Double>, map2: Map<Boards, Double>): Map<Boards, Double> {
//        val merged = mutableMapOf<Boards, Double>()
//
//        map1.forEach { (board, count) ->
//            merged[board] = merged.getOrDefault(board, 0.0) + count
//        }
//
//        map2.forEach { (board, count) ->
//            merged[board] = merged.getOrDefault(board, 0.0) + count
//        }
//
//        return merged
//    }

    private fun sumOverAllMaps(map: Map<Boards, Int>): Int {
        return map
            .map { it.value }
            .sum()
    }

}