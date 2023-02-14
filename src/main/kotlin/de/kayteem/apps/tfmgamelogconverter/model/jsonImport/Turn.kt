package de.kayteem.apps.tfmgamelogconverter.model.jsonImport

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Data class required for importing root/turnLogs/<TurnLog> elements from JSON.
 *
 * Author: Tobias Mielke
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class Turn(
    val generation: Int,

    @JsonAlias("playerInfo")
    val playerInfos: PlayerInfos
)
