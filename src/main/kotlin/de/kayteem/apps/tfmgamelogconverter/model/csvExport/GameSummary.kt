package de.kayteem.apps.tfmgamelogconverter.model.csvExport

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Tobias Mielke
 * Created on 21.01.2023
 * Changed on 21.01.2023
 */
data class GameSummary(
    @field:JsonProperty("Timestamp") val timestamp: String,
    @field:JsonProperty("Board") val board: String,
    @field:JsonProperty("Player1Name") val player1Name: String,
    @field:JsonProperty("Player1Elo") val player1Elo: Int? = null,
    @field:JsonProperty("Player1Score") val player1Score: Int,
    @field:JsonProperty("Player2Name") val player2Name: String,
    @field:JsonProperty("Player2Elo") val player2Elo: Int? = null,
    @field:JsonProperty("Player2Score") val player2Score: Int,
    @field:JsonProperty("Player3Name") val player3Name: String? = null,
    @field:JsonProperty("Player3Elo") val player3Elo: Int? = null,
    @field:JsonProperty("Player3Score") val player3Score: Int? = null,
    @field:JsonProperty("Player4Name") val player4Name: String? = null,
    @field:JsonProperty("Player4Elo") val player4Elo: Int? = null,
    @field:JsonProperty("Player4Score") val player4Score: Int? = null,
    @field:JsonProperty("Player5Name") val player5Name: String? = null,
    @field:JsonProperty("Player5Elo") val player5Elo: Int? = null,
    @field:JsonProperty("Player5Score") val player5Score: Int? = null,
    @field:JsonProperty("Generations") val generations: Int
)
