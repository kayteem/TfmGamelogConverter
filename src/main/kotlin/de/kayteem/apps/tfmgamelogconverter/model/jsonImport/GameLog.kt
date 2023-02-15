package de.kayteem.apps.tfmgamelogconverter.model.jsonImport

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Data class required for importing the root element from JSON.
 *
 * Author: Tobias Mielke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class GameLog(
    @JsonAlias("boardType")
    val board: String,

    val isRanked: Boolean,
    val isOnline: Boolean,

    @JsonAlias("gameStartTime")
    val start: String,

    val players: Players,

    @JsonAlias("turnLogs")
    val turns: List<Turn>
) {

    fun generations(): Int {
        return turns.maxOf { it.generation }
    }

    fun finalScores(): Map<LogPlayer, Int> {
        val allPlayers = players.all()

        return allPlayers.associateWith {
            finalPlayerScore(allPlayers.indexOf(it))
        }
    }

    private fun finalPlayerScore(playerIdx: Int): Int {
        val playerInfos = turns.last().playerInfos

        val playerInfo: PlayerInfo = when (playerIdx) {
            0 -> playerInfos.playerInfo1
            1 -> playerInfos.playerInfo2
            2 -> playerInfos.playerInfo3!!
            3 -> playerInfos.playerInfo4!!
            4 -> playerInfos.playerInfo5!!
            else -> throw Exception("Player at index $playerIdx not found!")
        }

        return playerInfo.score.finalScore
    }

}